package com.revature.eggheads.backendp2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

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

    private int capacity = 9;

    @OneToMany(mappedBy = "incubator", orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Egg> eggs;
}
