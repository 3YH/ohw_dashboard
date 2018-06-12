package com.yannickhj.ohwdash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yannickhj.ohwdash.model.Sign;

@Repository
public interface SignRepository extends JpaRepository<Sign, Long> {

}
