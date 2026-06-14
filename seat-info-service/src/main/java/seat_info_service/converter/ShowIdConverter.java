package seat_info_service.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import seat_info_service.id.ShowId;

@Component
public class ShowIdConverter implements Converter<String, ShowId> {

    @Override
    public ShowId convert(String value) {
        if (value == null || !value.startsWith("SHOW")) {
            throw new IllegalArgumentException("Invalid ShowId");
        }
        return new ShowId(value);
    }
}
