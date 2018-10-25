package meet.dashboard.adapters;

import meet.dashboard.model.Visit;
import meet.dashboard.ports.DashboardBindings;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;

import static meet.dashboard.ports.DashboardBindings.*;

@EnableBinding(DashboardBindings.class)
public class AggregateProcessor {

    @StreamListener
//    @SendTo(VISITS_AFTER_AGGREGATION)
    //    public KStream<Disease, Long> visitsByDisease(@Input(VISITS_BEFORE_AGGREGATION) KStream<String, Visit> events) {
    public void visitsByDisease(@Input(VISITS_BEFORE_AGGREGATION) KStream<String, Visit> events) {
        events
                .map((key, value) -> new KeyValue<>(value.getDiagnosedDisease().toString(), "0"))
                .groupByKey()
                .count(Materialized.as(VISITS_STATISTICS_VIEW))
                .toStream();
    }

//    @StreamListener
//    @SendTo(PAGE_COUNTS_OUT)
//    public KStream<String, Long> process(@Input(VISITS_BEFORE_AGGREGATION) KStream<String, Visit> events) {
//
//        return events
//                .peek((key, value) -> log.info("==== PEEK: k:{} v:{}", key, value))
//                .filter((key, value) -> value.getDurationSpentOnPage() > 10)
//                .map((key, value) -> new KeyValue<>(value.getPage(), "0"))
//                .groupByKey()
//                .count(Materialized.as(PAGE_COUNTS_MV))
//                .toStream();
//    }


}
