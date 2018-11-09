import model.Product;
import org.hibernate.SessionFactory;
import repository.generic.impl.GenericRepositoryImpl;

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
}
