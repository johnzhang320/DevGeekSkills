package com.spring.data.cassandra.stocktick.repository;

import java.util.UUID;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.spring.data.cassandra.stocktick.entity.StockTick;

@NoRepositoryBean
public interface StockTickRepository extends CassandraRepository<StockTick, UUID> {
  
 // public StockTick insert(StockTick stick);
}