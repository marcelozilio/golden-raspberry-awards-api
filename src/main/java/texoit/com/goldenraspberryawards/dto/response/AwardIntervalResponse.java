package texoit.com.goldenraspberryawards.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AwardIntervalResponse {

    private List<AwardInterval> min;
    private List<AwardInterval> max;
}
