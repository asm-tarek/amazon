package com.wsd.amazon.repository;

import com.wsd.amazon.entity.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;

@Repository
public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {

    @Query(nativeQuery = true, value = "SELECT SUM(total_amount) AS TOTAL_SALES\n" +
            "FROM PURCHASE_HISTORY\n" +
            "WHERE PURCHASE_DATE = CURRENT_DATE\n" +
            "GROUP BY PURCHASE_DATE;")
    BigDecimal getCurrentDayTotalSaleAmount();

    @Query(nativeQuery = true, value = "SELECT PURCHASE_DATE\n" +
            "FROM (\n" +
            "    SELECT PURCHASE_DATE, SUM(TOTAL_AMOUNT) AS TOTAL_SALES\n" +
            "    FROM PURCHASE_HISTORY\n" +
            "    WHERE PURCHASE_DATE BETWEEN :fromDate AND :toDate\n" +
            "    GROUP BY PURCHASE_DATE\n" +
            "    ORDER BY TOTAL_SALES DESC\n" +
            "    LIMIT 1\n" +
            ") AS SUBQUERY;")
    Date getTopSaleDay(Date fromDate, Date toDate);
}
