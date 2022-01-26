package texoit.com.goldenraspberryawards.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AwardInterval {

    private String producer;
    private int interval;
    private int previousWin;
    private int followingWing;
}
