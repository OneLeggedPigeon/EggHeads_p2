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
public class Incubator {

    @Id
    @GeneratedValue
    private int id;

    private int capacity = 10;

    @OneToOne(mappedBy = "incubator")
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "incubator")
    private List<Egg> eggs;
}
