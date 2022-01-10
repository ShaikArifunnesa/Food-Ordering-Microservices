package com.online.orderservice.Repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.online.orderservice.entity.OrderInfo;

@Repository
public interface OrderInfoRepository extends JpaRepository<OrderInfo, Integer> {

	@Query("SELECT t FROM OrderInfo t WHERE (t.date BETWEEN :startDate AND :endDate) AND (t.userId = :userId)")
    List<OrderInfo> getByDateAndUserId(@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate,@Param("userId") Integer userId);
	
	@Query("SELECT t FROM OrderInfo t WHERE MONTH(t.date) = MONTH(now()) AND (t.userId = :userId)")
    List<OrderInfo> getByMonthAndUserId(@Param("userId") Integer userId);
	
}
