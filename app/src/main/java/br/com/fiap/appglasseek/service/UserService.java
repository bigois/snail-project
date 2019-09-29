package br.com.fiap.appglasseek.service;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import br.com.fiap.appglasseek.dao.StaticData;
import br.com.fiap.appglasseek.model.Usuario;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserService extends AsyncTask<String, Void, Usuario> {
    static final String IP_ADDRESS = "192.168.1.139";
    static final String URL = "http://" + IP_ADDRESS + ":6085/rest/00User";

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
    protected Usuario doInBackground(String... params) {
        JsonObject jsonObject;
        Usuario usuario = new Usuario();

        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("email", params[0]);
            jsonBody.put("password", params[1]);

            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, jsonBody.toString());
            Request request = new Request.Builder()
                    .url(URL + "/getUser")
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .build();

            Response response = client.newCall(request).execute();
            jsonObject = new Gson().fromJson(response.body().string(), JsonObject.class);

            if (response.isSuccessful()) {
                usuario.setStatus(jsonObject.get("status").getAsString());
                usuario.setSenha(jsonObject.get("password").getAsString());
                usuario.setTelefone(jsonObject.get("phone").getAsString());
                usuario.setCpf(jsonObject.get("userId").getAsString());
                usuario.setNome(jsonObject.get("name").getAsString());
                usuario.setSobrenome(jsonObject.get("lastName").getAsString());
                usuario.setEmail(jsonObject.get("email").getAsString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuario;
    }

    @Override
    protected void onPostExecute(Usuario usuario) {
        dialog.hide();

        if (usuario.getCpf() == null) {
            Toast.makeText(context, "Usuário ou senha inválido!", Toast.LENGTH_SHORT).show();
        } else {
            StaticData.UserData.setUsuario(usuario);
            LoginUtility.logIn(context, usuario.getEmail());
            ((Activity) context).finish();
        }
    }
}
