package blog.bloggingApp.service;

import blog.bloggingApp.entity.User;
import blog.bloggingApp.payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto, Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
    public User getUserFromOutside();
}
