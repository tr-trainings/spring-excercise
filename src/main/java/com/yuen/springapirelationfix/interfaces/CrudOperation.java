package com.yuen.springapirelationfix.interfaces;

import java.util.List;

public interface CrudOperation<T>{
    List<T> findAll();
    T findById(Long id);
    T add(T entity);
    Boolean delete(Long id);
    T update(Long id, T entity);
}
