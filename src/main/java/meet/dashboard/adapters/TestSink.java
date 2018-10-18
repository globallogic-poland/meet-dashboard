package meet.dashboard.adapters;

import lombok.extern.slf4j.Slf4j;
import meet.dashboard.ports.Sink;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import static org.apache.kafka.common.requests.DeleteAclsResponse.log;

@Slf4j
@EnableBinding(Sink.class)
public class TestSink {

    @StreamListener("test-sink")
    public void receive(String payload) {
        log.info("Data received: " + payload);
    }
}
	