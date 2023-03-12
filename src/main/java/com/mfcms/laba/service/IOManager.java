package com.mfcms.laba.service;

import java.io.OutputStreamWriter;
import java.util.Scanner;

public class IOManager {
    private Scanner inputScanner;
    private OutputStreamWriter outputStreamWriter;

    public IOManager(Scanner inputScanner, OutputStreamWriter outputStreamWriter) {
        this.inputScanner = inputScanner;
        this.outputStreamWriter = outputStreamWriter;
    }

    public Scanner in() {
        return inputScanner;
    }

    public OutputStreamWriter out() {
        return outputStreamWriter;
    }
}
