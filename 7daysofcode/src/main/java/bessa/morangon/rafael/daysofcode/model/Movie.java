package bessa.morangon.rafael.daysofcode.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "filmes")
public class Movie {

	@Id
	@Column(name = "imagem", unique = true, nullable = false)
	private String image;
	@Column(name = "titulo",nullable = false)
	private String title;
	@Column(name = "ano", nullable = false)
	private String year;
	@Column(name = "rating", nullable = false)
	private String imDbRating;

}
