package com.khnu.yakymchuk.reader.impl;

import com.khnu.yakymchuk.reader.IDisplayData;

public class ConsoleDisplay implements IDisplayData {

    @Override
    public void showInfo(String message) {
        System.out.println(message);
    }
}
