package seat_info_service.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import seat_info_service.dto.Status;

@Document(collection = "seat_info")
@Data
public class SeatDocument {

    @Id
    private String id;

    private String showId;
    private String seatId;
    private Status status;
    private double price;

}
