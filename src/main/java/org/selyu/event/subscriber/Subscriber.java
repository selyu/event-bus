package org.selyu.event.subscriber;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class Subscriber {
    private final Object owner;
    private final Method method;

    public Subscriber(Object owner, Method method) {
        this.owner = owner;
        this.method = method;
    }

    public void invoke(Object object) throws InvocationTargetException, IllegalAccessException {
        if (!method.canAccess(owner))
            method.trySetAccessible();

        method.invoke(owner, object);
        method.setAccessible(false);
    }
}
