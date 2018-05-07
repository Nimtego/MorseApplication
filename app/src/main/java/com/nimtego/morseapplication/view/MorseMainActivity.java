package com.nimtego.morseapplication.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.nimtego.morseapplication.R;
import com.nimtego.morseapplication.presenter.MorsePresenter;
import com.nimtego.morseapplication.presenter.Presenter;


public class MorseMainActivity extends AppCompatActivity implements CommonView{
    private EditText mEditText;
    private Presenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new MorsePresenter();
        mPresenter.attach(this);
        setContentView(com.nimtego.morseapplication.R.layout.activity_morse_main);
        findViewById(R.id.button_convert).setOnClickListener(mPresenter);
        mEditText = findViewById(R.id.editText);
    }

    @Override
    public void toast(String message) {

    }

    @Override
    public void alarm(String message) {

    }

    @Override
    public String getText() {
        return mEditText.getText().toString();
    }

    @Override
    public void setText(String text) {
        mEditText.clearComposingText();
        mEditText.setText(text);
    }

    @Override
    public void setImage(int number, int id) {
        if (number != 7) {
            switch (number) {
                case 0:
                    ((ImageView) findViewById(R.id.image_0)).setImageResource(id);
                    break;
                case 1:
                    ((ImageView) findViewById(R.id.image_1)).setImageResource(id);
                    break;
                case 2:
                    ((ImageView) findViewById(R.id.image_2)).setImageResource(id);
                    break;
                case 3:
                    ((ImageView) findViewById(R.id.image_3)).setImageResource(id);
                    break;
            }
        }
        else {
            findViewById(R.id.button_convert).performClick();
        }
    }
}
