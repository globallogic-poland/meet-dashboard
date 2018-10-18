package meet.dashboard.ports;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ChannelSource {
    @Output("test-source")
    MessageChannel sampleSource();
}
