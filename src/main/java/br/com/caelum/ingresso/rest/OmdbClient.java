package br.com.caelum.ingresso.rest;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.caelum.ingresso.model.DetalhesDoFilme;
import br.com.caelum.ingresso.model.Filme;

@Component
public class OmdbClient {
  public Optional<DetalhesDoFilme> request(Filme filme) {
    RestTemplate client = new RestTemplate();

    String titulo = filme.getNome().replace(" ", "+");

    String url = String.format("https://omdb-fj22.herokuapp.com/movie?title=%s", titulo);
    
    try {
      System.out.println("Fazendo requisição para: " + url);
      DetalhesDoFilme detalhesDoFilme = client.getForObject(url, DetalhesDoFilme.class);
      System.out.println("Resposta recebida!");
      return Optional.ofNullable(detalhesDoFilme);
    } catch(RestClientException e) {
      System.out.println("deu erro: " + e.getMessage());
      return Optional.empty();
    }
  }
}
