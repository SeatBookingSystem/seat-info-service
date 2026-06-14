package seat_info_service.repository;

import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import seat_info_service.dto.Status;
import seat_info_service.entity.SeatDocument;

public class SeatInfoRepositoryImpl implements SeatInfoRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    public SeatInfoRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void updateStatusByShowIdAndSeatId(String showId, String seatId, Status status) {
        Query query = new Query();
        query.addCriteria(
                Criteria.where("showId").is(showId)
                        .and("seatId").is(seatId)
                        .and("status").ne(Status.BOOKED)
        );

        Update update = new Update();
        update.set("status", status);

        UpdateResult result = mongoTemplate.updateFirst(query, update, SeatDocument.class);

        if (result.getMatchedCount() == 0) {
            throw new RuntimeException("Seat not found or already booked");
        }
    }
}
