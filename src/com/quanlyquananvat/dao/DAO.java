package com.quanlyquananvat.dao;

import java.util.List;

public abstract class DAO<EntityType, KeyType> {

    public abstract void insert(EntityType entiTy);

    public abstract void update(EntityType entiTy);

    public abstract void delete(KeyType id);

    public abstract List<EntityType> selectAll();

    public abstract EntityType selectById(KeyType id);

    public abstract List<EntityType> selectBySQL(String sql, Object... args);

}
