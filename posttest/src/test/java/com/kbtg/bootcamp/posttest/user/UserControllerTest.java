package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.lottery.LotteryService;
import com.kbtg.bootcamp.posttest.userTicket.UserTicketResponse;
import com.kbtg.bootcamp.posttest.userTicket.UserTicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(MockitoExtension.class)
class UserControllerTest {
	MockMvc mockMvc;
	@Mock
	LotteryService lotteryService;

	@Mock
	UserTicketService userTicketService;

	@BeforeEach
	void setUp() {
		UserController userController = new UserController(lotteryService,userTicketService);
		mockMvc = MockMvcBuilders.standaloneSetup(userController)
//                .alwaysDo(print())
				.build();
	}

	@Test
	@DisplayName("when fetch lottery  GET: /users/:userid/lotteries should return status 200 and body" +
			" contain ticket numbers , total price, total amount"
	)
	void fetchUserTicket() throws Exception {

		Lottery lottery1 = new Lottery();
		lottery1.setTicket("123456");
		UserTicketResponse userTicketResponse = new UserTicketResponse(List.of("123456"),1,80);
		when(userTicketService.getUserTicket("9999999999")).thenReturn(userTicketResponse);
		mockMvc.perform(get("/users/9999999999/lotteries"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.ticketList[0]", is("123456")))
				.andExpect(jsonPath("$.count", is(1)))
				.andExpect(jsonPath("$.cost", is(80)));
	}

	@Test
	@DisplayName("when POST lottery  POST: /users/:userid/lotteries/:lotteryid should return status 201 and body" +
			" contain user ticket id"
	)
	void buyLottery() throws Exception {


		when(lotteryService.buyLottery("9999999999","123456")).thenReturn("1");
		mockMvc.perform(post("/users/9999999999/lotteries/123456"))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id", is("1")));

	}

	@Test
	@DisplayName("when POST lottery  POST: /users/:userid/lotteries/:lotteryid should return status 202 and body" +
			" contain ticket number"
	)
	void sellLottery() throws Exception {
		mockMvc.perform(delete("/users/9999999999/lotteries/123456"))
				.andExpect(status().isAccepted())
				.andExpect(jsonPath("$.ticket", is("123456")));

	}


}