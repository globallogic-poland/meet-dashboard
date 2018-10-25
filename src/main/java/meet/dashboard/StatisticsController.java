package meet.dashboard;

import lombok.AllArgsConstructor;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.cloud.stream.binder.kafka.streams.QueryableStoreRegistry;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

import static meet.dashboard.ports.DashboardBindings.VISITS_STATISTICS_VIEW;

@AllArgsConstructor
@RestController
public class StatisticsController {

    private final QueryableStoreRegistry registry;

    @GetMapping(path = "/stats", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<Map<String, Long>> stats() {
        ReadOnlyKeyValueStore<String, Long> store = registry.getQueryableStoreType(
                VISITS_STATISTICS_VIEW, QueryableStoreTypes.keyValueStore());
        return Flux.fromIterable(store::all)
                .collectMap(kv -> kv.key, kv -> kv.value);
    }

}