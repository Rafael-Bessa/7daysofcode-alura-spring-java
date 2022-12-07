package bessa.morangon.rafael.daysofcode.controller;

import bessa.morangon.rafael.daysofcode.model.Movie;
import bessa.morangon.rafael.daysofcode.repository.MovieRepository;
import bessa.morangon.rafael.daysofcode.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@Controller
public class MoviePersonalizadoController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MovieService service;
    @Autowired
    private MovieRepository repository;

    @Value("${api.key}")
    private String apiKey;


    @GetMapping("/personalizado")
    public String paginaPersonalizada(Model model) throws IOException {

        ResponseEntity<String> response =
                restTemplate.getForEntity("https://imdb-api.com/en/API/Top250Movies/" + apiKey, String.class);

        String json = response.getBody();
        List<Movie> movies = service.instanciaMovies(json);

        movies.sort(Comparator.comparing(Movie::getYear));

        for (Movie m: movies) {
            if(Integer.parseInt(m.getYear()) >= 2020){
                repository.save(m);
            }
        }

        model.addAttribute("list", repository.findAll());

        return "personalizado";

    }


}
