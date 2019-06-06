package com.spring.data.cassandra.movie.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.data.cassandra.movie.entity.MovieByActor;
import com.spring.data.cassandra.movie.entity.MovieByActorKey;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface MovieByActorRepository extends CassandraRepository<MovieByActor, MovieByActorKey> {

  @Query(allowFiltering = true)
  List<MovieByActor> findByKeyReleaseDateAndKeyMovieId(LocalDateTime releaseDate, UUID movieId);
}