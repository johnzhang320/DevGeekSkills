package com.spring.data.cassandra.stocktick;

 
import com.spring.data.cassandra.stocktick.entity.StockTick;
import com.spring.data.cassandra.stocktick.repository.*;
 

import java.util.UUID;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.mapping.CassandraPersistentEntity;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.MappingCassandraEntityInformation;

@Configuration
public class StockTickConfig {

  @Bean
  public StockTickRepository stockTickRepository(
      final CassandraTemplate cassandraTemplate,
      final StockTickByExchangeRepository sockTickByExchangeRepository,
      final StockTickByExchangeDateRepository sockTickByExchangeDateRepository
     
     ) {
	  
    final CassandraPersistentEntity<?> entity =
        cassandraTemplate
            .getConverter()
            .getMappingContext()
            .getRequiredPersistentEntity(StockTick.class);
    
    final CassandraEntityInformation<StockTick, UUID> metadata =
        new MappingCassandraEntityInformation<>(
            (CassandraPersistentEntity<StockTick>) entity, cassandraTemplate.getConverter());
    
    return new StockTickRepositoryImpl(
        metadata,
        cassandraTemplate,
        sockTickByExchangeRepository,
        sockTickByExchangeDateRepository 
     );
  }
}
