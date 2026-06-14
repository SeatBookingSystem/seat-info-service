package seat_info_service.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Document(indexName = "shows")
@Data
public class ShowDocument {

    @Id
    private String id;

    private String showId;
    private String movieName;
    private String theatre;
    private String cityId;

    @Field(
            type = FieldType.Date,
            format = {},
            pattern = "uuuu-MM-dd'T'HH:mm:ss"
    )
    private LocalDateTime time;
}
