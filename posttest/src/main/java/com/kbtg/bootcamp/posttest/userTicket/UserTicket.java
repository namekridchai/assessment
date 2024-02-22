package com.kbtg.bootcamp.posttest.userTicket;

import com.kbtg.bootcamp.posttest.lottery.Lottery;
import jakarta.persistence.*;

@Entity
@Table(name = "user_ticket")
public class UserTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;
    @Column(name = "user_id")
    private String userId;
    @ManyToOne
    @JoinColumn(name = "ticket")
    private Lottery lottery;
    private int price;

    public UserTicket(String userId, int pricePerQty) {
        this.userId = userId;
        this.price = pricePerQty;
    }

    public UserTicket(){

    }

    public void setLottery(Lottery lottery) {
        this.lottery = lottery;
    }
    public Integer getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public Lottery getLottery() {
        return lottery;
    }
}
