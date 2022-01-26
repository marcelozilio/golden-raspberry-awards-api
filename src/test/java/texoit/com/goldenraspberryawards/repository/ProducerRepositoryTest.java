package texoit.com.goldenraspberryawards.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import texoit.com.goldenraspberryawards.entity.Producer;
import texoit.com.goldenraspberryawards.util.RandomUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class ProducerRepositoryTest {

    @Autowired
    private ProducerRepository producerRepository;

    @Test
    void saveProducerWithSuccess() {
        Producer producer = Producer.builder()
                .name(RandomUtils.generateString(10))
                .build();

        Producer producerSaved = producerRepository.save(producer);

        assertThat(producerSaved).isNotNull();
        assertThat(producerSaved.getId()).isNotNull();
        assertThat(producerSaved.getName()).isEqualTo(producer.getName());

        producerRepository.deleteAll();
    }

    @Test
    void saveProducerWithErrorWhenNameIsNull() {

        Producer producer = Producer.builder().build();

        assertThatThrownBy(() -> producerRepository.save(producer)).hasMessageContaining("not-null");
    }

}