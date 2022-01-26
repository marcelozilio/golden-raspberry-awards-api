package texoit.com.goldenraspberryawards.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import texoit.com.goldenraspberryawards.entity.Producer;
import texoit.com.goldenraspberryawards.service.ProducerService;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("gra/producers")
public class ProducerController {

    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Producer producer) throws URISyntaxException {
        Producer save = producerService.save(producer);
        return ResponseEntity.created(new URI("/producers/" + save.getId())).build();
    }

    @GetMapping
    public ResponseEntity<Page<Producer>> findAll(Pageable pageable) {
        return ResponseEntity.ok(producerService.findAll(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Producer> findOne(@PathVariable("id") Long id) {
        return producerService.findOne(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Producer> put(@PathVariable("id") Long id, @RequestBody Producer producer) {
        return producerService.existsById(id)
                ? ResponseEntity.ok(producerService.update(producer))
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        if (!producerService.existsById(id)) return ResponseEntity.notFound().build();

        producerService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
