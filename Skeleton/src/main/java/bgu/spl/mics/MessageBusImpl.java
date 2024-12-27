package bgu.spl.mics;

import java.util.List;
import java.util.concurrent.*;

/**
 * The {@link MessageBusImpl class is the implementation of the MessageBus interface.
 * Write your implementation here!
 * Only one public method (in addition to getters which can be public solely for unit testing) may be added to this class
 * All other methods and members you add the class must be private.
 */
public class MessageBusImpl implements MessageBus {

    // Fields
    private final ConcurrentHashMap<MicroService, BlockingQueue<Message>> messageQueues; 

    private final ConcurrentHashMap<Class<? extends Event<?>>, BlockingQueue<MicroService>> eventSubscribers;
    private final ConcurrentHashMap<Class<? extends Broadcast>, List<MicroService>> broadcastSubscribers;

    private final ConcurrentHashMap<Event<?>, Future<?>> eventFutures;


    // Private constructor for Singleton 
    private MessageBusImpl() { 
        messageQueues = new ConcurrentHashMap<>();
        eventSubscribers = new ConcurrentHashMap<>();
        broadcastSubscribers = new ConcurrentHashMap<>();
        eventFutures = new ConcurrentHashMap<>();
    }

    // Singleton Holder (Thread-Safe)
    private static class SingletonHolder {
        private static final MessageBusImpl instance = new MessageBusImpl();
    }

    // Public access to the singleton instance
    public static MessageBusImpl getInstance() {
        return SingletonHolder.instance;
    }

    @Override
    public <T> void subscribeEvent(Class<? extends Event<T>> type, MicroService m) {
        eventSubscribers.computeIfAbsent(type, k -> new LinkedBlockingQueue<>()).add(m);
    }

    @Override
    public void subscribeBroadcast(Class<? extends Broadcast> type, MicroService m) {
        broadcastSubscribers.computeIfAbsent(type, k -> new CopyOnWriteArrayList<>()).add(m);
    }

    @Override
    public synchronized <T> void complete(Event<T> e, T result) {
        Future<T> future = (Future<T>) eventFutures.remove(e); 
        if (future != null) {
            future.resolve(result);
        }
    }

	@Override
    public void sendBroadcast(Broadcast b) {
        broadcastSubscribers.computeIfAbsent(b.getClass(), k -> new CopyOnWriteArrayList<>())
            .forEach(m -> {
                BlockingQueue<Message> queue = messageQueues.computeIfAbsent(m, k -> new LinkedBlockingQueue<>());
                queue.offer(b);
            });
        synchronized (this) {
            this.notifyAll();
        }
	}

    @Override
    public <T> Future<T> sendEvent(Event<T> e) {
        BlockingQueue<MicroService> queue = eventSubscribers.get(e.getClass());
        if (queue == null) {
            return null;
        }

        MicroService m;
        try {
            m = queue.take(); // Choosing the first micro-service
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            return null;
        }
       
        Future<T> future = new Future<>();
        synchronized (m) {
            messageQueues.computeIfAbsent(m, k -> new LinkedBlockingQueue<>()).add(e); // Add event to the correct queue
            eventFutures.put(e, future); // Track the future for this event
            queue.offer(m); // Return the microservice to the queue for round-robin scheduling
            m.notifyAll(); // Notify the microservice about the new event
        }
        return future;
    }

    @Override
    public synchronized void register(MicroService m) {
        messageQueues.computeIfAbsent(m, k -> new LinkedBlockingQueue<>());
    }

	@Override
	public synchronized void unregister(MicroService m) {   //synchronize
		messageQueues.remove(m);
		eventSubscribers.forEach((key, value) -> value.remove(m));
	    broadcastSubscribers.forEach((key, value) -> value.remove(m));
	    }

    @Override
    public Message awaitMessage(MicroService m) throws InterruptedException {
        BlockingQueue<Message> eventQueue = messageQueues.get(m);

        if (eventQueue == null) {
            throw new IllegalStateException("MicroService is not registered: " + m);
        }

        synchronized (m) {
            while (true) {
                try {
                    Message message = eventQueue.poll();
                    if (message != null) {
                        return message;
                    }
                    m.wait(); // Wait only for this specific MicroService
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw e;
                }
            }
        }
    }
}


