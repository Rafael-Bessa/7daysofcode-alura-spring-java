package bessa.morangon.rafael.daysofcode.repository;

import bessa.morangon.rafael.daysofcode.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, String> {
}
