package com.tinkoff;

import core.InitialDriver;
import elements.Button;
import elements.ElementProperties;
import elements.Fields;
import elements.Windows;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import pages.MainPage;
import pages.MyPage;

public abstract class TestBase {
    protected Windows windows;
    protected Button button;
    protected Fields fields;
    protected MainPage mainPage;
    protected ElementProperties elementProperties;
    protected MyPage myPage;

    @BeforeMethod
    public void setUp() {
        windows = new Windows();
        button = new Button();
        fields = new Fields();
        mainPage = new MainPage();
        elementProperties = new ElementProperties();
        myPage = new MyPage();
    }

    @AfterTest
    public void tearDown() {
        InitialDriver.getInstance().destroy();
    }
}
