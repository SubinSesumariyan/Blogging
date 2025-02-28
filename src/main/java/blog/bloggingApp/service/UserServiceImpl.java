package blog.bloggingApp.service;

import blog.bloggingApp.entity.User;
import blog.bloggingApp.exception.ResourceNotFoundException;
import blog.bloggingApp.payloads.Product;
import blog.bloggingApp.payloads.UserDto;
import blog.bloggingApp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user=this.dtoToUser(userDto);
        User savedUser=this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id", userId));
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());

        User updatedUser=this.userRepo.save(user);
        UserDto userDto1=new UserDto();
        userDto1.setId(updatedUser.getId());
        userDto1.setName(updatedUser.getName());
        userDto1.setPassword(updatedUser.getPassword());
        userDto1.setEmail(updatedUser.getEmail());
        userDto1.setAbout(updatedUser.getAbout());
        return userDto1;

    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id", userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users=this.userRepo.findAll();
        List<UserDto> userDtos=new ArrayList<>();
        for(int i=0; i< users.size(); i++){
            UserDto userDto=new UserDto();
            userDto.setId(users.get(i).getId());
            userDto.setName(users.get(i).getName());
            userDto.setPassword(users.get(i).getPassword());
            userDto.setEmail(users.get(i).getEmail());
            userDto.setAbout(users.get(i).getAbout());
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id", userId));
        this.userRepo.delete(user);

    }
    private User dtoToUser(UserDto userDto){
        User user=new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        return user;
    }
    public UserDto userToDto(User user){
        UserDto userDto=new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        userDto.setAbout(user.getAbout());
        return userDto;
    }

    public User getUserFromOutside() {
        String url = "http://localhost:8080/api/users/2";
        return restTemplate.getForObject(url, User.class);

    }
}
