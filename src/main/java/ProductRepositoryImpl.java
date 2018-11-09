import model.Product;
import org.hibernate.SessionFactory;
import repository.generic.impl.GenericRepositoryImpl;

public class ProductRepositoryImpl extends GenericRepositoryImpl<Product> {

    public ProductRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void updateName(Product product) {
        update(product, "name");
    }

    public void updateCategory(Product product) {
        update(product, "categoryId");
    }
}
