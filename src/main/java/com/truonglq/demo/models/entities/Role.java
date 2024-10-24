package com.truonglq.demo.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.truonglq.demo.models.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Table(name = "roles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Enumerated(EnumType.STRING)
    RoleEnum name;

//    @ManyToMany(targetEntity = User.class,
//            mappedBy = "roles",
//            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
//    @JsonBackReference
//    Set<User> users;

//    @Column(name = "user_id")
//    String userId;
}
