package br.com.fiap.appglasseek.service;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import br.com.fiap.appglasseek.R;
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
    static String operation;
    private String URL;
    private Context context;
    private ACProgressFlower dialog;

    public UserService(Context context, String operation) {
        this.context = context;
        this.operation = operation;
        this.URL = "http://" + context.getString(R.string.ip_address) + ":6085/rest/00User";
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

        if (operation.equals("GET")) {
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
        } else if (operation.equals("CREATE")) {
            try {
                JSONObject jsonBody = new JSONObject();
                jsonBody.put("name", params[0]);
                jsonBody.put("lastName", params[1]);
                jsonBody.put("userId", params[2]);
                jsonBody.put("phone", params[3]);
                jsonBody.put("email", params[4]);
                jsonBody.put("password", params[5]);

                OkHttpClient client = new OkHttpClient();

                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, jsonBody.toString());
                Request request = new Request.Builder()
                        .url(URL + "/createUser")
                        .post(body)
                        .addHeader("Content-Type", "application/json")
                        .addHeader("cache-control", "no-cache")
                        .build();


                Response response = client.newCall(request).execute();

                jsonObject = new Gson().fromJson(response.body().string(), JsonObject.class);

                if (response.isSuccessful()) {
                    usuario.setNome(params[0]);
                    usuario.setSobrenome(params[1]);
                    usuario.setCpf(params[2]);
                    usuario.setTelefone(params[3]);
                    usuario.setEmail(params[4]);
                    usuario.setSenha(params[5]);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (operation.equals("UPDATE")) {
            //
        } else if (operation.equals("DELETE")) {

            try {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("http://192.168.1.139:6085/rest/00User/deleteUser?email=" + StaticData.UserData.getUsuario().getEmail())
                        .delete(null)
                        .build();

                Response response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    StaticData.UserData.setUsuario(new Usuario());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return usuario;
    }

    @Override
    protected void onPostExecute(Usuario usuario) {
        dialog.hide();

        if (operation.equals("GET")) {
            if (usuario.getCpf() == null) {
                Toast.makeText(context, "Usuário ou senha inválido!", Toast.LENGTH_SHORT).show();
            } else {
                StaticData.UserData.setUsuario(usuario);
                LoginUtility.logIn(context.getApplicationContext(), usuario.getEmail());
                Toast.makeText(context, "Conectado com sucesso!", Toast.LENGTH_SHORT).show();

            }
        } else if (operation.equals("CREATE")) {
            if (usuario.getCpf() == null) {
                StaticData.UserData.setUsuario(usuario);
            }

        } else if (operation.equals("UPDATE")) {
            //
        } else if (operation.equals("DELETE")) {
            if (usuario.getCpf() == null) {
                LoginUtility.logOut(context);
                Toast.makeText(context, "Usuário deletado, você foi desconectado!", Toast.LENGTH_SHORT).show();
                // TransportView.Transport.getFragmentManager().beginTransaction().replace(android.R.id.content,new InicioFragment()).commit();
            }
        }
    }
}
