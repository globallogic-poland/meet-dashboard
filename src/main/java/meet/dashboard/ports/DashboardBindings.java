package meet.dashboard.ports;

import meet.dashboard.model.Visit;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;

public interface DashboardBindings {

    String VISITS_BEFORE_AGGREGATION = "visits-before-aggregation";

    String VISITS_STATISTICS_VIEW = "visits-statistics-view";

    @Input(VISITS_BEFORE_AGGREGATION)
    KStream<String, Visit> visitsBeforeAggregation();
    
}
