import org.junit.Test;
import org.selyu.event.AsyncEventBus;
import org.selyu.event.EventBus;
import org.selyu.event.annotation.Subscribe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class AsyncEventBusTest {
    private static final String COMPARE_AGAINST = "hM8ZUc1ejpq";
    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(1);
    private static final EventBus EVENT_BUS = new AsyncEventBus();

    @Test
    public void subscribe_and_post() throws InterruptedException {
        EVENT_BUS.register(this);
        EVENT_BUS.post(COMPARE_AGAINST);
        COUNT_DOWN_LATCH.await(1, TimeUnit.SECONDS);
    }

    @Subscribe
    public void subscribed_method(String string) {
        COUNT_DOWN_LATCH.countDown();
        assertEquals(string, COMPARE_AGAINST);
    }
}
