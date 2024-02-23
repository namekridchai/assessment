package com.kbtg.bootcamp.posttest.userTicket;

import com.kbtg.bootcamp.posttest.lottery.Lottery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class UserTicketServiceTest {

	@Mock
	UserTicketRepository userTicketRepository;

	@Test
	@DisplayName("when call method getUserTicket should return correct ticket number,total count"+
			"and total cost"
	)
	void getUserTicket() throws Exception {
		UserTicketService userTicketService = new UserTicketService(userTicketRepository);
		String userId = "999999999";
		Lottery lottery = new Lottery();
		lottery.setTicket("123456");

		UserTicket userTicket1 = new UserTicket(userId,80);
		userTicket1.setLottery(lottery);

		UserTicket userTicket2 = new UserTicket(userId,80);
		userTicket2.setLottery(lottery);

		when(userTicketRepository.findByuserId(userId)).thenReturn(List.of(userTicket1,userTicket2));
		UserTicketResponse expected = new UserTicketResponse(List.of("123456","123456"),2,160);

		UserTicketResponse userTicketResponse  =  userTicketService.getUserTicket(userId);

		assertEquals(expected,userTicketResponse);
	}

}