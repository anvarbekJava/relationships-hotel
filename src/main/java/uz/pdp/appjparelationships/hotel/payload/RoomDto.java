package uz.pdp.appjparelationships.hotel.payload;

import lombok.Data;
import uz.pdp.appjparelationships.hotel.entity.Hotel;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

@Data
public class RoomDto {

    private Integer number;

    private Integer floor;

    private Integer size;

    private Integer hotelId;
}
