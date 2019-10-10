package br.com.fiap.appglasseek.service;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.activity.SplashActivity;
import br.com.fiap.appglasseek.dao.StaticData;
import br.com.fiap.appglasseek.model.Usuario;
import cc.cloudist.acplibrary.ACProgressFlower;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserService extends AsyncTask<String, Void, Usuario> implements Service {
    static String operation;
    private String URL;
    private Context context;
    private ACProgressFlower dialog;
    private Boolean success;
    private Activity activity;

    public UserService(Context context, String operation) {
        this.context = context;
        this.operation = operation;
        this.URL = Service.URL + "/00User";
        this.success = Service.success;
    }

    public UserService(Context context, String operation, Activity activity) {
        this.context = context;
        this.operation = operation;
        this.URL = Service.URL + "/00User";
        this.success = Service.success;
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
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

                response.close();
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
                        .build();

                Response response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    usuario.setNome(params[0]);
                    usuario.setSobrenome(params[1]);
                    usuario.setCpf(params[2]);
                    usuario.setTelefone(params[3]);
                    usuario.setEmail(params[4]);
                    usuario.setSenha(params[5]);

                    StaticData.UserData.setUsuario(usuario);
                }

                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (operation.equals("UPDATE")) {
            try {
                JSONObject jsonBody = new JSONObject();
                jsonBody.put("name", params[0]);
                jsonBody.put("lastName", params[1]);
                jsonBody.put("userId", params[2]);
                jsonBody.put("phone", params[3]);
                jsonBody.put("email", StaticData.UserData.getUsuario().getEmail());
                jsonBody.put("newEmail", (StaticData.UserData.getUsuario().getEmail().equals(params[4]) ? null : params[4]));
                jsonBody.put("password", params[5]);

                OkHttpClient client = new OkHttpClient();

                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, jsonBody.toString());
                Request request = new Request.Builder()
                        .url(URL + "/updateUser")
                        .put(body)
                        .addHeader("Content-Type", "application/json")
                        .build();

                Response response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    usuario.setNome(params[0]);
                    usuario.setSobrenome(params[1]);
                    usuario.setCpf(params[2]);
                    usuario.setTelefone(params[3]);
                    usuario.setEmail(params[4]);
                    usuario.setSenha(params[5]);

                    StaticData.UserData.setUsuario(usuario);
                    success = true;
                }

                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (operation.equals("DELETE")) {
            try {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(URL + "/deleteUser?email=" + StaticData.UserData.getUsuario().getEmail())
                        .delete(null)
                        .build();

                Response response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    StaticData.UserData.setUsuario(new Usuario());
                }

                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return usuario;
    }

    @Override
    protected void onPostExecute(Usuario usuario) {
        if (operation.equals("GET")) {
            if (usuario.getCpf() == null) {
                if (this.context instanceof SplashActivity) {
                    Toast.makeText(context, "Não foi possível te reconectar, tente realizar o login novamente!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Usuário ou senha inválido!", Toast.LENGTH_SHORT).show();
                }

            } else {
                StaticData.UserData.setUsuario(usuario);
                LoginUtility.logIn(context.getApplicationContext(), usuario.getEmail(), usuario.getSenha());

                if (this.context instanceof SplashActivity) {
                    Toast.makeText(context, "Você foi reconectado!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();

                    NavigationView navigationView = activity.findViewById(R.id.nav_view);
                    View headerView = navigationView.getHeaderView(0);

                    TextView nomeUsuario = headerView.findViewById(R.id.txt_usuario_nome);
                    nomeUsuario.setText(StaticData.UserData.getUsuario().getNome() + " " + StaticData.UserData.getUsuario().getSobrenome());

                    TextView emailUsuario = headerView.findViewById(R.id.txt_usuario_email);
                    emailUsuario.setText(StaticData.UserData.getUsuario().getEmail());
                }

                AddressService addressService = new AddressService(context, "GET");
                addressService.execute(StaticData.UserData.getUsuario().getEmail());

                FavoritesService favoritesService = new FavoritesService(context, "GET");
                favoritesService.execute(StaticData.UserData.getUsuario().getEmail());

                CarrinhoService carrinhoService = new CarrinhoService(context, "GET");
                carrinhoService.execute(StaticData.UserData.getUsuario().getEmail());
            }
        } else if (operation.equals("CREATE")) {
            if (usuario.getCpf() == null) {
                StaticData.UserData.setUsuario(usuario);
                Toast.makeText(context, "Não foi possível realizar o cadastro.\nTente novamente!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Registro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                LoginUtility.logIn(context.getApplicationContext(), usuario.getEmail(), usuario.getSenha());
            }
        } else if (operation.equals("UPDATE")) {
            if (success) {
                Toast.makeText(context, "Dados do usuário alterados com sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Não foi possível alterar os dados do usuário", Toast.LENGTH_SHORT).show();
            }
        } else if (operation.equals("DELETE")) {
            if (usuario.getCpf() == null) {
                LoginUtility.logOut(context);
                Toast.makeText(context, "Usuário deletado, você foi desconectado!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
