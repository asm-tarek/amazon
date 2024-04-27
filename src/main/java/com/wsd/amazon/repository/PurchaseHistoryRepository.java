package com.wsd.amazon.repository;

import com.wsd.amazon.entity.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {

    @Query(nativeQuery = true, value = "SELECT SUM(total_amount) AS TOTAL_SALES\n" +
            "FROM PURCHASE_HISTORY\n" +
            "WHERE PURCHASE_DATE = CURRENT_DATE\n" +
            "GROUP BY PURCHASE_DATE;")
    BigDecimal getCurrentDayTotalSaleAmount();

    @Query(nativeQuery = true, value = "SELECT PURCHASE_DATE\n" +
            "FROM PURCHASE_HISTORY\n" +
            "WHERE PURCHASE_DATE BETWEEN :fromDate AND :toDate\n" +
            "GROUP BY PURCHASE_DATE\n" +
            "ORDER BY SUM(TOTAL_AMOUNT) DESC\n" +
            "LIMIT 1;")
    Date getTopSaleDay(Date fromDate, Date toDate);

    @Query(nativeQuery = true, value = "SELECT PRODUCT_ID\n" +
            "FROM PURCHASE_HISTORY\n" +
            "GROUP BY PRODUCT_ID\n" +
            "ORDER BY SUM(TOTAL_AMOUNT) DESC\n" +
            "LIMIT 5;")
    List<Long> getAllTimeTop5SellingItem();

    @Query(nativeQuery = true, value = "SELECT PRODUCT_ID\n" +
            "FROM PURCHASE_HISTORY\n" +
            "WHERE PURCHASE_DATE >= DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month'\n" +
            "GROUP BY PRODUCT_ID\n" +
            "ORDER BY SUM(count) DESC\n" +
            "LIMIT 5;")
    List<Long> getLastMonthTop5SellingItem();
}
