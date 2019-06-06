package com.spring.data.cassandra;
import com.spring.data.cassandra.stocktick.entity.StockTick;

import com.spring.data.cassandra.stocktick.repository.StockTickByExchangeDateRepository;
import com.spring.data.cassandra.stocktick.repository.StockTickByExchangeRepository;
import com.spring.data.cassandra.stocktick.repository.StockTickRepository;
import com.spring.data.cassandra.symbol.entity.StockSymbol;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@SpringBootApplication
public class StockTickCassandraApplication implements CommandLineRunner {
	
	  @Autowired private StockTickRepository stockTickRepository;
	   
 

	  @Autowired private StockTickByExchangeRepository stockTickByExchangeRepository;

	  @Autowired private StockTickByExchangeDateRepository stockTickByExchangeDateRepository;
	  
	 public static void main(final String args[]) {
		    SpringApplication.run(StockTickCassandraApplication.class);
	 }
	 /*
	  * stock_symbol
	 	symbol       description         exchange
	 	AAPL         Apple,Inc           NASDAG
	 	FB           Facebook,Inc        NASDAG
	 	AMZN		 Amazon,Inc          NASDAG
	 	ULTA		 Ulta Beauty,Inc     DOW     
	  	SPXL         Direxion 3xETF      S&P500
	 	
	 	
	 	stock_tick
	 	symbol		tick_date		open		high		low			close		volume
	 	AAPL		2019-04-24		568.21		570.00		560.73		567.77		27092600	
	 	FB			2019-04-24		63.60		63.65		58.77		60.87		1327092600	
	 	AAPL		2019-04-25		564.53		571.00		563.96		567.77		28012600	
	 	FB			2019-04-25		59.97		60.01		57.57		57.71		91232243
	 	SPXL        2019-06-02      42.30       43.5        41.85       42.6        346364352
	     ULTA        2019-06-02      340.23      340.6       329.4       331.2       656564553 
	  */
	 @Override
	 public void run(final String... args) throws Exception {
		  stockTickRepository.deleteAll();   // if tables exist , run it
		  final StockSymbol AAPL =new StockSymbol("AAPL","Apple,Inc","NASDAG");
		  final StockSymbol FB =new StockSymbol("FB","Facebook,Inc","NASDAG");
		  final StockSymbol AMZN =new StockSymbol("AMZN","Amazon,Inc","NASDAG");
		  final StockSymbol ULTA =new StockSymbol("ULTA","Ulta Beauty,Inc","DOW ");    
		  final StockSymbol SPXL =new StockSymbol("SPXL","Direxion 3xETF","S&P500");
		  stockTickRepository.insert(new StockTick(UUID.randomUUID(),FB.getSymbol(),FB.getDescription(),FB.getExchange(),"2019-04-24",63.60F,63.65F,58.77F,60.87F,1327092600L));;	
		  stockTickRepository.insert(new StockTick(UUID.randomUUID(),AAPL.getSymbol(),AAPL.getDescription(),AAPL.getExchange(),"2019-04-25",564.53F,571.00F,563.96F,567.77F,28012600L));	
		  stockTickRepository.insert(new StockTick(UUID.randomUUID(),FB.getSymbol(),FB.getDescription(),FB.getExchange(),"2019-04-25",59.97F,60.01F,57.57F,57.71F,91232243L));
		  stockTickRepository.insert(new StockTick(UUID.randomUUID(),SPXL.getSymbol(),SPXL.getDescription(),SPXL.getExchange(),"2019-06-02", 42.30F,  43.5F,41.85F,  42.6F,346364352L));
		  stockTickRepository.insert(new StockTick(UUID.randomUUID(),SPXL.getSymbol(),SPXL.getDescription(),SPXL.getExchange(),"2019-06-03",41.30F, 42.5F,40.85F, 39.6F,335636435L));
		  stockTickRepository.insert(new StockTick(UUID.randomUUID(),ULTA.getSymbol(),ULTA.getDescription(),ULTA.getExchange(),"2019-06-02",340.23F,340.6F, 329.4F, 331.2F, 656564553L));
		  stockTickRepository.insert(new StockTick(UUID.randomUUID(),ULTA.getSymbol(),ULTA.getDescription(),ULTA.getExchange(),"2019-06-03",380.23F,390.6F, 329.4F, 325.2F, 452464553L)); 
	  
	   
		  System.out.println("AFTER INSERT");
		  System.out.println("All StockTick:");
		  stockTickRepository.findAll().forEach(System.out::println);
		  System.out.println("All StockTickByExchange:"); 
		  stockTickByExchangeRepository.findAll().forEach(System.out::println);
		  System.out.println("All  StockTickByExchangeDate:"); 
		  stockTickByExchangeDateRepository.findAll().forEach(System.out::println);
		    
		  //  System.out.println("All Stock_Symbol:"); 
		   // stockSymbolRepository.findAll().forEach(System.out::println);
		    /*movieRepository.deleteAll();

		    System.out.println("AFTER DELETE");
		    movieRepository.findAll().forEach(System.out::println);
		    movieByActorRepository.findAll().forEach(System.out::println);
		    movieByGenreRepository.findAll().forEach(System.out::println);
		    movieByYearRepository.findAll().forEach(System.out::println);
		    actorByMovieRepository.findAll().forEach(System.out::println);
		    actorRepository.findAll().forEach(System.out::println);*/
	 }
}


 /*
  * Result displayed in Cqlsh side:
  *  
  *  exchange | symbol | tick_date  | close     | description
	----------+--------+------------+-----------+-----------------
	   S&P500 |   SPXL | 2019-06-02 |      42.6 |  Direxion 3xETF
	   S&P500 |   SPXL | 2019-06-03 |      39.6 |  Direxion 3xETF
	   NASDAG |   AAPL | 2019-04-25 | 567.77002 |       Apple,Inc
	   NASDAG |     FB | 2019-04-24 |     60.87 |    Facebook,Inc
	   NASDAG |     FB | 2019-04-25 |     57.71 |    Facebook,Inc
	     DOW  |   ULTA | 2019-06-02 | 331.20001 | Ulta Beauty,Inc
	     DOW  |   ULTA | 2019-06-03 | 325.20001 | Ulta Beauty,Inc
	
	(7 rows)
	cqlsh:mykeyspace> select * from stock_tick_by_exchanges;
	
	 exchange | symbol | tick_date  | close     | description
	----------+--------+------------+-----------+-----------------
	   S&P500 |   SPXL | 2019-06-02 |      42.6 |  Direxion 3xETF
	   S&P500 |   SPXL | 2019-06-03 |      39.6 |  Direxion 3xETF
	   NASDAG |   AAPL | 2019-04-25 | 567.77002 |       Apple,Inc
	   NASDAG |     FB | 2019-04-24 |     60.87 |    Facebook,Inc
	   NASDAG |     FB | 2019-04-25 |     57.71 |    Facebook,Inc
	     DOW  |   ULTA | 2019-06-02 | 331.20001 | Ulta Beauty,Inc
	     DOW  |   ULTA | 2019-06-03 | 325.20001 | Ulta Beauty,Inc
	
	(7 rows)
	cqlsh:mykeyspace> select * from stock_ticks;
	
	 stock_tick_id                        | close     | description     | exchange | high      | low       | open      | symbol | tickdate   | volume
	--------------------------------------+-----------+-----------------+----------+-----------+-----------+-----------+--------+------------+------------
	 9283cdec-13e5-4d92-99e3-680f3e180f72 | 325.20001 | Ulta Beauty,Inc |     DOW  | 390.60001 | 329.39999 | 380.23001 |   ULTA | 2019-06-03 |  452464553
	 6fa5d868-62b4-41dc-9010-a82b0ea9a020 | 331.20001 | Ulta Beauty,Inc |     DOW  | 340.60001 | 329.39999 | 340.23001 |   ULTA | 2019-06-02 |  656564553
	 8c06f9e7-58ac-4180-831a-3f158de21e9e |      42.6 |  Direxion 3xETF |   S&P500 |      43.5 |     41.85 |      42.3 |   SPXL | 2019-06-02 |  346364352
	 46d8f7c4-b926-49a3-8014-0510d216b8f2 |     60.87 |    Facebook,Inc |   NASDAG |     63.65 |     58.77 |      63.6 |     FB | 2019-04-24 | 1327092600
	 a95d35d2-e619-4715-a186-7ab584291770 | 567.77002 |       Apple,Inc |   NASDAG |       571 | 563.96002 | 564.53003 |   AAPL | 2019-04-25 |   28012600
	 a44b4966-fcc5-4fbe-b16e-386d4b5fa3c9 |     57.71 |    Facebook,Inc |   NASDAG |     60.01 |     57.57 |     59.97 |     FB | 2019-04-25 |   91232243
	 23f93d41-0201-48ae-9d5e-3f18a00029aa |      39.6 |  Direxion 3xETF |   S&P500 |      42.5 |     40.85 |      41.3 |   SPXL | 2019-06-03 |  335636435

  * 
  */
 