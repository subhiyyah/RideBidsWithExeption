package com.example.drivers.Repository;

import com.example.drivers.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository <User,Integer> {

    User findUserById(Integer Id);



}