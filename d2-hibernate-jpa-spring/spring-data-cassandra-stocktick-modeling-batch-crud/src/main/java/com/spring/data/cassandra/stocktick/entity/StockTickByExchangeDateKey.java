package com.spring.data.cassandra.stocktick.entity;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@PrimaryKeyClass
public class StockTickByExchangeDateKey implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@PrimaryKeyColumn(name = "exchange",ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	  private String exchange;
	  
	  @PrimaryKeyColumn(name = "tick_date", ordinal = 1,type = PrimaryKeyType.PARTITIONED)
	  private String tickDate;
	
	  @PrimaryKeyColumn(name = "symbol",  ordering = Ordering.ASCENDING)   // oridinal is order position in cluster key
	  private String symbol;

	public StockTickByExchangeDateKey(String exchange, String tickDate, String symbol) {
		super();
		this.exchange = exchange;
		this.tickDate = tickDate;
		this.symbol = symbol;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getTickDate() {
		return tickDate;
	}

	public void setTickDate(String tickDate) {
		this.tickDate = tickDate;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "StockTickByExchangeDateKey [exchange=" + exchange + ", tickDate=" + tickDate + ", symbol=" + symbol
				+ "]";
	}
	
	 

	 

}
