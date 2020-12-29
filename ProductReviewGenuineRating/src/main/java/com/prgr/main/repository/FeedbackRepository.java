package com.prgr.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prgr.main.entity.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback,Integer> {

}
