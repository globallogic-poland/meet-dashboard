package meet.dashboard.adapters;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import reactor.core.publisher.Flux;

import java.time.Duration;

@EnableBinding(Processor.class)
public class AggregateProcessor {

    @StreamListener
    @Output(Processor.OUTPUT)
    public Flux<String> aggregate(@Input(Processor.INPUT) Flux<String> inbound) {
        return inbound.
                log()
                .window(Duration.ofSeconds(5), Duration.ofSeconds(5))
                .flatMap(w -> w.reduce("", (s1, s2) -> s1 + s2))
                .log();
    }

}
