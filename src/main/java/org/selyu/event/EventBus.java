package org.selyu.event;

import org.selyu.event.annotation.Subscribe;
import org.selyu.event.subscriber.Subscriber;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class EventBus {
    private final Map<Class<?>, Set<Subscriber>> eventToSubscriberMap = new HashMap<>();

    public void register(Object object) {
        Objects.requireNonNull(object, "registered object cannot be null!");
        for (Method declaredMethod : object.getClass().getDeclaredMethods()) {
            if (declaredMethod.getParameterCount() != 1 || !declaredMethod.isAnnotationPresent(Subscribe.class))
                continue;

            Class<?> eventClass = declaredMethod.getParameterTypes()[0];
            Set<Subscriber> subscribers = eventToSubscriberMap.getOrDefault(eventClass, Collections.emptySet());
            subscribers.add(new Subscriber(object, declaredMethod));

            eventToSubscriberMap.put(eventClass, subscribers);
        }
    }

    public void post(Object object) {
        Objects.requireNonNull(object, "posted object cannot be null!");
        if (!eventToSubscriberMap.containsKey(object.getClass()))
            return;

        Set<Subscriber> subscribers = eventToSubscriberMap.getOrDefault(object.getClass(), Collections.emptySet());
        if (subscribers.isEmpty())
            return;

        subscribers.forEach(subscriber -> {
            try {
                subscriber.invoke(object);
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }
}