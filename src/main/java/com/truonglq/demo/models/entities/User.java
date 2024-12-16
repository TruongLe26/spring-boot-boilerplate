package com.truonglq.demo.models.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "username", unique = true, nullable = false)
    String username;

    @Column(name = "password", nullable = false)
    String password;

    // Removed FetchType.EAGER
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    @Builder.Default
    Set<Role> authorities = new HashSet<>(); // Added new HashSet<>()

    public void addRole(Role role) {
        this.authorities.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role) {
        this.authorities.remove(role);
        role.getUsers().remove(this);
    }

    public void removeRoles() {
        Iterator<Role> iterator = this.authorities.iterator();

        while (iterator.hasNext()) {
            Role role = iterator.next();

            role.getUsers().remove(this);
            iterator.remove();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        return id != null && id.equals(((User) obj).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }

//    @ManyToMany(targetEntity = Role.class,
//            fetch = FetchType.EAGER,
//            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH,  CascadeType.REFRESH})
//    @JoinTable(
//            name = "users_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//    )
//    @JsonManagedReference
//    Set<Role> roles;

//    @Column(name = "status", columnDefinition = "tinyint(1) default 1")
//    Boolean status = true;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return roles.stream()
//                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().toString()))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }

//    public boolean isEnabled() {
//        return Boolean.TRUE.equals(status);
//    }
}
