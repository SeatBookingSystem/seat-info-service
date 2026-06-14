package seat_info_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShowDetail {

    private String showId;
    private String movieName;
    private String theatre;
    private LocalDateTime time;

}