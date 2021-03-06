package meet.dashboard.adapters;

import meet.dashboard.model.Visit;
import meet.dashboard.ports.DashboardBindings;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;

import static meet.dashboard.ports.DashboardBindings.VISITS_BEFORE_AGGREGATION;
import static meet.dashboard.ports.DashboardBindings.VISITS_STATISTICS_VIEW;

@EnableBinding(DashboardBindings.class)
public class AggregateProcessor {

    @StreamListener
    public void visitsByDisease(@Input(VISITS_BEFORE_AGGREGATION) KStream<String, Visit> events) {
        events
                .map((key, value) -> new KeyValue<>(value.getDiagnosedDisease().toString(), "0"))
                .groupByKey()
                .count(Materialized.as(VISITS_STATISTICS_VIEW))
                .toStream();
    }

}
