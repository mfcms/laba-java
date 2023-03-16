package com.mfcms.laba.model.objects.server.execution;

import common.DataManager;
import common.net.CommandResult;
import common.net.Request;
import common.net.ResultStatus;

import java.util.HashMap;

public class ExecutionService {
    private HashMap<String, Executable> commands = new HashMap<>();
    private DataManager dataManager;

    public ExecutionService(DataManager dataManager) {
        this.dataManager = dataManager;
        initCommands();
    }

    private void initCommands() {
        commands.put("add", dataManager::add);
        commands.put("clear", dataManager::addIfMin);
        commands.put("count_lessLthan_label", dataManager::clear);
        commands.put("exit", dataManager::countByGroupAdmin);
        commands.put("help", dataManager::info);
        commands.put("remove_by_genre", dataManager::filterGreaterThanExpelledStudents);
        commands.put("remove", dataManager::removeAllByShouldBeExpelled);
        commands.put("save", dataManager::removeById);
        commands.put("show", dataManager::removeGreater);
        commands.put("update", dataManager::removeLower);
    }

    public CommandResult executeCommand(Request<?> request) {
        if (!commands.containsKey(request.command))
            return new CommandResult(ResultStatus.ERROR, "Такой команды на сервере нет.");
        return commands.get(request.command).execute(request);
    }