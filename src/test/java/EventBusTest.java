import org.junit.Test;
import org.selyu.event.EventBus;
import org.selyu.event.annotation.Subscribe;

import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class EventBusTest {
    private static final String COMPARE_AGAINST = "1Ka0kUC97NLQ1nz8Ar";
    private static final EventBus EVENT_BUS = new EventBus();
    private static Object object;

    @Test
    public void subscribe_and_post() {
        EVENT_BUS.register(this);
        EVENT_BUS.post(COMPARE_AGAINST);
    }

    @Test
    public void subscribe_and_unsubscribe() {
        EVENT_BUS.register(this);
        EVENT_BUS.unregister(this);
        EVENT_BUS.post(System.out);
        assertNull(object);
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
