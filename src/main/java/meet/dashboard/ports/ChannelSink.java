package meet.dashboard.ports;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ChannelSink {
    @Input("test-sink")
    SubscribableChannel sampleSink();
}