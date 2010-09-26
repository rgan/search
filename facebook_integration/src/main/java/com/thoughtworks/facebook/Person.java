package com.thoughtworks.facebook;

import java.util.List;
import java.util.Set;

public class Person {
    private String id;
    private String name;
    private List<Like> likes;
    private Set<Person> friends;

    public Person(String id, String name, List<Like> likes,
                  Set<Person> friends) {
        this.id = id;
        this.name = name;
        this.likes = likes;
        this.friends = friends;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public Set<Person> getFriends() {
        return friends;
    }

    public String getNameOrId() {
        return name == null ? id : name;
    }
}
