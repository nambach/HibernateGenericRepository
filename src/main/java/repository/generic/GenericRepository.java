package repository.generic;

import java.util.List;
import java.util.Map;

public interface GenericRepository<T> {

    void insertOrReplace(T entity);

    void insertOrReplaceBatch(List<T> entities);

    void updateProperties(T entity, String... properties);

    List<T> searchAll();

    List<T> searchAlikeColumn(Map<String, String> keyValues);

    List<T> searchExactColumn(List<String> values, String columnName);

    T findById(T entity);

    T findById(String id);

    T delete(T entity);

    void truncateTable();
}
