package com.mfcms.laba.common.services;

import java.io.PrintStream;
import java.util.Scanner;

public class IOManager {
    private Scanner inputScanner;
    private PrintStream printStream;

    public IOManager(Scanner inputScanner, PrintStream printStream) {
        this.inputScanner = inputScanner;
        this.printStream = printStream;
    }

    public Scanner in() {
        return inputScanner;
    }

    public PrintStream out() {
        return printStream;
    }
}
