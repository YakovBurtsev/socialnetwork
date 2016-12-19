package ru.yakovburtsev.socialnetwork.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * The class represents a user of the social network.
 */

@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.GET_BY_EMAIL, query = "SELECT u FROM User u WHERE u.email=?1"),
        @NamedQuery(name = User.GET_BY_NAME_AND_SURNAME, query = "SELECT u FROM User u WHERE u.name LIKE :name AND u.surname LIKE :surname")
})
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_email_unique")})
public class User implements Serializable {
    public static final String DELETE = "User.delete";
    public static final String GET_BY_EMAIL = "User.getByEmail";
    public static final String GET_BY_NAME_AND_SURNAME = "User.getByNameAndSurname";

    @Id
    @SequenceGenerator(name = "users_seq", sequenceName = "users_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @Access(value = AccessType.PROPERTY)
    private Long id;

    @NotEmpty
    @SafeHtml
    @Column(name = "name", nullable = false)
    private String name;

    @NotEmpty
    @SafeHtml
    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(name = "city")
    private String city;

    @SafeHtml
    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotEmpty
    private String email;

    @SafeHtml
    @Length(min = 6)
    @Column(name = "password", nullable = false)
    @NotEmpty
    private String password;

    public User() {
    }

    private User(Builder builder) {
        id = builder.id;
        name = builder.name;
        surname = builder.surname;
        birthday = builder.birthday;
        sex = builder.sex;
        city = builder.city;
        email = builder.email;
        password = builder.password;
    }

    public User(User u) {
        id = u.getId();
        name = u.getName();
        surname = u.getSurname();
        birthday = u.getBirthday();
        sex = u.getSex();
        city = u.getCity();
        email = u.getEmail();
        password = u.getPassword();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public boolean isNew() {
        return getId() == null;
    }

    @Override
    public String toString() {
        return "User(" +
                "id=" + id +
                ", name=" + name +
                ", surname=" + surname +
                ", birthday=" + birthday +
                ", sex=" + sex +
                ", city=" + city +
                ", email=" + email +
                ')';
    }

    public static class Builder {
        private Long id;
        private String name;
        private String surname;
        private LocalDate birthday;
        private Sex sex;
        private String city;
        private String email;
        private String password;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder birthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder sex(Sex sex) {
            this.sex = sex;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
