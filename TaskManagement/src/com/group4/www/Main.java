package com.group4.www;

import com.group4.www.core.EngineImpl;
import com.group4.www.core.contacts.Engine;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Engine engine = new EngineImpl();
        engine.start();
    }
}