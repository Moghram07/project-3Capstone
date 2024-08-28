package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.Fabric;
import com.example.capstone_3.Model.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Integer> {
    Shipping findShippingById(Integer id);

    List<Shipping> findShippingByStatus(String status);

}

