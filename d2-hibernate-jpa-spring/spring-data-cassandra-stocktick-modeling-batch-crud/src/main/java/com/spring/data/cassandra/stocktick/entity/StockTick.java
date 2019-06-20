package com.spring.data.cassandra.stocktick.entity;

import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import com.spring.data.cassandra.symbol.entity.StockSymbol;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
/*
 *  Cassandra originated table
 */
	@Table("stock_ticks")
	public class StockTick {
	
	  @PrimaryKeyColumn(name = "stock_tick_id", type = PrimaryKeyType.PARTITIONED)
	  private UUID id;
	
 	  
	  private String symbol;
	  
	  private String description;
	  
	  private String exchange;
	
	  @Column 
	  private String tickDate;
	   
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

	public StockTick(UUID id, String symbol, String description, String exchange, String tickDate, Float open,
			Float high, Float low, Float close, Long volume) {
		super();
		this.id = id;
		this.symbol = symbol;
		this.description = description;
		this.exchange = exchange;
		this.tickDate = tickDate;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		return "StockTick [id=" + id + ", symbol=" + symbol + ", description=" + description + ", exchange=" + exchange
				+ ", tickDate=" + tickDate + ", open=" + open + ", high=" + high + ", low=" + low + ", close=" + close
				+ ", volume=" + volume + "]";
	}

}
