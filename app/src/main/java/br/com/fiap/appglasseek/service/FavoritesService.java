package br.com.fiap.appglasseek.service;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.com.fiap.appglasseek.dao.StaticData;
import br.com.fiap.appglasseek.model.Favorito;
import br.com.fiap.appglasseek.model.Oculos;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FavoritesService extends AsyncTask<String, Void, Favorito> implements Service{
    static String operation;
    private String URL;
    private Context context;
    private ACProgressFlower dialog;
    private Boolean success;

    public FavoritesService(Context context, String operation) {
        this.context = context;
        this.operation = operation;
        this.URL = Service.URL + "/03Wish";
        this.success = Service.success;
    }

    @Override
    protected void onPreExecute() {
//        dialog = new ACProgressFlower.Builder(context)
//                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
//                .themeColor(Color.WHITE)
//                .fadeColor(Color.DKGRAY).build();
//        dialog.show();
    }

    @Override
    protected Favorito doInBackground(String... params) {
        JsonArray jsonArray;
        Favorito favoritos = new Favorito();

        try {
            if (operation.equals("GET")) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(URL + "?email=" + params[0])
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .build();

                Response response = client.newCall(request).execute();
                jsonArray = new Gson().fromJson(response.body().string(), JsonObject.class).getAsJsonArray("wishes");

                if(response.isSuccessful()){

                    for (int i = 0; i < jsonArray.size(); i++) {
                        Oculos oculos = new Oculos();
                        String codigoOculos;
                        String item;

                        JsonObject row = jsonArray.get(i).getAsJsonObject();

                        codigoOculos = row.get("code").getAsString();
                        //item = row.get("item").getAsString();

                        oculos = StaticData.OculosData.getOculosByCodigo(codigoOculos);

                        if(null != oculos){
                            favoritos.getOculos().add(oculos);
                            success = true;
                        }

                    }

                }
                response.close();

            }
            else if (operation.equals("CREATE")) {
                JsonObject jsonList = new JsonObject();

                JsonArray wishProperty = new JsonArray();

                JsonObject wishJson = new JsonObject();
                wishJson.addProperty("code", params[1]);

                wishProperty.add(wishJson);
                jsonList.add("wishes",wishProperty);

                OkHttpClient client = new OkHttpClient();

                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, jsonList.toString());
                Request request = new Request.Builder()
                        .url(URL + "?email=" + params[0])
                        .post(body)
                        .addHeader("Content-Type", "application/json")
                        .addHeader("cache-control", "no-cache")
                        .build();

                Response response = client.newCall(request).execute();
                if(response.isSuccessful()){
                    favoritos.getOculos().add(StaticData.OculosData.getOculosByCodigo(params[1]));
                    success = true;
                }
                response.close();


            }
            else if (operation.equals("DELETE")) {

                JsonObject jsonList = new JsonObject();

                JsonArray wishProperty = new JsonArray();

                JsonObject wishJson = new JsonObject();
                wishJson.addProperty("code", params[1]);

                wishProperty.add(wishJson);
                jsonList.add("wishes",wishProperty);

                OkHttpClient client = new OkHttpClient();

                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, jsonList.toString());
                Request request = new Request.Builder()
                        .url(URL + "?email=" + params[0])
                        .delete(body)
                        .addHeader("Content-Type", "application/json")
                        .addHeader("cache-control", "no-cache")
                        .build();

                Response response = client.newCall(request).execute();

                if(response.isSuccessful()){
                    favoritos.getOculos().remove(StaticData.OculosData.getOculosByCodigo(params[1]));
                    success = true;
                }
                response.close();

            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return favoritos;
    }

    @Override
    protected void onPostExecute(Favorito favoritos) {
        //dialog.hide();
        if (operation.equals("GET")) {
            if(true == success){
                StaticData.UserData.getFavorito().setOculos(favoritos.getOculos());
            }
        }
        else if (operation.equals("CREATE")) {
            if(!success){
                Toast.makeText(context, "Não foi possível adicionar os óculos aos favoritos no servidor, tente novamente.", Toast.LENGTH_SHORT).show();
            }
        }
        else if (operation.equals("DELETE")) {
            if(!success){
                Toast.makeText(context, "Não foi possível remover os óculos dos favoritos no servidor, tente novamente.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
