package uz.pdp.appjparelationships.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjparelationships.hotel.entity.Hotel;
import uz.pdp.appjparelationships.hotel.repository.HotelRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/hotel")
public class HotelController {
   @Autowired
    HotelRepository hotelRepository;


   @PostMapping(value = "/addHotel")
    public String addHotel(@RequestBody Hotel hotel){
       boolean existsByName = hotelRepository.existsByName(hotel.getName());
       if (existsByName)
           return "This Hotel such faculty exist";
       hotelRepository.save(hotel);
       return "Save success"+hotel.getName();
   }

   @GetMapping(value = "/getHotel")
    public List<Hotel> getHotel(){
       return hotelRepository.findAll();
   }
   @DeleteMapping(value = "/deleteHotel/{id}")
    public String deleteHotel(@PathVariable Integer id){
      try{
          hotelRepository.deleteById(id);
          return "Delete Hotel";
      }catch (Exception e){
          return "Error not found";
      }

   }
   @PutMapping(value = "/edetHotel/{id}")
    public String edetHotel(@PathVariable Integer id, @RequestBody Hotel hotel){
       Optional<Hotel> optionalHotel = hotelRepository.findById(id);
       if (!optionalHotel.isPresent())
           return "Hotel not found";
       Hotel hotelOne = optionalHotel.get();
       hotelOne.setName(hotel.getName());
       hotelRepository.save(hotelOne);
       return "Edet Hotel"+hotel.getName();

   }
}
