package com.prgr.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prgr.main.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff,Integer> {

	Staff findByUserNameAndPassword(String username,String password);
}
