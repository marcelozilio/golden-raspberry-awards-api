package texoit.com.goldenraspberryawards.service;

import org.springframework.stereotype.Service;
import texoit.com.goldenraspberryawards.entity.Producer;
import texoit.com.goldenraspberryawards.repository.ProducerRepository;

@Service
public class ProducerService extends DefaultCrudServiceImpl<Producer, Long> {

    public ProducerService(ProducerRepository repository) {
        super(repository);
    }

    public Producer findByName(String name){
        return this.getRepository().findByName(name);
    }

    @Override
    protected ProducerRepository getRepository() {
        return (ProducerRepository) super.getRepository();
    }
}
