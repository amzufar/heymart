package heymart.heymart.repository;

import heymart.heymart.model.Supermarket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupermarketRepository extends JpaRepository<Supermarket, Long> {
    Supermarket save(Supermarket supermarket);
}
