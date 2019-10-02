package br.com.fiap.appglasseek.service;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.com.fiap.appglasseek.dao.StaticData;
import br.com.fiap.appglasseek.model.Carrinho;
import br.com.fiap.appglasseek.model.Item;
import br.com.fiap.appglasseek.model.Oculos;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CarrinhoService extends AsyncTask<String, Void, Carrinho> implements Service{
    static String operation;
    private String URL;
    private Context context;
    private ACProgressFlower dialog;
    private Boolean success;

    public CarrinhoService(Context context, String operation) {
        this.context = context;
        this.operation = operation;
        this.URL = Service.URL + "/05Cart";
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
    protected Carrinho doInBackground(String... params) {
        JsonArray jsonArray;
        Carrinho carrinho = new Carrinho();

        try {
            if (operation.equals("GET")) {

                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(URL + "?email=" + params[0])
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .build();

                Response response = client.newCall(request).execute();
                jsonArray = new Gson().fromJson(response.body().string(), JsonObject.class).getAsJsonArray("cart");

                if(response.isSuccessful()){
                    for (int i = 0; i < jsonArray.size(); i++) {
                        Oculos oculos = new Oculos();
                        Integer quantidade = 1;
                        Double total;
                        String codigoOculos;

                        JsonObject row = jsonArray.get(i).getAsJsonObject();

                        codigoOculos = row.get("code").getAsString();
                        //item = row.get("item").getAsString();

                        oculos = StaticData.OculosData.getOculosByCodigo(codigoOculos);
                        total = oculos.getPreco() * quantidade;
                        Item item = new Item(oculos,quantidade,total);

                        if(null != oculos){
                            carrinho.getItens().add(item);
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

        return carrinho;
    }

    @Override
    protected void onPostExecute(Carrinho carrinho) {
        //dialog.hide();
        if (operation.equals("GET")) {
            if (carrinho.getItens().size() > 0){
                StaticData.UserData.setCarrinho(carrinho);
            }
        }
//        } else if (operation.equals("CREATE")) {
//
//
//        } else if (operation.equals("UPDATE")) {
//
//        } else if (operation.equals("DELETE")) {
//
//        }
    }
}

