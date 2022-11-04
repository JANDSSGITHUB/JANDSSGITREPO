package com.demo.DSS5MSREVIEW.repository;

import com.demo.DSS5MSREVIEW.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
}
