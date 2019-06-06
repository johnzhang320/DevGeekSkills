package com.spring.data.cassandra.stocktick.entity;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Set;
/**
 *  Query B Cassadra Table
 *  create table stock_tick_by_exchange
	exchange		varchar,
	symbol			varchar,
	description		varchar,
	tick_date		varchar,
	close			decimal,
	primary key(exchange, symbol, tick_date)
);
 */
@Table("stock_tick_by_exchanges")
public class StockTickByExchange {

	  @PrimaryKey 
	  private StockTickByExchangeKey key;
	
	  @Column 
	  private String description;
	  
	  @Column 
	  private Float close;

	public StockTickByExchange(StockTickByExchangeKey key, String description, Float close) {
		super();
		this.key = key;
		this.description = description;
		this.close = close;
	}

	public StockTickByExchangeKey getKey() {
		return key;
	}

	public void setKey(StockTickByExchangeKey key) {
		this.key = key;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getClose() {
		return close;
	}

	public void setClose(Float close) {
		this.close = close;
	}

	@Override
	public String toString() {
		return "StockTickByExchange [key=" + key + ", description=" + description + ", close=" + close + "]";
	}
	  
 
}
