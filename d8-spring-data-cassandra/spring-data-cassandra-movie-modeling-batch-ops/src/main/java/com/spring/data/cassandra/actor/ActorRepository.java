package com.spring.data.cassandra.actor;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.spring.data.cassandra.actor.entity.Actor;

import java.util.UUID;

@Repository
public interface ActorRepository extends CassandraRepository<Actor, UUID> {}
