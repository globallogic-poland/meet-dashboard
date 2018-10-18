package meet.dashboard.adapters;

import meet.dashboard.ports.ChannelSource;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.messaging.support.GenericMessage;

import java.util.concurrent.atomic.AtomicBoolean;

@EnableBinding(ChannelSource.class)
public class ExampleMessageSource {

    private AtomicBoolean semaphore = new AtomicBoolean(true);

    @Bean
    @InboundChannelAdapter(channel = "test-source", poller = @Poller(fixedDelay = "1000"))
    public org.springframework.integration.core.MessageSource<String> sendTestData() {
        return () -> new GenericMessage<>(semaphore.getAndSet(!semaphore.get()) ? "ping" : "pong");
    }
}