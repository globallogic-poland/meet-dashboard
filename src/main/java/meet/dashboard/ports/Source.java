package meet.dashboard.ports;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Source {
    @Output("test-source")
    MessageChannel sampleSource();
}
