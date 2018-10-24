package meet.dashboard.model;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.Set;

@Value
@Builder
public class Patient {

    String id;

    String firstName;

    String lastName;

    int age;

    Sex sex;

    @Singular
    Set<Disease> chronicDiseases;

    String district;

    String city;

    String country;

}
