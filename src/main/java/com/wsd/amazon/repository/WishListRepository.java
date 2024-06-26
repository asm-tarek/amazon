package com.wsd.amazon.repository;

import com.wsd.amazon.entity.WishList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
    Page<WishList> findByUserId(long userId, Pageable pageable);
}
