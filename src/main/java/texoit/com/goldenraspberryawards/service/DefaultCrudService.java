package texoit.com.goldenraspberryawards.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public interface DefaultCrudService<T, Id extends Serializable> {

    Long count();

    T save(T entity);

    List<T> save(List<T> entities);

    T findOne(Id id);

    Page<T> findAll(Pageable pageable);

    List<T> findAll();

    T update(T entity);

    void deleteById(Id id);

    void delete(T entity);

    void delete(Iterable<? extends T> entities);

    boolean existsById(Id id);
}
