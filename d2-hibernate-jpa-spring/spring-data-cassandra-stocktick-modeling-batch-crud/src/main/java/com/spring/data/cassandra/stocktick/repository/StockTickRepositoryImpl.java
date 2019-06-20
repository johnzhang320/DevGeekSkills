package com.spring.data.cassandra.stocktick.repository;

 
import com.spring.data.cassandra.stocktick.entity.*;

 

import org.springframework.data.cassandra.core.CassandraBatchOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
 
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.SimpleCassandraRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StockTickRepositoryImpl extends SimpleCassandraRepository<StockTick, UUID>
    implements StockTickRepository {

	  private final CassandraTemplate cassandraTemplate;
	  final StockTickByExchangeRepository sockTickByExchangeRepository;
	  final StockTickByExchangeDateRepository sockTickByExchangeDateRepository;
 
	  
	
	
	  public StockTickRepositoryImpl(
	      final CassandraEntityInformation<StockTick, UUID> metadata,
	      final CassandraTemplate cassandraTemplate,
	      final StockTickByExchangeRepository sockTickByExchangeRepository,
	      final StockTickByExchangeDateRepository sockTickByExchangeDateRepository 
		) {
	    super(metadata, cassandraTemplate);
	    this.cassandraTemplate = cassandraTemplate;
	    this.sockTickByExchangeRepository = sockTickByExchangeRepository;
	    this.sockTickByExchangeDateRepository = sockTickByExchangeDateRepository;
 
	  }
	
	  @Override
	  public <S extends StockTick> S insert(final S stockTick) {
	    stockTick.setId(UUID.randomUUID());
	    final CassandraBatchOperations batchOps = cassandraTemplate.batchOps();
	    insertByExchange(stockTick, batchOps);
	    insertByExchangeDate(stockTick, batchOps);
	 
	    batchOps.insert(stockTick);
	    batchOps.execute();
	    return stockTick;
	  }
	  //StockTickByExchangeKey(String exchange, String symbol, String tick_date) 
	  //public StockTickByExchange(StockTickByExchangeKey key, String description, Float close) 
	  // public StockTickByExchangeDateKey(String exchange, String symbol, String tick_date)
	  // public StockTickByExchangeDate(StockTickByExchangeDateKey key, String description, Float open, Float high, Float low, Float close, Long volume)
	  private void insertByExchange(final StockTick stockTick, final CassandraBatchOperations batchOps) {
		  
	      batchOps.insert(
	          new StockTickByExchange(
	              new StockTickByExchangeKey(
	              	  stockTick.getExchange(),
	            	  stockTick.getSymbol(),
	                  stockTick.getTickDate()),
	                  stockTick.getDescription(),
	              	  stockTick.getClose()
	               ));
	  }
	  
	  // public StockTickByExchangeDate(StockTickByExchangeDateKey key, String description, Float open, Float high, Float low, Float close, Long volume)
	  //	public StockTickByExchangeDateKey(String exchange, String tickDate, String symbol) {
	  private void insertByExchangeDate(final StockTick stockTick, final CassandraBatchOperations batchOps) {
	     
	      batchOps.insert(
	          new StockTickByExchangeDate(
	              new StockTickByExchangeDateKey(
	            		  stockTick.getExchange(),
	            		  stockTick.getTickDate(),
	                	  stockTick.getSymbol()
 	                    ),
	              stockTick.getDescription(),
	              stockTick.getOpen(),
	              stockTick.getHigh(),
	              stockTick.getLow(),
	              stockTick.getClose(),
	              stockTick.getVolume()
	             ));
	  	}
	  
	/*  private void insertStockSymbolTable(final StockTick stockTick, final CassandraBatchOperations batchOps) {
		 
	      batchOps.insert(
	    		  UUID.randomUUID(),
	    		  stockTick.getSymbol(),
 	      		  stockTick.getDescription(),
	      		  stockTick.getExchange()
	      );
		   
	  }*/

	  @Override
	  public void delete(final StockTick stockTick) {
	    final CassandraBatchOperations batchOps = cassandraTemplate.batchOps();
	    deleteByExchange(stockTick, batchOps);
	    deleteByExchangeDate(stockTick, batchOps);
	  //  deleteStockSymbolTable(stockTick,batchOps);
	    batchOps.delete(stockTick);
	    batchOps.execute();
	  }
	
	  private void deleteByAll(final StockTick stockTick, final CassandraBatchOperations batchOps) {
		 
	    batchOps.delete(
	    		sockTickByExchangeRepository.findByKeyExchangeAndKeySymbolAndKeyTickDate(
	    				stockTick.getExchange(),stockTick.getSymbol(),stockTick.getTickDate())
	    		);
	    
	    batchOps.delete(
	    		 
	    		sockTickByExchangeDateRepository.findByKeyExchangeAndKeyTickDateAndKeySymbol(
	    				stockTick.getExchange(), stockTick.getTickDate(), stockTick.getSymbol())
	    		);
	  }
	  private void deleteByExchange(final StockTick stockTick, final CassandraBatchOperations batchOps) {
		 
		  batchOps.delete(
					sockTickByExchangeRepository.findByKeyExchangeAndKeySymbolAndKeyTickDate(
		    				stockTick.getExchange(),stockTick.getSymbol(),stockTick.getTickDate())
		    		);
	  }
	  
	  private void deleteByExchangeDate(final StockTick stockTick, final CassandraBatchOperations batchOps) {
		  
		  batchOps.delete(
					sockTickByExchangeDateRepository.findByKeyExchangeAndKeyTickDateAndKeySymbol(
		    				stockTick.getExchange(), stockTick.getTickDate(), stockTick.getSymbol())
		    		);
	  }
	
	/* private void deleteStockSymbolTable(final StockTick stockTick, final CassandraBatchOperations batchOps) {
		 
		  batchOps.delete(
				  stockSymbolTableRepository.findByKeySymbol(stockTick.getSymbol())
		   );
	  }*/
	
	  @Override
	  public void deleteById(final UUID id) {
	    findById(id).ifPresent(this::delete);
	  }
	
	  @Override
	  public void deleteAll(final Iterable<? extends StockTick> stockTick) {
		  stockTick.forEach(this::delete);
	  }
	
	  @Override
	  public void deleteAll() {
	    deleteAll(findAll());
	  }
	
	  @Override
	  public <S extends StockTick> List<S> insert(final Iterable<S> movies) {
	    final List<S> result = new ArrayList<>();
	    for (final S movie : movies) {
	      result.add(insert(movie));
	    }
	    return result;
	  }
	
	  @Override
	  public <S extends StockTick> S save(final S movie) {
	    return insert(movie);
	  }
	
	  @Override
	  public <S extends StockTick> List<S> saveAll(final Iterable<S> movies) {
	    return insert(movies);
	  }
}
