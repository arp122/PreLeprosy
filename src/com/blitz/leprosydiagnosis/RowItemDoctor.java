package com.blitz.leprosydiagnosis;

public class RowItemDoctor {
    private String name,description;
    public RowItemDoctor(String name,String description) {
        this.name=name;
        this.description=description;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDesciption() {
        return description;
    }
    public void setDescription(String descrption) {
        this.description = descrption;
    }
    
    @Override
    public String toString() {
        return name+" "+description;
    }
}
