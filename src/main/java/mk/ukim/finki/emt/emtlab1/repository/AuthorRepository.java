package mk.ukim.finki.emt.emtlab1.repository;

import mk.ukim.finki.emt.emtlab1.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
