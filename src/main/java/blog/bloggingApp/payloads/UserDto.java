package blog.bloggingApp.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDto {

    private int id;
    @NotEmpty
    @Size(min = 4, message ="Username must be minimum 4 characters!" )
    private String name;

    @NotEmpty
    @Size(min = 4,max = 8,message = "password must be min 4 and max 8 characters")
    private String password;

    @Email(message = "Email address is not valid")
    private String email;

    @NotEmpty
    private String about;

    public UserDto() {
        // This is a no-argument constructor
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
