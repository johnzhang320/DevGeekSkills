package com.spring.data.cassandra.stocktick.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.spring.data.cassandra.stocktick.entity.StockTickByExchange;
import com.spring.data.cassandra.stocktick.entity.StockTickByExchangeDate;
import com.spring.data.cassandra.stocktick.entity.StockTickByExchangeDateKey;

import java.util.List;
 

@Repository
public interface StockTickByExchangeDateRepository extends CassandraRepository<StockTickByExchangeDate, StockTickByExchangeDateKey> {
 
  public List<StockTickByExchangeDate> findByKeyExchangeAndKeyTickDateAndKeySymbol(String exchange, String tickDate,String symbol);
}