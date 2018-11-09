package repository.generic;

import java.util.List;
import java.util.Map;

public interface GenericRepository<T> {

    void insert(T entity);

    void insertBatch(List<T> entities);

    void updateBatch(List<T> entities);

    void insertOrReplace(T entity);

    void update(T entity, String... properties);

    List<T> searchAll();

    List<T> searchAlikeColumn(Map<String, String> keyValues);

    List<T> searchExactColumn(List<String> values, String columnName);

    T findById(T entity);

    T delete(T entity);

    void clearData();
}
