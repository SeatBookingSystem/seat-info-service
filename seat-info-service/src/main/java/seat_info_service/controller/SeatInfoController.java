package seat_info_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seat_info_service.dto.Seat;
import seat_info_service.dto.ShowDetail;
import seat_info_service.dto.Status;
import seat_info_service.id.CityId;
import seat_info_service.id.SeatId;
import seat_info_service.id.ShowId;
import seat_info_service.service.SeatInfoService;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class SeatInfoController {

    private final SeatInfoService seatInfoService;

    public SeatInfoController(SeatInfoService seatInfoService) {
        this.seatInfoService = seatInfoService;
    }

    @GetMapping("/shows")
    public ResponseEntity<List<ShowDetail>> getShowsInfo(@RequestParam CityId cityId){
        return ResponseEntity.ok(seatInfoService.getListOfShows(cityId));
    }

    @GetMapping("/shows/{showId}/seats")
    public ResponseEntity<List<Seat>> getSeatsInfo(@PathVariable ShowId showId){
        return ResponseEntity.ok(seatInfoService.getSeatsForShow(showId));
    }

    @GetMapping("/shows/{showId}/seats/{seatId}")
    public ResponseEntity<Status> getSeatStatus(
            @PathVariable ShowId showId,
            @PathVariable SeatId seatId){
        return ResponseEntity.ok(seatInfoService.getStatusOfSeat(showId, seatId));
    }

    @PutMapping("/shows/{showId}/seats/{seatId}/book")
    public ResponseEntity<Void> bookSeat(
            @PathVariable ShowId showId,
            @PathVariable SeatId seatId){
         seatInfoService.bookSeat(seatId, showId);
         return ResponseEntity.noContent().build();
    }

}
