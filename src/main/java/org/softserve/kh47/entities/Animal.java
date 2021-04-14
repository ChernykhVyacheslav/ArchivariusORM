package org.softserve.kh47.entities;

import org.softserve.kh47.annotations.Column;
import org.softserve.kh47.annotations.Entity;
import org.softserve.kh47.annotations.Id;
import org.softserve.kh47.annotations.Table;

@Entity
@Table(value = "animals_table")
public class Animal {
    @Id
    @Column(value = "animal_id")
    private int id;

    @Column(value = "an_native_name")
    private String name;

    public Animal() {}
}