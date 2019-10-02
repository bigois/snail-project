package br.com.fiap.appglasseek.service;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.com.fiap.appglasseek.dao.StaticData;
import br.com.fiap.appglasseek.model.Favorito;
import br.com.fiap.appglasseek.model.Oculos;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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
        Favorito favorito = new Favorito();

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
                            favorito.getOculos().add(oculos);
                        }

                    }

                }
                response.close();

            }
//            else if (operation.equals("CREATE")) {
//
//            } else if (operation.equals("UPDATE")) {
//
//            } else if (operation.equals("DELETE")) {
//
//            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return favorito;
    }

    @Override
    protected void onPostExecute(Favorito favoritos) {
        //dialog.hide();
        if (operation.equals("GET")) {
            if(favoritos.getOculos().size()>0){
                StaticData.UserData.getFavorito().setOculos(favoritos.getOculos());
            }
        }
//        else if (operation.equals("CREATE")) {
//
//        } else if (operation.equals("UPDATE")) {
//
//        } else if (operation.equals("DELETE")) {
//
//        }
    }
}
