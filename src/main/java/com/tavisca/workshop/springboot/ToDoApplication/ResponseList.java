package com.tavisca.workshop.springboot.ToDoApplication;

import java.util.List;

public class ResponseList {
    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    List<String> list;
}