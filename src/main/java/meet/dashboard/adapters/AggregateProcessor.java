package meet.dashboard.adapters;

import meet.dashboard.model.Disease;
import meet.dashboard.model.Visit;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;

import static meet.dashboard.ports.DashboardBindings.*;

@EnableBinding(Processor.class)
public class AggregateProcessor {

    @StreamListener
    @SendTo(VISITS_AFTER_AGGREGATION)
    public KStream<Disease, Long> visitsByDisease(@Input(VISITS_BEFORE_AGGREGATION) KStream<String, Visit> events) {
        return events
                .map((key, value) -> new KeyValue<>(value.getDiagnosedDisease(), "0"))
                .groupByKey()
                .count(Materialized.as(VISITS_STATISTICS_VIEW))
                .toStream();
    }

}
