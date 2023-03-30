package mk.ukim.finki.emt.emtlab1.model.dto;

import lombok.Data;
import mk.ukim.finki.emt.emtlab1.model.Author;
import mk.ukim.finki.emt.emtlab1.model.enumerations.BookType;

@Data
public class BookDto {
    String name;

    BookType category;

    Long authorId;

    int availableCopies;
}
