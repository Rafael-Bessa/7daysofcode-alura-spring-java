package bessa.morangon.rafael.daysofcode.service;

import bessa.morangon.rafael.daysofcode.model.Movie;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
@Service
public class MovieService {
    public List<Movie> instanciaMovies(String json) throws IOException {

        String pedaco = json.substring(9,json.length() - 19);

        ObjectMapper mapper = new ObjectMapper();
        List<Movie> filmes = mapper.readValue(pedaco, new TypeReference<List<Movie>>(){});

        return filmes;
    }

}
