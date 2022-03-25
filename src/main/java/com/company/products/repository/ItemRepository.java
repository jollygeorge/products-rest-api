package com.company.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.products.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
