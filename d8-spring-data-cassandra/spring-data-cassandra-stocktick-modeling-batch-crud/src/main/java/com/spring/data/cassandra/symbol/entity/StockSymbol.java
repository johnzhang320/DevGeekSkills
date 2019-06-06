package com.spring.data.cassandra.symbol.entity;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

/*
 * create table symbol_by_stocktick
	symbol			varchar	primary key,
	description		varchar,
	exchange		varchar
);
  Even symbol is unique , only one column , we must create Serializable Class for this one column
  whenever insert one stock_tick record , check if symbol exists in stock_symbol, not exists, add one 
 */
 
public class StockSymbol {
 	 
 
	private String symbol;         
	 
	private String description;
 
	private String exchange;

	public StockSymbol(String symbol, String description, String exchange) {
		super();
		this.symbol = symbol;
		this.description = description;
		this.exchange = exchange;
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

	@Override
	public String toString() {
		return "StockSymbol [symbol=" + symbol + ", description=" + description + ", exchange=" + exchange + "]";
	}
	
	
	 
}
