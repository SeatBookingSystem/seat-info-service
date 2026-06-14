package seat_info_service.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import seat_info_service.id.SeatId;

@Component
public class SeatIdConverter implements Converter<String, SeatId> {

    @Override
    public SeatId convert(String value) {
        if (value == null || !value.startsWith("S")) {
            throw new IllegalArgumentException("Invalid SeatId");
        }
        return new SeatId(value);
    }


}
