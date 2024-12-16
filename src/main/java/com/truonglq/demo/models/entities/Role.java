package com.truonglq.demo.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.truonglq.demo.models.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, unique = true)
    RoleEnum name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "restricted_endpoints", joinColumns = @JoinColumn(name = "role_id"))
    @Column(name = "restricted_endpoint")
    Set<String> restrictedEndpoints = new HashSet<>();

    //    @ManyToMany(
//            fetch = FetchType.LAZY,
//            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
//            mappedBy = "authorities"
//    )
    @ManyToMany(mappedBy = "authorities")
    @JsonIgnore
    Set<User> users = new HashSet<>();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        return id != null && id.equals(((Role) obj).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String getAuthority() {
        return name.toString();
    }
}
