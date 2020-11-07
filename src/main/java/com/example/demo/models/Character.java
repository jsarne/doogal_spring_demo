package com.example.demo.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Production production;

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL)
    private Set<Look> characterLooks = new HashSet<>();
    
    public void addLook(Look l) {
        this.getLooks().add(l);
        l.setCharacter(this);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Production getProduction() {
        return production;
    }

    public void setProduction(Production production) {
        this.production = production;
    }

    public Set<Look> getLooks() {
        return characterLooks;
    }

    public void setLooks(Set<Look> looks) {
        this.characterLooks = looks;
    }

}
