import model.Product;
import org.hibernate.SessionFactory;
import repository.generic.impl.GenericRepositoryImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepositoryImpl extends GenericRepositoryImpl<Product> {

    public ProductRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void updateName(Product product) {
        updateProperties(product, "name");
    }

    public void updateCategory(Product product) {
        updateProperties(product, "categoryId");
    }

    public List<Product> getByNameAndId(List<String> names, List<String> ids) {
        Map<String, List<String>> keyValues = new HashMap<>();
        keyValues.put("id", ids);
        keyValues.put("name", names);
        return searchExactColumn(keyValues, "AND", false);
    }
}
