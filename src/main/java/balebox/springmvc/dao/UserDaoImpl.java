package balebox.springmvc.dao;

import balebox.springmvc.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers(String userlist) {
        return entityManager.createQuery("From User", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void editUser(User user, Long id) {
        User newDataUser = entityManager.find(User.class,id);
        newDataUser.setName(user.getName());
        newDataUser.setLastName(user.getLastName());
        newDataUser.setAge(user.getAge());
        newDataUser.setEmail(user.getEmail());
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }
}

