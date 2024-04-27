package com.wsd.amazon;

import com.wsd.amazon.repository.PurchaseHistoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class PurchaseHistoryTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PurchaseHistoryRepository purchaseHistoryRepository;

    @Test
    void shouldBeAbleToReturnCurrentDayTotalSaleAmount() throws Exception {
        //repository check
        BigDecimal currentDayTotalSaleAmount = purchaseHistoryRepository.getCurrentDayTotalSaleAmount();
        assertTrue(currentDayTotalSaleAmount.compareTo(BigDecimal.valueOf(0)) > 0);

        //controller check
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/product/today/total-sale"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.hasError", is(false)))
                .andExpect(jsonPath("$.data").exists());
    }

    @Test
    void shouldBeAbleToReturnTopSaleDayInAGivenTimePeriod() throws Exception {
        //repository check
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate = formatter.parse("2024-04-25");
        Date toDate = formatter.parse("2025-04-25");
        Date topSaleDay = purchaseHistoryRepository.getTopSaleDay(fromDate, toDate);
        assertTrue(topSaleDay != null);

        //controller check
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/product/top-sale-day?fromDate=2024-04-25&toDate=2025-04-25"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.hasError", is(false)))
                .andExpect(jsonPath("$.data").exists());
    }
}
