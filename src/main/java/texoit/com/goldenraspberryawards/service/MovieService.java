package texoit.com.goldenraspberryawards.service;

import org.springframework.stereotype.Service;
import texoit.com.goldenraspberryawards.entity.Movie;
import texoit.com.goldenraspberryawards.repository.MovieRepository;

@Service
public class MovieService extends DefaultCrudServiceImpl<Movie, Long> {

    public MovieService(MovieRepository repository) {
        super(repository);
    }

    @Override
    protected MovieRepository getRepository() {
        return (MovieRepository) super.getRepository();
    }
}
