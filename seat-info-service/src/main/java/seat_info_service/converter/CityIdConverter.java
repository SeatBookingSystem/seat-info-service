package seat_info_service.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import seat_info_service.id.CityId;

@Component
public class CityIdConverter implements Converter<String, CityId> {

    @Override
    public CityId convert(String value) {
        if (!value.startsWith("CITY:")) {
            throw new IllegalArgumentException("Invalid CityId");
        }
        return new CityId(value);
    }
}

//58 26 16
//1 mutual fund only