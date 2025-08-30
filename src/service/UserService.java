package service;

import Model.User;
import dao.UserDAO;

public class UserService {
    UserDAO dao = new UserDAO();

    public boolean registerUser(User user) {
        return dao.registerUser(user);
    }

    public User loginUser(String email, String password) {
        return dao.loginUser(email, password);
    }
}