package com.spring.data.cassandra.movie.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.spring.data.cassandra.movie.entity.MovieByGenre;
import com.spring.data.cassandra.movie.entity.MovieByGenreKey;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface MovieByGenreRepository extends CassandraRepository<MovieByGenre, MovieByGenreKey> {

  List<MovieByGenre> findByKeyGenreAndKeyReleaseDateAndKeyMovieId(String genre, LocalDateTime releaseDate, UUID movieId);
}