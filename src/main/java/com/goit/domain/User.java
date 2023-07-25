package com.goit.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.goit.model.Note;
import com.goit.model.Role;

@Entity
@Table(name = "users")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = "passwordHash")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Size(min = 5, max = 50)
    private String username;

    @Size(min = 8, max = 100)
    private String passwordHash;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Note> notes = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User() {}

    public User(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }
}