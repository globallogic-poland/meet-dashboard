package meet.dashboard;

import lombok.AllArgsConstructor;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.cloud.stream.binder.kafka.streams.QueryableStoreRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static meet.dashboard.ports.DashboardBindings.VISITS_STATISTICS_VIEW;

@AllArgsConstructor
@RestController
public class StatisticsController {

    private final QueryableStoreRegistry registry;

    @GetMapping("/counts")
    public Map<String, Long> counts() {
        ReadOnlyKeyValueStore<String, Long> store = registry
                .getQueryableStoreType(VISITS_STATISTICS_VIEW, QueryableStoreTypes.keyValueStore());

        Map<String, Long> m = new HashMap<>();
        KeyValueIterator<String, Long> iterator = store.all();

        while (iterator.hasNext()) {
            KeyValue<String, Long> next = iterator.next();
            m.put(next.key, next.value);
        }
        return m;
    }

//    @GetMapping("/counts")
//    public Map<String, Long> counts() {
//        ReadOnlyKeyValueStore<String, Long> store = registry.getQueryableStoreType(
//                VISITS_STATISTICS_VIEW, QueryableStoreTypes.keyValueStore());
//        Iterable<KeyValue<String, Long>> iterable = store::all;
//        return StreamSupport.stream(iterable.spliterator(), false)
//                .collect(Collectors.toMap(e -> e.key, e -> e.value));
//    }


}