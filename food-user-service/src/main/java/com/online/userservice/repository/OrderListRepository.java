package com.online.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online.userservice.entity.OrderList;

@Repository
public interface OrderListRepository extends JpaRepository<OrderList, Integer>{

}
