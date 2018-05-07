package com.nimtego.morseapplication.presenter;

import android.os.AsyncTask;
import android.view.View;


import com.nimtego.morseapplication.R;

import java.util.Random;
import java.util.concurrent.TimeUnit;


public class MorsePresenter extends AbstractBasePresenter {
/*    private WordConversion mWordConversion;*/

    MyTask mt;

    @Override
    public void onClick(View view) {
/*        if (mWordConversion == null)
            mWordConversion = new EnglishWordConversion();*/

        String text = commonView.getText();
        byte[] morse = new byte[10];
        for (int i = 0; i < morse.length; i++) {
            morse[i] = (byte) new Random().nextInt(4);
        }
        mt = new MyTask();
        mt.execute();

        /*commonView.setText(new String(morse));*/
    }

    private int randId() {
        Random random = new Random();
        switch (random.nextInt(3)) {
            case 0:
                return R.drawable.dot;
            case 1:
                return R.drawable.dash;
            case 2:
                return R.drawable.space;
                default:
                    return R.drawable.space;
        }
    }
    class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            commonView.setImage(0, randId());
            commonView.setImage(1, randId());
            commonView.setImage(2, randId());
            commonView.setImage(3, randId());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            commonView.setImage(7,0);
            /*tvInfo.setText("End");*/
        }
    }
}


