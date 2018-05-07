package com.nimtego.morseapplication.presenter;

import android.view.View;

import com.nimtego.morseapplication.view.CommonView;

public interface Presenter<T extends CommonView> extends View.OnClickListener {
    void attach(T view);
    void detach();
}
