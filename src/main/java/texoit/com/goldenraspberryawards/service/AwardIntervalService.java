package texoit.com.goldenraspberryawards.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import texoit.com.goldenraspberryawards.dto.AwardIntervalProcess;
import texoit.com.goldenraspberryawards.dto.response.AwardInterval;
import texoit.com.goldenraspberryawards.dto.response.AwardIntervalResponse;
import texoit.com.goldenraspberryawards.entity.Movie;
import texoit.com.goldenraspberryawards.entity.Producer;
import texoit.com.goldenraspberryawards.mapper.AwardIntervalMapper;
import texoit.com.goldenraspberryawards.util.AwardIntervalUtil;
import texoit.com.goldenraspberryawards.util.MovieUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AwardIntervalService {

    private final ProducerService producerService;

    public AwardIntervalResponse getIntervals() {

        Map<Producer, List<AwardIntervalProcess>> allProducersAndWards = getProducersAndAWards();

        List<AwardInterval> allMaxAwardIntervals = new ArrayList<>();
        List<AwardInterval> allMinAwardIntervals = new ArrayList<>();

        allProducersAndWards.keySet().forEach(producer -> {

            allMaxAwardIntervals.add(AwardIntervalMapper.map(producer,
                    AwardIntervalUtil.getMaxAwardIntervalProcessByProducer(allProducersAndWards, producer)));

            allMinAwardIntervals.add(AwardIntervalMapper.map(producer,
                    AwardIntervalUtil.getMinAwardIntervalProcessByProducer(allProducersAndWards, producer)));
        });

        return AwardIntervalResponse.builder()
                .max(AwardIntervalUtil.getAllMaxAwardIntervals(allMaxAwardIntervals))
                .min(AwardIntervalUtil.getAllMinAwardIntervals(allMinAwardIntervals))
                .build();
    }

    private Map<Producer, List<AwardIntervalProcess>> getProducersAndAWards() {

        List<Producer> producers = producerService.findAll();

        this.setAwardedMoviesAndSortByYear(producers);

        Map<Producer, List<AwardIntervalProcess>> producerAndIntervalsOfAwards = new HashMap<>();

        producers.forEach(producer -> {

            List<Integer> years = this.getYearsFromTheMovies(producer);

            List<AwardIntervalProcess> intervalsOfAwards = AwardIntervalUtil.getIntervalsOfAwards(years);

            producerAndIntervalsOfAwards.put(producer, intervalsOfAwards);
        });

        return producerAndIntervalsOfAwards;
    }

    private List<Integer> getYearsFromTheMovies(Producer producer) {
        return producer.getMovies()
                .stream()
                .map(Movie::getYear)
                .collect(Collectors.toList());
    }

    private void setAwardedMoviesAndSortByYear(List<Producer> producers) {
        producers.forEach(producer -> producer.setMovies(MovieUtils.getAwardedMoviesAndSortByYear(producer.getMovies())));
    }
}
