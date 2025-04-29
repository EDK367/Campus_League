package com.umesdnd.CampusLeague.service.interfaces.common;

import java.util.List;

public interface CrudServiceInterface <T>{
    T getById(Long id);
    T saveOne(T t);
    T update(Long id, T t);
    void delete(Long id);
    List<T> getAll();
}
