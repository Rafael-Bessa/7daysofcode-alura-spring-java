package bessa.morangon.rafael.daysofcode.controller;

import bessa.morangon.rafael.daysofcode.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
public class MovieController {
	@Autowired
	private RestTemplate restTemplate;
	@Value("${api.key}")
	private String apiKey;
	
	@GetMapping("/top250")
    public String getTop250Filmes() throws IOException {
        ResponseEntity<String> response =
				restTemplate.getForEntity("https://imdb-api.com/en/API/Top250Movies/" + apiKey, String.class);
		return response.getBody();
    }

}



