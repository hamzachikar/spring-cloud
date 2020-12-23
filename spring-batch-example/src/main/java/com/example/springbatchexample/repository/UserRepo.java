package com.example.springbatchexample.repository;

import com.example.springbatchexample.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
