import org.junit.Test;
import org.selyu.event.EventBus;
import org.selyu.event.annotation.Subscribe;

import static org.junit.Assert.assertEquals;

public class EventBusTest {
    private static final String COMPARE_AGAINST = "1Ka0kUC97NLQ1nz8Ar";

    @Test
    public void subscribe_and_post() {
        EventBus eventBus = new EventBus();
        eventBus.register(this);
        eventBus.post(COMPARE_AGAINST);
    }

    @Subscribe
    public void subscribed_method(String string) {
        assertEquals(string, COMPARE_AGAINST);
    }
}
