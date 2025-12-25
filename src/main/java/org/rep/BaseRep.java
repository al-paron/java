package org.rep;

import java.util.List;
import java.util.Optional;

public interface BaseRep<T, ID> {
    Optional<T> findSingle(ID id);
    List<T> findAll();
    void save(T object);
    void delete(Integer id);
}
