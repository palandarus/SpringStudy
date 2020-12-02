package com.geekbrains.spring.mvc.model;

public class Brand {

    private Long id;
    private String Title;
    private String Description;

    public Brand() {
    }

    public Brand(Long id, String title, String description) {
        this.id = id;
        Title = title;
        Description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
