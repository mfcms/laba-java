package com.mfcms.laba.model.objects.server;

import client.commands.Command;
import common.model.Person;
import common.model.StudyGroup;
import common.net.CommandResult;
import common.net.Request;

import java.io.IOException;

public abstract class DataManager {
    protected abstract Integer generateNextId();
    public abstract CommandResult add(Request<?> request);
    public abstract CommandResult clear(Request<?> request);
    public abstract CommandResult count_less_than_label(Request<?> request);
    public abstract CommandResult exit(Request<?> request);
    public abstract CommandResult help(Request<?> request);
    public abstract CommandResult remove_by_genre(Request<?> request);
    public abstract CommandResult remove(Request<?> request);
    public abstract CommandResult save(Request<?> request);
    public abstract CommandResult update(Request<?> request);
    public abstract CommandResult show(Request<?> request);
    public void save() {}
}