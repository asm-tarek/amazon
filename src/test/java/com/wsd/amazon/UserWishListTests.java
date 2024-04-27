package com.wsd.amazon;

import com.wsd.amazon.entity.WishList;
import com.wsd.amazon.repository.WishListRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserWishListTests {
    @Autowired
    private WishListRepository wishListRepository;

    @Test
    void getWishListProductsOfAUser() throws Exception {

        Pageable pageable = PageRequest.of(0, 10);

        Page<WishList> result = wishListRepository.findByUserId(1, pageable);

        assertFalse(result.getContent().isEmpty());

        assertTrue(result.getContent().getFirst().getUserId() > 0);

        assertNotNull(result.getContent().getFirst().getProduct());
    }
}
