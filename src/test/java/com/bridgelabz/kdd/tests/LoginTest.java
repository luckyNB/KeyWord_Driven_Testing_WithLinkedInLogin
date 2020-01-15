package com.bridgelabz.kdd.tests;

import com.bridgelabz.kdd.base.Base;
import com.bridgelabz.kdd.keyboardengine.KeyEngine;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends Base {
    KeyEngine engine;

    public LoginTest() throws IOException {
        property = Base.init_property();
    }

    @BeforeMethod
    public void setUp() {
        engine = new KeyEngine();
    }

    @Test
    public void loginTest() {
        engine.startExecution(property.getProperty("loginSheet"));
    }
}
