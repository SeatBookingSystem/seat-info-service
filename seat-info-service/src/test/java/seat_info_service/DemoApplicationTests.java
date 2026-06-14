package seat_info_service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import seat_info_service.dto.Seat;
import seat_info_service.dto.Status;
import seat_info_service.entity.SeatDocument;
import seat_info_service.id.SeatId;
import seat_info_service.id.ShowId;
import seat_info_service.repository.SeatInfoRepository;
import seat_info_service.service.SeatInfoService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DemoApplicationTests {

	@Mock
	private SeatInfoRepository seatInfoRepository;

	@InjectMocks
	private SeatInfoService seatInfoService;

	@Test
	void getSeatsForShow_shouldReturnSeats() {
		// given
		String showId = "SHOW:1";

		SeatDocument doc = new SeatDocument();
		doc.setSeatId("SEAT:1");
		doc.setShowId(showId);
		doc.setStatus(Status.AVAILABLE);
		doc.setPrice(200);

		when(seatInfoRepository.findByShowId(showId))
				.thenReturn(List.of(doc));

		// when
		List<Seat> result = seatInfoService.getSeatsForShow(new ShowId(showId));

		// then
		assertEquals(1, result.size());
		assertEquals("SEAT:1", result.get(0).getSeatId());
	}

	@Test
	void getStatusOfSeat_shouldReturnStatus() {
		// given
		String showId = "SHOW:1";
		String seatId = "SEAT:1";

		SeatDocument doc = new SeatDocument();
		doc.setSeatId(seatId);
		doc.setShowId(showId);
		doc.setStatus(Status.AVAILABLE);

		when(seatInfoRepository.findByShowIdAndSeatId(showId, seatId))
				.thenReturn(Optional.of(doc));

		// when
		Status status = seatInfoService.getStatusOfSeat(
				new ShowId(showId),
				new SeatId(seatId)
		);

		// then
		assertEquals(Status.AVAILABLE, status);
	}

	@Test
	void bookSeat_shouldUpdateStatus() {
		// given
		String showId = "SHOW:1";
		String seatId = "SEAT:1";

		// when
		seatInfoService.bookSeat(
				new SeatId(seatId),
				new ShowId(showId)
		);

		// then
		verify(seatInfoRepository)
				.updateStatusByShowIdAndSeatId(showId, seatId, Status.BOOKED);
	}

	@Test
	void bookSeat_shouldThrowIfAlreadyBooked() {
		// given
		String showId = "SHOW:1";
		String seatId = "SEAT:1";

		doThrow(new RuntimeException("Seat already booked"))
				.when(seatInfoRepository)
				.updateStatusByShowIdAndSeatId(showId, seatId, Status.BOOKED);

		// then
		assertThrows(RuntimeException.class, () ->
				seatInfoService.bookSeat(
						new SeatId(seatId),
						new ShowId(showId)
				)
		);
	}

}
