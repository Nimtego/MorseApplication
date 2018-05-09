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
    private ImageView[] imageViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new MorsePresenter();
        mPresenter.attach(this);
        setContentView(com.nimtego.morseapplication.R.layout.activity_morse_main);
        findViewById(R.id.button_convert).setOnClickListener(mPresenter);
        mEditText = findViewById(R.id.editText);
        imageViews = new ImageView[4];
        imageViews[0] = findViewById(R.id.image_0);
        imageViews[1] = findViewById(R.id.image_1);
        imageViews[2] = findViewById(R.id.image_2);
        imageViews[3] = findViewById(R.id.image_3);
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
        mEditText.setText(text);
    }

    @Override
    public void nextSymbol(byte symbol) {
            for (int i = 0; i < imageViews.length - 1; i++) {
                imageViews[i] = imageViews[i + 1];
            }
            imageViews[imageViews.length - 1].setImageResource(getImage(symbol));
    }

    private int getImage(byte b) {
        switch (b) {
            case 1:
                return R.drawable.dot;
            case 2:
                return R.drawable.dash;
            case 3:
                return R.drawable.space;
            default:
                return R.drawable.space;
        }
    }

}
