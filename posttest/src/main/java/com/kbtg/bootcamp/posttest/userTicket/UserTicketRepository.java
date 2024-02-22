package com.kbtg.bootcamp.posttest.userTicket;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserTicketRepository extends JpaRepository<UserTicket,Long> {
    List<UserTicket> findByuserId(String user_id);
}
