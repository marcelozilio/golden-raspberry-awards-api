package texoit.com.goldenraspberryawards.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import texoit.com.goldenraspberryawards.dto.response.AwardIntervalResponse;
import texoit.com.goldenraspberryawards.service.AwardIntervalService;

@RestController
@RequestMapping("gra/award-interval")
public class AwardIntervalController {

    private final AwardIntervalService awardIntervalService;

    public AwardIntervalController(AwardIntervalService awardIntervalService) {
        this.awardIntervalService = awardIntervalService;
    }

    @GetMapping
    public ResponseEntity<AwardIntervalResponse> handle() {
        return ResponseEntity.ok(awardIntervalService.getIntervals());
    }

}
