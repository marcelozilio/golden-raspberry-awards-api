package texoit.com.goldenraspberryawards.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import texoit.com.goldenraspberryawards.entity.Movie;
import texoit.com.goldenraspberryawards.service.MovieService;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("gra/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Movie movie) throws URISyntaxException {
        Movie save = movieService.save(movie);
        return ResponseEntity.created(new URI("/movies/" + save.getId())).build();
    }

    @GetMapping
    public ResponseEntity<Page<Movie>> findAll(Pageable pageable) {
        return ResponseEntity.ok(movieService.findAll(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Movie> findOne(@PathVariable("id") Long id) {
        return movieService.findOne(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Movie> put(@PathVariable("id") Long id, @RequestBody Movie movie) {
        return movieService.existsById(id)
                ? ResponseEntity.ok(movieService.update(movie))
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        if (!movieService.existsById(id)) return ResponseEntity.notFound().build();

        movieService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
