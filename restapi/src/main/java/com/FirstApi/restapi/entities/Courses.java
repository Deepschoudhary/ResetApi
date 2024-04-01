package com.FirstApi.restapi.entities;



public class Courses {

    private long id;
    private String name;
    private String description;

    public Courses(long id, String title, String description) {
        this.id = id;
        this.name = title;
        this.description = description;
    }

    public Courses(){
        super();
    }

    @Override
    public String toString() {
        return "Courses{" +
                "id=" + id +
                ", title='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public int getId() {
        return (int) id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
