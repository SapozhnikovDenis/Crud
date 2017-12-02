package entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement(name = "user")
public class User implements Serializable{
    private Long id;
    private String nickname;
    private String password;
    private String firstName;
    private String lastName;
    private Date birthday;

    public User(){}

    public User(Long id, String nickname, String password, String firstName, String lastName, Date birthday) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public User(String nickname, String password, String firstName, String lastName, Date birthday) {
        this.nickname = nickname;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public Long getId() {
        return id;
    }
    @XmlElement
    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }
    @XmlElement
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }
    @XmlElement
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }
    @XmlElement
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    @XmlElement
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }
    @XmlElement
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
