package com.nimtego.morseapplication.view;

public interface CommonView {
    void toast(String message);
    void alarm(String message);
    String getText();
    void setText(String text);
    void nextSymbol(byte symbol);
}
