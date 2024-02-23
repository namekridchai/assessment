package com.kbtg.bootcamp.posttest.userTicket;

import java.util.List;

public class UserTicketResponse {
	private List<String> ticketList;
	private int count;
	private int cost;

	public UserTicketResponse(List<String> ticketList, int count, int cost) {
		this.ticketList = ticketList;
		this.count = count;
		this.cost = cost;
	}

	public List<String> getTicketList() {
		return ticketList;
	}

	public int getCount() {
		return count;
	}

	public int getCost() {
		return cost;
	}
	public boolean equals(Object o) {
		UserTicketResponse u = (UserTicketResponse) o;
		// If the object is compared with itself then return true
		if ((u.getTicketList().equals(this.getTicketList())) &&
				u.getCost()==this.getCost() &&
				u.getCount()==this.getCount()
		) {
			return true;
		}
		return  false;


	}
}

