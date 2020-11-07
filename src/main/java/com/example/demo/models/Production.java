package com.example.demo.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Production {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "production", cascade = CascadeType.ALL)
    private Set<Scene> scenes = new HashSet<>();

    @OneToMany(mappedBy = "production", cascade = CascadeType.ALL)
    private Set<Character> characters = new HashSet<>();

    public void addScene(Scene s) {
        this.getScenes().add(s);
        s.setProduction(this);
    }
    
    public void addCharacter(Character c) {
        this.getCharacters().add(c);
        c.setProduction(this);
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

    public Set<Scene> getScenes() {
        return scenes;
    }

    public void setScenes(Set<Scene> scenes) {
        this.scenes = scenes;
    }

    public Set<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(Set<Character> characters) {
        this.characters = characters;
    }

}
