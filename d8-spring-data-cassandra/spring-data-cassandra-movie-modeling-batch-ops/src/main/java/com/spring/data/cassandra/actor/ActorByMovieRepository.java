package com.spring.data.cassandra.actor;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.spring.data.cassandra.actor.entity.ActorByMovie;
import com.spring.data.cassandra.actor.entity.ActorByMovieKey;

import java.util.List;
import java.util.UUID;

@Repository
public interface ActorByMovieRepository extends CassandraRepository<ActorByMovie, ActorByMovieKey>{

  List<ActorByMovie> findByKeyMovieId(UUID movieId);
}
