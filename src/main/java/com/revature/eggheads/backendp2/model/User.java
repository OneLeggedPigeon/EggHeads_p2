package com.revature.eggheads.backendp2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue()
    private int id;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Pet> pets;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "incubator_id", referencedColumnName = "id")
    private Incubator incubator;
}
