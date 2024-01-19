package com.example.transactions.dao;

import com.example.transactions.entity.StoreUserMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStoreUserMappingRepository extends JpaRepository<StoreUserMapping, Long> {
}
