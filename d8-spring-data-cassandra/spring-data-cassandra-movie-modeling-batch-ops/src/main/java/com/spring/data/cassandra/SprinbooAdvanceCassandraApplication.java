package com.spring.data.cassandra;
/**
 *  CREATE TABLE movies(
	  movie_id UUID,
	  release_date TIMESTAMP,
	  title TEXT,
	  genres SET<TEXT>,
	  age_rating TEXT,
	  PRIMARY KEY((movieId))
	);
	
	CREATE TABLE movies_by_year(
	  year INT,
	  release_date TIMESTAMP,
	  movie_id UUID,
	  title TEXT,
	  genres SET<TEXT>,
	  age_rating TEXT,
	  PRIMARY KEY((year), release_date, movie_id)
	) WITH CLUSTERING ORDER BY(release_date DESC, movie_id DESC);
	
	CREATE TABLE movies_by_genre(
	  genre TEXT,
	  release_date TIMESTAMP,
	  movie_id UUID,
	  title TEXT,
	  genres SET<TEXT>,
	  age_rating TEXT,
	  PRIMARY KEY((genre), release_date, movie_id)
	) WITH CLUSTERING ORDER BY(release_date DESC , movie_id ASC);
	
	CREATE TABLE movies_by_actor(
	  actor_name TEXT,
	  release_date TIMESTAMP,
	  movie_id UUID,
	  character_name TEXT
	  title TEXT,
	  genres SET<TEXT>
	  age_rating TEXT,
	  PRIMARY KEY((actor_name), release_date, movie_id, character_name)
	) WITH CLUSTERING ORDER BY (release_date DESC, movie_id DESC, character_name ASC);
	
	CREATE TABLE actors(
	  actor_id UUID,
	  name TEXT,
	  date_of_birth TIMESTAMP,
	  PRIMARY KEY((actor_id))
	);
	
	CREATE TABLE actors_by_movie(
	  movie_id UUID,
	  release_date TIMESTAMP,
	  actor_name TEXT,
	  character_name TEXT,
	  PRIMARY KEY((movie_id), release_date, actor_name, character_name)
	) WITH CLUSTERING ORDER BY(release_date DESC, actor_name ASC, character_name ASC); 
 */
import com.spring.data.cassandra.actor.ActorByMovieRepository;
import com.spring.data.cassandra.actor.ActorRepository;
import com.spring.data.cassandra.actor.entity.Actor;
import com.spring.data.cassandra.movie.entity.Movie;
import com.spring.data.cassandra.movie.entity.Role;
import com.spring.data.cassandra.movie.repository.MovieByActorRepository;
import com.spring.data.cassandra.movie.repository.MovieByGenreRepository;
import com.spring.data.cassandra.movie.repository.MovieByYearRepository;
import com.spring.data.cassandra.movie.repository.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@SpringBootApplication
public class SprinbooAdvanceCassandraApplication implements CommandLineRunner {

  @Autowired private ActorRepository actorRepository;

  @Autowired private MovieRepository movieRepository;

  @Autowired private MovieByActorRepository movieByActorRepository;

  @Autowired private MovieByGenreRepository movieByGenreRepository;

  @Autowired private MovieByYearRepository movieByYearRepository;

  @Autowired private ActorByMovieRepository actorByMovieRepository;

  public static void main(final String args[]) {
    SpringApplication.run(SprinbooAdvanceCassandraApplication.class);
  }

  @Override
  public void run(final String... args) throws Exception {
	
	movieRepository.deleteAll();
  
    final Actor tomHanks =
        new Actor(UUID.randomUUID(), "Tom Hanks", LocalDateTime.of(1956, 7, 9, 0, 0));
    
    final Actor megRyan =
            new Actor(UUID.randomUUID(), "Meg Ryan", LocalDateTime.of(1961, 11, 19, 0, 0));
    
    final Actor vinDiesel =
        new Actor(UUID.randomUUID(), "Vin Diesel", LocalDateTime.of(1967, 7, 18, 0, 0));
    final Actor mattDamon =
        new Actor(UUID.randomUUID(), "Matt Damon", LocalDateTime.of(1970, 10, 8, 0, 0));
    
  
    actorRepository.insert(Arrays.asList(tomHanks, megRyan, vinDiesel, mattDamon));

    final Role captainMiller = new Role("Tom Hanks", "Captain Miller");
    final Role ForestGumpRole = new Role("Tom Hanks", "Forest Gump");
    final Role SleeplessInSeattleRole = new Role("Tom Hanks", "Sam Baldwin");
    final Role privateCaparzo = new Role("Vin Diesel", "Private Caparzo");
    final Role privateRyan = new Role("Matt Damon", "Private Ryan");
    final Movie savingPrivateRyan =
        new Movie(
            "Saving Private Ryan",
            LocalDateTime.of(1998, 7, 21, 0, 0),
            new HashSet<>( Arrays.asList("Action", "War", "Drama")),
            "18",
            Arrays.asList(captainMiller, privateCaparzo, privateRyan));

    movieRepository.insert(savingPrivateRyan);

    final Movie sleeplessInSeattle =
            new Movie(
                "Sleepless In Seattle",
                LocalDateTime.of(1993, 6, 25, 0, 0),
                new HashSet<>( Arrays.asList("Romantic","Comedy")),
                "13",
                Arrays.asList(new Role("Tom Hanks", "Sam Baldwin"), new Role("Meg Ryan", "Annie Reed"))
               );
    
    movieRepository.insert(sleeplessInSeattle);
    
    final Movie forestGump =
            new Movie(
                "Forest Gump",
                LocalDateTime.of(1994, 7, 21, 0, 0),
                new HashSet<>( Arrays.asList("Drama","War")),
                "18",
                Arrays.asList(ForestGumpRole));
    
    movieRepository.insert(forestGump);
    
    System.out.println("AFTER INSERT");
    System.out.println("All Movies:");
    movieRepository.findAll().forEach(System.out::println);
    System.out.println("All Movies_by_Actor:"); 
    movieByActorRepository.findAll().forEach(System.out::println);
    System.out.println("All Movies_by_Genre:"); 
    movieByGenreRepository.findAll().forEach(System.out::println);
    System.out.println("All Movies_by_Year:"); 
    movieByYearRepository.findAll().forEach(System.out::println);
    System.out.println("All Actor_by_Movie:"); 
    actorByMovieRepository.findAll().forEach(System.out::println);
    System.out.println("All Actor:"); 
    actorRepository.findAll().forEach(System.out::println);

   /* movieRepository.deleteAll(); // if you need, uncoment 

    System.out.println("AFTER DELETE");
    movieRepository.findAll().forEach(System.out::println);
    movieByActorRepository.findAll().forEach(System.out::println);
    movieByGenreRepository.findAll().forEach(System.out::println);
    movieByYearRepository.findAll().forEach(System.out::println);
    actorByMovieRepository.findAll().forEach(System.out::println);
    actorRepository.findAll().forEach(System.out::println);*/
  }
}
