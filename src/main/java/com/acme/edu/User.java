package com.acme.edu;

import java.io.Serializable;
import javax.enterprise.context.Dependent;

//@Dependent
public class User implements Serializable {
    private int id = 1;
    private String name = "test name";

    public User() {}
    User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
