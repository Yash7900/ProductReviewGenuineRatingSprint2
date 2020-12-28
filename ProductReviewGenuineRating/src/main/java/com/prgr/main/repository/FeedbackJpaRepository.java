package com.prgr.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prgr.main.entity.Feedback;

public interface FeedbackJpaRepository extends JpaRepository<Feedback,Integer> {

}
