package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.lottery.LotteryService;
import com.kbtg.bootcamp.posttest.userTicket.UserTicketResponse;
import com.kbtg.bootcamp.posttest.userTicket.UserTicketService;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

	private LotteryService lotteryService;
	private UserTicketService userTicketService;


	public UserController(LotteryService lotteryService,UserTicketService userTicketService) {
		this.lotteryService = lotteryService;
		this.userTicketService = userTicketService;
	}

	@PostMapping("/{userId}/lotteries/{ticketId}")
	public ResponseEntity<Object>  buyLottery(
												@Validated
												@Pattern(regexp="[\\d]{10}")
												@PathVariable("userId")  String user_id,

												@Validated
												@Pattern(regexp="[\\d]{6}")
												@PathVariable("ticketId") String ticket_id){
		String user_ticket_id  = lotteryService.buyLottery(user_id, ticket_id);
		Map<String,String> data = new HashMap<>();
		data.put("id", user_ticket_id);
		return new ResponseEntity<>(data, HttpStatus.CREATED);
	}

	@DeleteMapping("/{userId}/lotteries/{ticketId}")
	public ResponseEntity<Object>  sellLottery(   @Validated
												@Pattern(regexp="[\\d]{10}")
												@PathVariable("userId")  String user_id,

												@Validated
												@Pattern(regexp="[\\d]{6}")
												@PathVariable("ticketId") String ticket_id){
		lotteryService.sellLottery(user_id, ticket_id);
		Map<String,String> data = new HashMap<>();
		data.put("ticket", ticket_id);
		return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
	}

	@GetMapping("/{userId}/lotteries")
	public ResponseEntity<Object>  buyLottery(
												@Validated
												@Pattern(regexp="[\\d]{10}")
												@PathVariable("userId") String user_id
											){
		UserTicketResponse userTicketResponse  = userTicketService.getUserTicket(user_id);
		return new ResponseEntity<>(userTicketResponse, HttpStatus.OK);
	}

}
