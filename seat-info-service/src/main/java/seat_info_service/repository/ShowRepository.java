package seat_info_service.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import seat_info_service.entity.ShowDocument;

import java.util.List;

public interface ShowRepository extends ElasticsearchRepository<ShowDocument, String> {

    List<ShowDocument> findByCityId(String cityId);
}
