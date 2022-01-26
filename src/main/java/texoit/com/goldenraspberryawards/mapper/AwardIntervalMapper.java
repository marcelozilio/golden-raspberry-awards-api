package texoit.com.goldenraspberryawards.mapper;

import texoit.com.goldenraspberryawards.dto.AwardIntervalProcess;
import texoit.com.goldenraspberryawards.dto.response.AwardInterval;
import texoit.com.goldenraspberryawards.entity.Producer;

public class AwardIntervalMapper {

    public static AwardInterval map(Producer producer, AwardIntervalProcess awardIntervalProcess) {
        return AwardInterval.builder()
                .producer(producer.getName())
                .interval(awardIntervalProcess.getSubtractionResult())
                .previousWin(awardIntervalProcess.getSmallestYear())
                .followingWing(awardIntervalProcess.getBiggestYear())
                .build();
    }
}
