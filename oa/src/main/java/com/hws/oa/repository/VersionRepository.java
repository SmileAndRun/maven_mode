package com.hws.oa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hws.oa.model.VersionModel;

@Repository  
public interface VersionRepository extends JpaRepository<VersionModel, Long>{

}