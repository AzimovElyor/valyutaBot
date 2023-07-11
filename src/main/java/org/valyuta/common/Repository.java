package org.valyuta.common;

import java.util.List;

public interface Repository <ID, ENTITY extends BaseEntity<ID>> {
    void add(ENTITY entity);
    ENTITY findByID(ID id);
    void delete(ID id);
    List<ENTITY> getAll();
    ENTITY save(ENTITY entity);

}
