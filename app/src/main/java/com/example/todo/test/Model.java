package com.example.todo.test;

public class Model {
    private String name;
    private String gender;

    public Model(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }
    public String getGender(){
        return gender;
    }
}
