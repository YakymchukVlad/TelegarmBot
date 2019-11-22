package com.khnu.yakymchuk.reader.impl;

import com.khnu.yakymchuk.reader.IDataReader;
import com.khnu.yakymchuk.reader.IDisplayData;

import java.util.Scanner;

public class ConsoleDataReader implements IDataReader {

    private IDisplayData consoleDisplay;

    public ConsoleDataReader(IDisplayData consoleDisplay) {
        this.consoleDisplay = consoleDisplay;
    }

    @Override
    public String getUserInfo(String message) {
        consoleDisplay.showInfo(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}
