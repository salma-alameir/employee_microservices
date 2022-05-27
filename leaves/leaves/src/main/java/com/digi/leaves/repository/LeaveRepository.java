package com.digi.leaves.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digi.leaves.model.Leave;

public interface LeaveRepository extends JpaRepository<Leave, Integer> {

}
