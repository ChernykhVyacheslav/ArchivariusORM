package orm.archivarius.entities;

import orm.archivarius.annotations.Column;
import orm.archivarius.annotations.Entity;
import orm.archivarius.annotations.Id;


@Entity
public class Animal {
    @Id
    @Column(value = "animal_id")
    private int id;

    @Column(value = "an_native_name")
    private String name;

    public Animal() {}
}