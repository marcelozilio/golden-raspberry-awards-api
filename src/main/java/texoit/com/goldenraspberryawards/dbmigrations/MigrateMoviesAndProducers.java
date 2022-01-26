package texoit.com.goldenraspberryawards.dbmigrations;

import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import texoit.com.goldenraspberryawards.service.MovieService;
import texoit.com.goldenraspberryawards.service.ProducerService;
import texoit.com.goldenraspberryawards.entity.Movie;
import texoit.com.goldenraspberryawards.entity.Producer;
import texoit.com.goldenraspberryawards.util.CSVUtils;

import java.io.IOException;
import java.io.Reader;
import java.util.Objects;

@AllArgsConstructor
@Component
public class MigrateMoviesAndProducers implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger logger = LoggerFactory.getLogger(MigrateMoviesAndProducers.class);

    private final ProducerService producerService;
    private final MovieService movieService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            this.handle("movies.csv");
        } catch (IOException e) {
            logger.error("Error on migrate database", e);
            e.printStackTrace();
        }
    }

    public void handle(String fileName) throws IOException {

        logger.info("Init database migration...");

        Reader reader = CSVUtils.loadFile(fileName);

        CSVFormat csvFormat = CSVUtils.buildCSVFortmat();

        csvFormat.parse(reader).forEach(csvRecord -> {

            logger.info("Migrating line {}", csvFormat);

            Producer producer = this.saveProducerIfNotExists(csvRecord.get(CSVUtils.HEADERS[3]));

            this.saveMovie(csvRecord, producer);

            logger.info("Line migrated to database h2...");
        });

        logger.info("Finished migration .csv file to database h2...");

    }

    private void saveMovie(CSVRecord csvRecord, Producer producer) {
        Movie movie = movieService.save(
                Movie.builder()
                        .year(Integer.parseInt(csvRecord.get(CSVUtils.HEADERS[0])))
                        .title(csvRecord.get(CSVUtils.HEADERS[1]))
                        .studio(csvRecord.get(CSVUtils.HEADERS[2]))
                        .winner("yes".equals(csvRecord.get(CSVUtils.HEADERS[4])))
                        .producerId(producer.getId())
                        .build());
        logger.info("Movie registered -> {}", movie);
    }

    private Producer saveProducerIfNotExists(String producerName) {
        Producer producer = producerService.findByName(producerName);

        if (Objects.isNull(producer))
            producer = producerService.save(Producer
                    .builder()
                    .name(producerName)
                    .build());

        logger.info("Producer registered -> {}", producer);

        return producer;
    }




}
