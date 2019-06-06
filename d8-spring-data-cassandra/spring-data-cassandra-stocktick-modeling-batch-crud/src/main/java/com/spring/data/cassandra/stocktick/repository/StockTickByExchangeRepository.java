package com.spring.data.cassandra.stocktick.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.data.cassandra.stocktick.entity.StockTickByExchange;
import com.spring.data.cassandra.stocktick.entity.StockTickByExchangeKey;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface StockTickByExchangeRepository extends CassandraRepository<StockTickByExchange, StockTickByExchangeKey> {
  /**
   *  constractor StockTickByExchangeKey(String exchange, String symbol, String tick_date) request exhange, symbol and tick_date
   *  therefore the findBy function signature must contain those three parameter names by adding prefix Key+Parameter and 'And'
   *  I previously use findByKeyExchangAndKeySymbolAndKeyTick_Date, Exchang missed 'e', when I run the code, keep Exception
   *  BTW
   *  Repository does not recognize under score _ as function name , do not use tick_date, use tickDate only !!!!
   *  
   *  No property exchang found for type StockTickByExchangeKey! 
		Did you mean 'exchange'? Traversed path: StockTickByExchange.key.
		
   *  Until I added 'e' to findByKeyExchangeAndKeySymbolAndKeyTick_Date, 
   */
  @Query(allowFiltering = true)
  public List<StockTickByExchange> findByKeyExchangeAndKeySymbolAndKeyTickDate(String exchange, String symbol,String tickDate);
}