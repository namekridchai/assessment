package com.kbtg.bootcamp.posttest.admin;

import com.kbtg.bootcamp.posttest.lottery.LotteryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {
    MockMvc mockMvc;
    @Mock
    LotteryService lotteryService;

    @BeforeEach
    void setUp() {
        AdminController adminController = new AdminController(lotteryService);
        mockMvc = MockMvcBuilders.standaloneSetup(adminController)
//                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("when create lottery  POST: /admin/lotteries should return status 201 and body" +
                " contain ticket number"
    )
    void addLottery() throws Exception {

        mockMvc.perform(
                        post("/admin/lotteries")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON)
                                .content("{\"ticket\":\"123456\"," +
                                        "\"price\":80," +
                                        "\"amount\":1" +
                                        "}"
                                )
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ticket", is("123456")));
    }
}