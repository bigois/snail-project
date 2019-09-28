package br.com.fiap.appglasseek.service;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;

import com.android.volley.toolbox.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.HttpURLConnection;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import retrofit2.http.HTTP;

public class UserService extends AsyncTask<String, Void, Void> {
    static final String IP_ADDRESS = "192.168.1.139";
    static final String URL = "https://" + IP_ADDRESS + ":6085/rest/00User";

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
    protected Void doInBackground(String... params) {
        StringBuffer stringBuffer = new StringBuffer();
        JsonObject response = new JsonObject();

        try {
            URL url = new URL(URL + "/getUser");

            JSONObject request = new JSONObject();
            request.put("user", "79779558031");
            request.put("password", "@bananadepija");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("POST");

            connection.setDoOutput(true);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = request.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            InputStream stream = connection.getInputStream();

            BufferedReader rd = new BufferedReader(new InputStreamReader(stream));
            String line = "";
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }

            response = new Gson().fromJson(stringBuffer.toString(), JsonObject.class);
        } catch (Exception e) {
            // TODO:Handle exception
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        dialog.hide();
    }
}
