package com.mfcms.laba.common.model.objects;

import java.io.Serializable;

public class Label implements Serializable {
    private String name;

    // Required for JAXB
    @SuppressWarnings("unused")
    private Label() {
    }
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