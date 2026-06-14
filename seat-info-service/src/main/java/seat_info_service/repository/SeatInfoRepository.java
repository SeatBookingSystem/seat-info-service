package seat_info_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import seat_info_service.entity.SeatDocument;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatInfoRepository extends MongoRepository<SeatDocument, String>, SeatInfoRepositoryCustom {

    List<SeatDocument> findByShowId(String showId);
    Optional<SeatDocument> findByShowIdAndSeatId(String showId, String seatId);
}
