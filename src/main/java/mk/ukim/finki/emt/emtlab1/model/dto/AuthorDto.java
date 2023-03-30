package mk.ukim.finki.emt.emtlab1.model.dto;


import lombok.Data;
import mk.ukim.finki.emt.emtlab1.model.Country;

@Data
public class AuthorDto {
    String name;

    String surname;

    Long countryId;
}
