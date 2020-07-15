package org.selyu.event;

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public final class AsyncEventBus extends EventBus {
    private final Executor executor;

    public AsyncEventBus() {
        this(Executors.newFixedThreadPool(2));
    }

    public AsyncEventBus(Executor executor) {
        Objects.requireNonNull(executor, "executor cannot be null!");
        this.executor = executor;
    }

    @Override
    public void post(Object object) {
        executor.execute(() -> super.post(object));
    }
}
