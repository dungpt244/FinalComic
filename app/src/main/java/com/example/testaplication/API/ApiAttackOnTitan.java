package com.example.testaplication.API;

import java.util.List;

public class ApiAttackOnTitan {
    private List<String> chapter1;
    private List<String> chapter2;
    private List<String> chapter3;
    private List<String> chapter4;

    public ApiAttackOnTitan(List<String> chapter1, List<String> chapter2, List<String> chapter3, List<String> chapter4) {
        this.chapter1 = chapter1;
        this.chapter2 = chapter2;
        this.chapter3 = chapter3;
        this.chapter4 = chapter4;
    }

    public List<String> getChapter1() {
        return chapter1;
    }

    public void setChapter1(List<String> chapter1) {
        this.chapter1 = chapter1;
    }

    public List<String> getChapter2() {
        return chapter2;
    }

    public void setChapter2(List<String> chapter2) {
        this.chapter2 = chapter2;
    }

    public List<String> getChapter3() {
        return chapter3;
    }

    public void setChapter3(List<String> chapter3) {
        this.chapter3 = chapter3;
    }

    public List<String> getChapter4() {
        return chapter4;
    }

    public void setChapter4(List<String> chapter4) {
        this.chapter4 = chapter4;
    }

    @Override
    public String toString() {
        return "APIBlackClover{" +
                "chapter1=" + chapter1 +
                ", chapter2=" + chapter2 +
                ", chapter3=" + chapter3 +
                ", chapter4=" + chapter4 +
                '}';
    }
}
