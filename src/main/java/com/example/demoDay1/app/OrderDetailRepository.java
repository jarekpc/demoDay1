package com.example.demoDay1.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    @Query(value = "select ifnull(max(o.id),0) from order_details o", nativeQuery = true)
    Long getMaxId();

    @Query(value = "select * from order_details o where o.order_id:id", nativeQuery = true)
    List<OrderDetail> getOrderDetailsByOrderId(@Param("id") Long id);

}
