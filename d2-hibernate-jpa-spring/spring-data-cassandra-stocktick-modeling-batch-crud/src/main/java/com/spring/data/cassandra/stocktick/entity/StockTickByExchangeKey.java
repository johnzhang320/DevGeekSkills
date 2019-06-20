package com.spring.data.cassandra.stocktick.entity;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@PrimaryKeyClass
public class StockTickByExchangeKey implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@PrimaryKeyColumn(name = "exchange", type = PrimaryKeyType.PARTITIONED)
	  private String exchange;
	
	  @PrimaryKeyColumn(name = "symbol", ordinal = 0, ordering = Ordering.ASCENDING)   // oridinal is order position in cluster key
	  private String symbol;
	
	  @PrimaryKeyColumn(name = "tick_date", ordinal = 1, ordering = Ordering.ASCENDING)
	  private String tickDate;

	public StockTickByExchangeKey(String exchange, String symbol, String tickDate) {
		super();
		this.exchange = exchange;
		this.symbol = symbol;
		this.tickDate = tickDate;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getTickDate() {
		return tickDate;
	}

	public void setTickDate(String tickDate) {
		this.tickDate = tickDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "StockTickByExchangeKey [exchange=" + exchange + ", symbol=" + symbol + ", tickDate=" + tickDate + "]";
	}

	 
	
}
