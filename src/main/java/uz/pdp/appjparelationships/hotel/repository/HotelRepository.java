package uz.pdp.appjparelationships.hotel.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appjparelationships.hotel.entity.Hotel;
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    boolean existsByName(String name);
}
