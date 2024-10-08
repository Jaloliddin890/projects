package com.tmrv.SecurityJWT.repository;

import com.tmrv.SecurityJWT.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
