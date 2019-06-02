package com.hws.oa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hws.oa.model.LogModel;


public interface MyLogRepository extends JpaRepository<LogModel, Long>{

}
