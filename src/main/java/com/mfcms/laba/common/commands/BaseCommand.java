package com.mfcms.laba.common.commands;

public abstract class BaseCommand {
    private String name;
    private String description;

    public BaseCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
