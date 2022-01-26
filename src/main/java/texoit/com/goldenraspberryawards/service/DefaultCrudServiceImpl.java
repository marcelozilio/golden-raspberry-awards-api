package texoit.com.goldenraspberryawards.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public abstract class DefaultCrudServiceImpl<T, Id extends Serializable> implements DefaultCrudService<T, Id> {

    private final JpaRepository<T, Id> repository;

    public DefaultCrudServiceImpl(JpaRepository<T, Id> repository) {
        this.repository = repository;
    }

    @Override
    public Long count() {
        return repository.count();
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public List<T> save(List<T> entities) {
        return repository.saveAll(entities);
    }

    @Override
    public Optional<T> findOne(Id id) {
        return repository.findById(id);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T update(T entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(Id id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    public void delete(Iterable<? extends T> entities) {
        repository.deleteAll(entities);
    }

    public void deleteAllInBatch(){
        repository.deleteAllInBatch();
    }

    @Override
    public boolean existsById(Id id) {
        return repository.existsById(id);
    }

    protected JpaRepository<T, Id> getRepository() {
        return repository;
    }
}
