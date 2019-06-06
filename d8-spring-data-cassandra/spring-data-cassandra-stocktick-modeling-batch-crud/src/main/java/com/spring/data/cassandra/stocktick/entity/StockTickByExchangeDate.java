package com.spring.data.cassandra.stocktick.entity;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Set;
/**
 * 	Query C
 *  Cassandra Table
 *  create table stock_tick_by_exchange_date
	exchange		varchar,	
	symbol			varchar,
	description		varchar,
	tick_date		varchar,
 	open			decimal,
	high			decimal,
	low				decimal,
	close			decimal,
	volume			bigint,
	primary key((exchange,tick_date), symbol)
);

 */
@Table("stock_tick_by_exchange_dates")
public class StockTickByExchangeDate {

	  @PrimaryKey 
	  private StockTickByExchangeDateKey key;
 
	  @Column 
	  private String description;
 	   
	  @Column 
	  private Float open;
	  
	  @Column 
	  private Float high;
	  
	  @Column 
	  private Float low;
	  
	  
	  @Column 
	  private Float close;
	  
	  @Column 
	  private Long volume;

	public StockTickByExchangeDate(StockTickByExchangeDateKey key, String description, Float open, Float high,
			Float low, Float close, Long volume) {
		super();
		this.key = key;
		this.description = description;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
	}

	public StockTickByExchangeDateKey getKey() {
		return key;
	}

	public void setKey(StockTickByExchangeDateKey key) {
		this.key = key;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getOpen() {
		return open;
	}

	public void setOpen(Float open) {
		this.open = open;
	}

	public Float getHigh() {
		return high;
	}

	public void setHigh(Float high) {
		this.high = high;
	}

	public Float getLow() {
		return low;
	}

	public void setLow(Float low) {
		this.low = low;
	}

	public Float getClose() {
		return close;
	}

	public void setClose(Float close) {
		this.close = close;
	}

	public Long getVolume() {
		return volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

	@Override
	public String toString() {
		return "StockTickByExchangeDate [key=" + key + ", description=" + description + ", open=" + open + ", high="
				+ high + ", low=" + low + ", close=" + close + ", volume=" + volume + "]";
	}

	  
	  
 
}
