package repository;

import model.User;
import repository.generic.GenericRepository;

import java.util.List;

public interface UserRepository extends GenericRepository<User> {

    User checkLogin(String username, String password);

    List<User> getByNameAndId(List<String> names, List<String> ids);
}
