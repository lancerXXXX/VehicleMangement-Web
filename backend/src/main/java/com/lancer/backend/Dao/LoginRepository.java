package com.lancer.backend.Dao;


import com.lancer.backend.entity.Login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login,String>{
    
}