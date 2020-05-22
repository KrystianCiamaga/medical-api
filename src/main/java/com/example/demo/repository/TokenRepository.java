package com.example.demo.repository;

import com.example.demo.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {

    Token findByValue(String value);

}
