package texoit.com.goldenraspberryawards.mapper;

import texoit.com.goldenraspberryawards.dto.AwardIntervalProcess;
import texoit.com.goldenraspberryawards.util.AwardIntervalUtil;

import java.util.List;

public class AwardIntervalProcessMapper {

    public static AwardIntervalProcess map(List<Integer> years, int i) {
        return AwardIntervalProcess.builder()
                .smallestYear(years.get(i))
                .biggestYear(years.get(AwardIntervalUtil.getIndexToBiggestYear(i, years)))
                .subtractionResult(years.get(AwardIntervalUtil.getIndexToBiggestYear(i, years)) - years.get(i))
                .build();
    }
}
