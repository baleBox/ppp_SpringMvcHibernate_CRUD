package balebox.springmvc.service;

import balebox.springmvc.model.User;

import java.util.List;


public interface UserService {
    List<User> getAllUsers(String usernum);

    void addUser(User user);

    void deleteUser(Long id);

    void editUser(User user, Long id);

    User getUserById(Long id);
}
