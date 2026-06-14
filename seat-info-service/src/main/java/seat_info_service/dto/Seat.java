package seat_info_service.dto;

import lombok.Data;

@Data
public class Seat {

    private String seatId;
    private Status status;
    private double price;
}
