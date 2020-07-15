import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.selyu.event.EventBus;
import org.selyu.event.annotation.Subscribe;

import java.io.PrintStream;

import static org.junit.Assert.*;

public class EventBusTest {
    private static final String COMPARE_AGAINST = "1Ka0kUC97NLQ1nz8Ar";

    private static EventBus eventBus = new EventBus();
    private static Object object;

    @Test
    public void subscribe_and_post() {
        eventBus.register(this);
        eventBus.post(COMPARE_AGAINST);
    }

    @Test
    public void subscribe_and_unsubscribe() {
        eventBus.register(this);
        eventBus.unregister(this);
        eventBus.post(System.out);
        assertNull(object);
    }

    @After
    public void cleanup() {
        eventBus = null;
    }

    @Subscribe
    public void subscribed_method(String string) {
        assertEquals(string, COMPARE_AGAINST);
    }

    @Subscribe
    public void unsubscribed_method(PrintStream printStream) {
        object = new Object();
        printStream.println("bad");
    }
}
