package texoit.com.goldenraspberryawards.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AwardIntervalProcess {

    private int smallestYear;
    private int biggestYear;
    private int subtractionResult;

}
