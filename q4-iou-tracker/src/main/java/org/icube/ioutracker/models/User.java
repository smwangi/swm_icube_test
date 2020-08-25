package org.icube.ioutracker.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.icube.ioutracker.util.UniqueName;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name="users",uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name",unique = true)
    @NotEmpty(message = "Please provide a name.")
    private String name;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "lenderId")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<IOU>owed_by;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "borrowerId")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<IOU>owes;


    public User(){

    }

    public User(String name){
        this.name = name;
    }

    public User(long id,String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<IOU> getOwed_by() {
        return owed_by;
    }

    public Set<IOU> getOwes() {
        return owes;
    }

    public long getId() {
        return id;
    }

}
