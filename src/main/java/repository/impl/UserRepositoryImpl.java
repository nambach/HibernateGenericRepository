package repository.impl;

import model.User;
import org.hibernate.SessionFactory;
import repository.UserRepository;
import repository.generic.impl.GenericRepositoryImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepositoryImpl extends GenericRepositoryImpl<User> implements UserRepository {

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public User checkLogin(String username, String password) {
        User tmp = new User(username, "", "", "");

        User user = findById(tmp);
        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }

        return user;
    }

    public List<User> getByNameAndId(List<String> names, List<String> ids) {
        Map<String, List<String>> keyValues = new HashMap<>();
        keyValues.put("username", ids);
        keyValues.put("name", names);
        return searchAlikeColumn(keyValues, "OR", true);
    }
}
