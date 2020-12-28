package com.prgr.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prgr.main.entity.Review;

public interface ReviewJpaRepository extends JpaRepository<Review,Integer> {

}
