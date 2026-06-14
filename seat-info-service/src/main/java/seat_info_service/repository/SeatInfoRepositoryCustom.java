package seat_info_service.repository;

import seat_info_service.dto.Status;

public interface SeatInfoRepositoryCustom {

    void updateStatusByShowIdAndSeatId(String showId, String seatId, Status status);
}
