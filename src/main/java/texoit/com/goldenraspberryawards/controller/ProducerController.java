package texoit.com.goldenraspberryawards.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import texoit.com.goldenraspberryawards.entity.Producer;
import texoit.com.goldenraspberryawards.service.ProducerService;

@RestController
@RequestMapping("gra/producer")
public class ProducerController {

    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping
    public ResponseEntity<Page<Producer>> getAll(Pageable pageable) {
        return ResponseEntity.ok(producerService.findAll(pageable));
    }
}
