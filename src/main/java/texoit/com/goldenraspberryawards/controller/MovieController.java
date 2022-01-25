package texoit.com.goldenraspberryawards.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import texoit.com.goldenraspberryawards.entity.Movie;
import texoit.com.goldenraspberryawards.service.MovieService;

@RestController
@RequestMapping("gra/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<Page<Movie>> getAll(Pageable pageable){
        return ResponseEntity.ok(movieService.findAll(pageable));
    }
}
