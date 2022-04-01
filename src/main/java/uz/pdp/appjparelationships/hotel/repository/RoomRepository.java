package uz.pdp.appjparelationships.hotel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appjparelationships.hotel.entity.Room;
@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    boolean existsByNumberAndHotel_Id(Integer number, Integer hotel_id);
    Page<Room> findAllByHotel_Id(Integer hotel_id, Pageable pageable);
}
