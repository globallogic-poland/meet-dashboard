package meet.dashboard.ports;

import meet.dashboard.model.Disease;
import meet.dashboard.model.Visit;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;

public interface DashboardBindings {

    String VISITS_BEFORE_AGGREGATION = "visits-before-aggregation";
//    String VISITS_AFTER_AGGREGATION = "visits-after-aggregation";

    String VISITS_STATISTICS_VIEW = "visits-statistics-view";

    @Input(VISITS_BEFORE_AGGREGATION)
    KStream<String, Visit> visitsBeforeAggregation();

//    @Output(VISITS_AFTER_AGGREGATION)
//    KStream<Disease, Double> visitsAfterAggregation();

}
