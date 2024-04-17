package balebox.springmvc.dao;

import balebox.springmvc.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers(String usernum);

    void addUser(User user);

    void deleteUser(Long id);

    void editUser(User user,Long id);

    User getUserById(Long id);
}
