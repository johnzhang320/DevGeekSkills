package com.spring.data.cassandra.movie.repository;

import java.util.UUID;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.spring.data.cassandra.movie.entity.Movie;

@NoRepositoryBean
public interface MovieRepository extends CassandraRepository<Movie, UUID> {
  
  // Movie insert(Movie movie);
}