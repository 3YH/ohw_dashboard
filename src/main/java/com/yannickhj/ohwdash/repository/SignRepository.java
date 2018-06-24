package com.yannickhj.ohwdash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yannickhj.ohwdash.model.Sign;
//Spring JPA(Java Persistence API) zorgt voort persistentie en bevat CRUD methoden zoals de findAll/save(gebruikt in controller)
@Repository
public interface SignRepository extends JpaRepository<Sign, Long> {

}
