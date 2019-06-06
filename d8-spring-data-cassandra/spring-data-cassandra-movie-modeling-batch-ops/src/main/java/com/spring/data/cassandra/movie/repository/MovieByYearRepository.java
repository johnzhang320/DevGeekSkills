package com.spring.data.cassandra.movie.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.spring.data.cassandra.movie.entity.MovieByYear;
import com.spring.data.cassandra.movie.entity.MovieByYearKey;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface MovieByYearRepository extends CassandraRepository<MovieByYear, MovieByYearKey> {

  List<MovieByYear> findByKeyYearAndKeyReleaseDateAndKeyMovieId(int year, LocalDateTime releaseDate, UUID movieId);
}