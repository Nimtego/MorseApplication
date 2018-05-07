package com.nimtego.morseapplication.presenter;

import android.support.v7.app.AppCompatActivity;

import com.nimtego.morseapplication.view.CommonView;
import com.nimtego.morseapplication.view.task.SimpleToastAlarm;
import com.nimtego.morseapplication.view.task.ToastAlarm;

public abstract class AbstractBasePresenter<T extends CommonView> implements Presenter<T> {
    protected T commonView;
    protected ToastAlarm toastAlarm;


    @Override
    public void attach(T view) {
        this.commonView = view;
        this.toastAlarm = new SimpleToastAlarm(
                ((AppCompatActivity)commonView).getApplicationContext());
    }

    @Override
    public void detach() {
        this.commonView = null;
    }
}
