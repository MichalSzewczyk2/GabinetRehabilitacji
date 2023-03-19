package http.service;

import db.client.DBClient;
import db.entities.UserEntity;
import db.repository.UserRepository;
import http.constants.HttpStatus;
import http.util.HttpException;
import users.Permissions;
import users.User;
import validator.Validator;

import java.io.ObjectInputFilter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

public class UserService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());
    private static final DBClient dbClient;

    static {
        dbClient = new DBClient(true);
    }

    private static final UserRepository userRepository = new UserRepository(dbClient);

    public synchronized ArrayList<User> getAllUsers() {
        return userRepository.toUserList(userRepository.getAllUsers());
    }

    public synchronized ArrayList<User> getUsersByPermission(Permissions permissions) {
        return userRepository.toUserList(userRepository.getUsersByPermissions(permissions));
    }

    public synchronized User getUser(int userId) {
        UserEntity user = userRepository.getUser(userId);
        if (user.equals(new UserEntity())) {
            return null;
        }
        return userRepository.toUser(user);
    }

    public synchronized User getUser(String email) {
        UserEntity user = userRepository.getUser(email);
        if (user.equals(new UserEntity())) {
            return null;
        }
        return userRepository.toUser(user);
    }

    public synchronized User addUser(Map<String, String> userData) {
        UserEntity userEntity = toUserEntity(userData);
        int userId = userRepository.insertUser(userEntity);
        userEntity.setUserId(userId);
        User user = userRepository.toUser(userEntity);
        return user;
    }

    public synchronized User updateUser(int userId, Map<String, String> userData) {
        User userBeforeUpdate = userRepository.toUser(userRepository.getUser(userId));
        UserEntity userEntity = toUserEntity(userData);
        userEntity.setUserId(userId);
        userRepository.updateUser(userEntity);
        User user = userRepository.toUser(userEntity);
        return user;
    }

    public synchronized void deleteUser(int userId) {
        userRepository.deleteUserById(userId);
    }

    private UserEntity toUserEntity(Map<String, String> userData) {
        try {
            if (!Validator.isValidMail(userData.get("email")) ||
                    !Validator.isValidPhone(userData.get("phoneNumber")) ||
                    !Validator.isValidString(userData.get("name")) ||
                    !Validator.isValidStringWithDash(userData.get("surname")) ||
                    !Validator.isValidAddress(userData.get("address")) ||
                    !Validator.isValidStringWithDashAndSpace(userData.get("city"))
            ) {
                throw new HttpException(HttpStatus.BAD_REQUEST, "Data did not pass validation");
            }
            UserEntity user = new UserEntity(
                    userData.get("name"),
                    userData.get("surname"),
                    userData.get("address"),
                    userData.get("city"),
                    Integer.parseInt(userData.get("phoneNumber")),
                    userData.get("email"),
                    userData.get("password"),
                    Permissions.valueOf(userData.get("permissions").toUpperCase(Locale.ROOT)));
            if (userData.get("userId") != null) {
                user.setUserId(Integer.parseInt(userData.get("userId")));
            }
            return user;
        } catch (NumberFormatException e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Invalid payload");
        }
    }
}
