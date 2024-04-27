package com.wsd.amazon;

import com.wsd.amazon.utils.FileUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void createSuccessfulProduct() throws Exception {
        String productStr = FileUtil.readFromFileToString("jsons/ValidProductData.json");
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/product")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(productStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.hasError", is(false)))
                .andExpect(jsonPath("$.data.id").exists()
                );
    }

    @Test
    void successfulPurchase() throws Exception {
        String productStr = FileUtil.readFromFileToString("jsons/ValidPurchaseData.json");
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/product/purchase")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(productStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.hasError", is(false)))
                .andExpect(jsonPath("$.data.id").exists()
                );
    }

    @Test
    void successfulAddToWishList() throws Exception {
        String productStr = FileUtil.readFromFileToString("jsons/ValidWishListData.json");
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/product/wishlist")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(productStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.hasError", is(false)))
                .andExpect(jsonPath("$.data.id").exists()
                );
    }
}
