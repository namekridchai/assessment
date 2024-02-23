package com.kbtg.bootcamp.posttest.lottery;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


public  record LotteryRequest(@NotNull
							@Pattern(regexp="[\\d]{6}")
							String ticket,
							@NotNull
							int price,
							@NotNull
							int amount){

}