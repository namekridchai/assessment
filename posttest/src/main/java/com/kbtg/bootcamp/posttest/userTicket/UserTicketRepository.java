package com.kbtg.bootcamp.posttest.userTicket;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserTicketRepository extends JpaRepository<UserTicket,Long> {
    List<UserTicket> findByuserId(String user_id);
    @Query(value = "select * from user_ticket  where user_id = :userid and ticket = :ticket",
            nativeQuery = true)
    List<UserTicket> findByUserIdAndTicket( @Param("userid") String userId,
                                            @Param("ticket") String ticket);
    @Modifying
    @Query(value = "delete from user_ticket  where user_id = :userid and ticket = :ticket",
            nativeQuery = true)
    void deleteByUserIdAndTicket(@Param("userid") String userId,
                                 @Param("ticket") String ticket);
}
