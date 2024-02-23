package com.kbtg.bootcamp.posttest.lottery;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(MockitoExtension.class)
class LotteryControllerTest {
    MockMvc mockMvc;
    @Mock
    LotteryService lotteryService;

    @BeforeEach
    void setUp() {
        LotteryController lotteryController = new LotteryController(lotteryService);
        mockMvc = MockMvcBuilders.standaloneSetup(lotteryController)
//                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("when fetch lottery  GET: lotteries should return status 200 and body" +
            " contain ticket numbers"
    )
    void fetchLottery() throws Exception {

        Lottery lottery1 = new Lottery();
        lottery1.setTicket("123456");
        when(lotteryService.getAllAvailableLotteries()).thenReturn(List.of(lottery1));
        mockMvc.perform(get("/lotteries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tickets[0]", is("123456")));
    }
}