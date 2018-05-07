package com.nimtego.morseapplication.view.task;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

public class SimpleToastAlarm implements ToastAlarm {
    private final String LOG_TAG = "Simple Toast Alarm";

    private Context mContext;
    private AlertDialog.Builder alertDialog;

    public SimpleToastAlarm(Context context) {
        mContext = context;
        alertDialog = new AlertDialog.Builder(mContext);
    }

    @Override
    public void toastMessage(String message) {
        Log.v(LOG_TAG, " toast method");
        Toast toast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void alarmMessage(String message) {
        Log.v(LOG_TAG, " alarm method");
        alertDialog.setTitle(mContext.getResources().toString());  // TODO: 03.05.2018
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() { // TODO: 03.05.2018
            public void onClick(DialogInterface dialog, int arg1) {
                toastMessage("Yes To do");
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                toastMessage("Yes To do");
            }
        });
        alertDialog.setCancelable(true);
        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) { // TODO: 03.05.2018
                toastMessage("Cancel To do");
            }
        });
        alertDialog.show();
    }
}
