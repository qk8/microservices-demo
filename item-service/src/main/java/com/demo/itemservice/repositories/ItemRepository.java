package com.demo.itemservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.itemservice.entities.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{

}
