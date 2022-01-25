package texoit.com.goldenraspberryawards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import texoit.com.goldenraspberryawards.entity.Producer;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {

    Producer findByName(String name);
}
