package com.tutorial.springfundamental.repository;

import com.tutorial.springfundamental.entity.Keyboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface KeyboardRepository extends JpaRepository<Keyboard, UUID> {

}
