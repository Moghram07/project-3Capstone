package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.Customer;
import com.example.capstone_3.Model.Fabric;
import com.example.capstone_3.Model.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findOrderById(Integer id);

    List<Order> findOrderByCustom(Customer customer);

    List<Order> findOrderByOrderStatus(String orderStatus);

    @Query("SELECT o.fabric FROM Order o GROUP BY o.fabric.id ORDER BY COUNT(o.id) DESC")
    List<Fabric> findBestSellingFabric(Pageable pageable);
}
