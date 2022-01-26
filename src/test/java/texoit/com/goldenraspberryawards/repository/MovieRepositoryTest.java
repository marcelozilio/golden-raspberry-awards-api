package texoit.com.goldenraspberryawards.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import texoit.com.goldenraspberryawards.entity.Movie;
import texoit.com.goldenraspberryawards.entity.Producer;
import texoit.com.goldenraspberryawards.util.RandomUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @Test
    void saveMovieWithSuccess() {

        Producer producer = this.createProducer();

        Movie movie = Movie.builder()
                .producerId(producer.getId())
                .year(RandomUtils.generateInteger(4).intValue())
                .title(RandomUtils.generateString(20))
                .studio(RandomUtils.generateString(20))
                .winner(true)
                .build();

        Movie savedMovie = movieRepository.save(movie);

        assertThat(savedMovie).isNotNull();
        assertThat(savedMovie.getId()).isNotNull();
        assertThat(savedMovie.getProducerId()).isEqualTo(movie.getProducerId());
        assertThat(savedMovie.getYear()).isEqualTo(movie.getYear());
        assertThat(savedMovie.getTitle()).isEqualTo(movie.getTitle());
        assertThat(savedMovie.getStudio()).isEqualTo(movie.getStudio());
        assertThat(savedMovie.getWinner()).isEqualTo(movie.getWinner());

        this.cleanUp();
    }

    @Test
    void saveMovieWithErrorWhenProducerIdIsNull() {

        Movie movie = Movie.builder()
                .year(RandomUtils.generateInteger(4).intValue())
                .title(RandomUtils.generateString(20))
                .studio(RandomUtils.generateString(20))
                .winner(true)
                .build();

        assertThatThrownBy(() -> movieRepository.save(movie))
                .hasMessageContaining("not-null")
                .hasMessageContaining("producerId");
    }

    @Test
    void saveMovieWithErrorWhenYearIsNull() {

        Movie movie = Movie.builder()
                .producerId(this.createProducer().getId())
                .title(RandomUtils.generateString(20))
                .studio(RandomUtils.generateString(20))
                .winner(true)
                .build();

        assertThatThrownBy(() -> movieRepository.save(movie))
                .hasMessageContaining("not-null")
                .hasMessageContaining("year");
    }

    @Test
    void saveMovieWithErrorWhenTitleIsNull() {

        Movie movie = Movie.builder()
                .producerId(this.createProducer().getId())
                .year(RandomUtils.generateInteger(4).intValue())
                .studio(RandomUtils.generateString(20))
                .winner(true)
                .build();

        assertThatThrownBy(() -> movieRepository.save(movie))
                .hasMessageContaining("not-null")
                .hasMessageContaining("title");
    }

    @Test
    void saveMovieWithErrorWhenStudioIsNull() {

        Movie movie = Movie.builder()
                .producerId(this.createProducer().getId())
                .year(RandomUtils.generateInteger(4).intValue())
                .title(RandomUtils.generateString(20))
                .winner(true)
                .build();

        assertThatThrownBy(() -> movieRepository.save(movie))
                .hasMessageContaining("not-null")
                .hasMessageContaining("studio");
    }

    @Test
    void saveMovieWithErrorWhenWinnerIsNull() {

        Movie movie = Movie.builder()
                .producerId(this.createProducer().getId())
                .year(RandomUtils.generateInteger(4).intValue())
                .title(RandomUtils.generateString(20))
                .studio(RandomUtils.generateString(20))
                .build();

        assertThatThrownBy(() -> movieRepository.save(movie))
                .hasMessageContaining("not-null")
                .hasMessageContaining("winner");
    }

    private Producer createProducer() {
        return producerRepository.save(
                Producer.builder()
                        .name(RandomUtils.generateString(10))
                        .build());
    }

    private void cleanUp() {
        movieRepository.deleteAll();
        producerRepository.deleteAll();
    }
}