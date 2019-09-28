package br.com.fiap.appglasseek.service;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class UserService extends AsyncTask<String, Void, Void> {
    private Context context;
    private ACProgressFlower dialog;

    public UserService(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = new ACProgressFlower.Builder(context)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .fadeColor(Color.DKGRAY).build();
        dialog.show();
    }

    @Override
    protected Void doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        dialog.hide();
    }
}
