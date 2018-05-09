package com.nimtego.morseapplication.presenter;

import android.content.Context;
import android.content.res.Resources;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;


import com.nimtego.morse.api.WordConversion;
import com.nimtego.morse.impl.EnglishNoStreamWordConversion;
import com.nimtego.morseapplication.R;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class MorsePresenter extends AbstractBasePresenter {
    private WordConversion mWordConversion;

    MyTask mt;
    String text;
    int sum = 0;

    @Override
    public void onClick(View view) {
        if (mWordConversion == null)
            mWordConversion = new EnglishNoStreamWordConversion();

        this.text = commonView.getText();
        Log.v("Test text in commonView - ", text);
        byte[] bytes = mWordConversion.convert(text);
        commonView.setText(Arrays.toString(bytes));
        startFlashLight(mWordConversion.convert(text));

    }

    private void startFlashLight(byte ... symbol) {
        final CameraManager cameraManager = (CameraManager) ((AppCompatActivity) commonView)
                .getSystemService(Context.CAMERA_SERVICE);
        try {
            final String cameraId = cameraManager.getCameraIdList()[0];
            try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (byte b : symbol){
                                try {
                                    Thread.sleep(400);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    if (b == 1) {
                                        cameraManager.setTorchMode(cameraId, true);
                                        try {
                                            Thread.sleep(300);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                    if (b == 2) {
                                        cameraManager.setTorchMode(cameraId, true);
                                        try {
                                            Thread.sleep(500);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    if (b == 3) {
                                        try {
                                            Thread.sleep(800);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    cameraManager.setTorchMode(cameraId, false);
                                } catch (CameraAccessException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();
            } catch (IllegalArgumentException e) {
                commonView.toast(e.getMessage());
            }
        } catch (CameraAccessException ignored) {
        }

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
    class MyTask extends AsyncTask<Void, Void, Byte> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Byte doInBackground(Void... params) {
            byte[] b = mWordConversion.convert(text);
            byte current = 7;
            for (int i = (sum++); i < b.length; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                current = b[i];
            }
            return current;
        }
        @Override
        protected void onPostExecute(Byte result) {
            super.onPostExecute(result);
            commonView.nextSymbol(result);
        }

    }
}


