package com.example.demo.repository;

import com.example.demo.entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserBaseRepository<T extends User>
        extends JpaRepository<T, Long> {

    T findByEmail(String email);
    T findByLogin(String username);

}
