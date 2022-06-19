package ru.dacha.mvc_exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@org.springframework.stereotype.Service
public class Service {

    public List<String> getTeacher() {
        return teacher;
    }

    public void setTeacher(List<String> teacher) {
        this.teacher = teacher;
    }

    List<String> teacher = new ArrayList<>();

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }
    String res ="RUB";

    public Service() {

    }




}
