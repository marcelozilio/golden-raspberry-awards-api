package texoit.com.goldenraspberryawards.util;

import texoit.com.goldenraspberryawards.dto.AwardIntervalProcess;
import texoit.com.goldenraspberryawards.dto.response.AwardInterval;
import texoit.com.goldenraspberryawards.entity.Producer;
import texoit.com.goldenraspberryawards.mapper.AwardIntervalProcessMapper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AwardIntervalUtil {

    public static int getIndexToBiggestYear(int i, List<Integer> years) {
        return i + 1 == years.size() ? i : i + 1;
    }

    public static AwardIntervalProcess getMinAwardIntervalProcessByProducer(Map<Producer, List<AwardIntervalProcess>> allProducersAndWards, Producer producer) {
        return allProducersAndWards.get(producer).stream()
                .min(Comparator.comparingInt(AwardIntervalProcess::getSubtractionResult))
                .orElseThrow();
    }

    public static AwardIntervalProcess getMaxAwardIntervalProcessByProducer(Map<Producer, List<AwardIntervalProcess>> allProducersAndWards, Producer producer) {
        return allProducersAndWards.get(producer).stream()
                .max(Comparator.comparingInt(AwardIntervalProcess::getSubtractionResult))
                .orElseThrow();
    }

    public static List<AwardInterval> getAllMinAwardIntervals(List<AwardInterval> allAwardIntervalsMin) {

        List<AwardInterval> result = new ArrayList<>();

        int min = Integer.MAX_VALUE;

        for (AwardInterval awardInterval : allAwardIntervalsMin) {
            if (min >= awardInterval.getInterval()) {
                min = awardInterval.getInterval();
                result.add(awardInterval);
            }
        }

        return result;
    }

    public static List<AwardInterval> getAllMaxAwardIntervals(List<AwardInterval> allAwardIntervalsMax) {
        return allAwardIntervalsMax.stream()
                .collect(Collectors.groupingBy(AwardInterval::getInterval, TreeMap::new, Collectors.toList()))
                .lastEntry()
                .getValue();
    }

    public static List<AwardIntervalProcess> getIntervalsOfAwards(List<Integer> years) {
        return IntStream.range(0, years.size())
                .mapToObj(i -> AwardIntervalProcessMapper.map(years, i))
                .limit(years.size() - 1)
                .collect(Collectors.toList());
    }
}
