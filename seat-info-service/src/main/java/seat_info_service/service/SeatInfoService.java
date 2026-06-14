package seat_info_service.service;

import org.springframework.stereotype.Service;
import seat_info_service.dto.Seat;
import seat_info_service.dto.ShowDetail;
import seat_info_service.dto.Status;
import seat_info_service.id.CityId;
import seat_info_service.id.SeatId;
import seat_info_service.id.ShowId;
import seat_info_service.repository.SeatInfoRepository;
import seat_info_service.repository.ShowRepository;

import java.util.List;

@Service
public class SeatInfoService {

    private final SeatInfoRepository seatInfoRepository;
    private final ShowRepository showRepository;

    public SeatInfoService(SeatInfoRepository seatInfoRepository, ShowRepository showRepository) {
        this.seatInfoRepository = seatInfoRepository;
        this.showRepository = showRepository;
    }

    public List<ShowDetail> getListOfShows(CityId cityId) {
        return showRepository.findByCityId(cityId.value()).stream().map(showDocument -> {
            ShowDetail showDetail = new ShowDetail();
            showDetail.setShowId(showDocument.getShowId());
            showDetail.setMovieName(showDocument.getMovieName());
            showDetail.setTheatre(showDocument.getTheatre());
            showDetail.setTime(showDocument.getTime());
            return showDetail;
        }).toList();
    }

    public List<Seat> getSeatsForShow(ShowId showId) {
        return seatInfoRepository.findByShowId(showId.value()).stream().map(seatDocument -> {
            Seat seat = new Seat();
            seat.setSeatId(seatDocument.getSeatId());
            seat.setStatus(seatDocument.getStatus());
            seat.setPrice(seatDocument.getPrice());
            return seat;
        }).toList();
    }

    public Status getStatusOfSeat(ShowId showId, SeatId seatId) {
        return seatInfoRepository
                .findByShowIdAndSeatId(showId.value(), seatId.value())
                .orElseThrow(() -> new RuntimeException("Seat not found"))
                .getStatus();
    }

    public void bookSeat(SeatId seatId, ShowId showId) {
        seatInfoRepository.updateStatusByShowIdAndSeatId(showId.value(), seatId.value(), Status.BOOKED);
    }
}
