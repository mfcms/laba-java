package com.mfcms.laba.model.objects;

public class Label {
    private String name;

    private Label() {}
    public Label(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int compareTo(Label label){
        return name.compareTo(label.name);
    }
}