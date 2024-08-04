package com.demo.todo.model;

import java.sql.Date;
import java.time.LocalDate;

public class ToDo {
    private String username;
    private String description;
    private LocalDate targetDate;
    private boolean status;
    private long id;
    private String title;


    protected ToDo(){

    }

    public ToDo(long id,String title,String username,String description,LocalDate targetDate,boolean isDone){
        super();
        this.id=id;
        this.title=title;
        this.username=username;
        this.description=description;
        this.targetDate=targetDate;
        this.status=isDone;

    }

    public ToDo(String title,String username,String description,LocalDate targetDate,boolean isDone){
super();
this.title=title;
this.username=username;
this.description=description;
this.targetDate=targetDate;
this.status=isDone;



    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
