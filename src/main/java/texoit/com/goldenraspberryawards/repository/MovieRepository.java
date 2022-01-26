package texoit.com.goldenraspberryawards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import texoit.com.goldenraspberryawards.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
