package com.group4.www;

import com.group4.www.core.EngineImpl;
import com.group4.www.core.contacts.Engine;

public class StartUp {
    public static void main(String[] args) {
        Engine engine = new EngineImpl();
        engine.start();
    }
}