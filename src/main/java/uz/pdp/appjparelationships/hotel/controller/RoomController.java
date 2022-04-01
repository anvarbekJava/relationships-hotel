package uz.pdp.appjparelationships.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjparelationships.hotel.entity.Hotel;
import uz.pdp.appjparelationships.hotel.entity.Room;
import uz.pdp.appjparelationships.hotel.payload.RoomDto;
import uz.pdp.appjparelationships.hotel.repository.HotelRepository;
import uz.pdp.appjparelationships.hotel.repository.RoomRepository;

import java.util.Optional;

@RestController
@RequestMapping(value = "/room")
public class RoomController {
    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    RoomRepository roomRepository;

    @PostMapping(value = "/addRoom")
    public String addRoom(@RequestBody RoomDto roomDto){
        boolean exists = roomRepository.existsByNumberAndHotel_Id(roomDto.getNumber(), roomDto.getHotelId());
        if (exists)
            return "This room such faculty exist";
        Room room  = new Room();
        room.setNumber(roomDto.getNumber());
        room.setFloor(roomDto.getFloor());
        room.setSize(roomDto.getSize());
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());

        if (!optionalHotel.isPresent())
            return "Hotel no found";
        Hotel hotel = optionalHotel.get();
        room.setHotel(hotel);
        roomRepository.save(room);
        return "Save Room";
    }

    @GetMapping(value = "/getByHotel/{id}")
    public Page<Room> getByHotelId(@PathVariable Integer id, @RequestParam int page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<Room> roomPage = roomRepository.findAllByHotel_Id(id, pageable);
        return roomPage;
    }

    @PutMapping(value = "/edet/{id}")
    public String edetRoom(@PathVariable Integer id, @RequestBody RoomDto roomDto){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent())
            return "Room not found!";
        Room room = optionalRoom.get();
        room.setNumber(roomDto.getNumber());
        room.setFloor(roomDto.getFloor());
        room.setSize(roomDto.getSize());
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());

        if (!optionalHotel.isPresent())
            return "Hotel no found";
        Hotel hotel = optionalHotel.get();
        room.setHotel(hotel);
        roomRepository.save(room);
        return "Edet Room";
    }
    @DeleteMapping(value = "/deleteRoom/{id}")
    public String deleteRoom(@PathVariable Integer id){
        try{
            roomRepository.deleteById(id);
            return "Delete room";
        }catch (Exception e){
            return "Error Deleting";
        }
    }
}
