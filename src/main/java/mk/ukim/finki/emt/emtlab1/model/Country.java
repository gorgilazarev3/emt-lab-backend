package mk.ukim.finki.emt.emtlab1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Country {
    @Id
    @GeneratedValue
    Long id;

    String name;

    String continent;

    public Country(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }
}
