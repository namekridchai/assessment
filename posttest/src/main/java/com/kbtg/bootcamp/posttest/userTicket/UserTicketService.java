package com.kbtg.bootcamp.posttest.userTicket;

import com.kbtg.bootcamp.posttest.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserTicketService {
	private UserTicketRepository userTicketRepository;

	public UserTicketService(UserTicketRepository userTicketRepository) {
		this.userTicketRepository = userTicketRepository;
	}

	public UserTicketResponse getUserTicket(String user_id){
		List<UserTicket> userTicketList = userTicketRepository.findByuserId(user_id);

		if (userTicketList.isEmpty())
			throw new UserNotFoundException("user specific id is not found");

		int totalPrice = 0;
		int ticketCount =  userTicketList.size();
		List<String> ticketList = new ArrayList<String>();

		for (int i = 0; i < ticketCount; i++) {
			UserTicket userTicket = userTicketList.get(i);
			totalPrice+=userTicket.getPrice();
			ticketList.add(userTicket.getLottery().getTicket());
		}

		UserTicketResponse userTicketResponse = new UserTicketResponse(ticketList,ticketCount,totalPrice);
		return userTicketResponse;

	}
}
