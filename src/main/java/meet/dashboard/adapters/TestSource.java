package meet.dashboard.adapters;

import meet.dashboard.ports.Source;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

import java.util.concurrent.atomic.AtomicBoolean;

@EnableBinding(Source.class)
public class TestSource {

    private AtomicBoolean semaphore = new AtomicBoolean(true);

    @Bean
    @InboundChannelAdapter(channel = "test-source", poller = @Poller(fixedDelay = "1000"))
    public MessageSource<String> sendTestData() {
        return () -> new GenericMessage<>(semaphore.getAndSet(!this.semaphore.get()) ? "foo" : "bar");
    }
}