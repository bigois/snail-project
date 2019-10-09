package br.com.fiap.appglasseek.service;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.com.fiap.appglasseek.dao.StaticData;
import br.com.fiap.appglasseek.model.Compra;
import br.com.fiap.appglasseek.model.Item;
import br.com.fiap.appglasseek.model.Oculos;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CompraService extends AsyncTask<String, Void, Compra> implements Service {
    static String operation;
    private String URL;
    private Context context;
    private Boolean success;

    public CompraService(Context context, String operation) {
        this.context = context;
        this.operation = operation;
        this.URL = Service.URL + "/06Purchase";
        this.success = Service.success;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected Compra doInBackground(String... params) {
        Compra compra = new Compra();
        JsonArray jsonArray;

        try {
            if (operation.equals("GET")){

                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(URL + "?email=" + params[0])
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .build();

                Response response = client.newCall(request).execute();

//                jsonArray = new Gson().fromJson(response.body().string(), JsonObject.class).getAsJsonArray("purchases");
////
////                if (response.isSuccessful()) {
////
////                    for (int i = 0; i < jsonArray.size(); i++) {
////                        Oculos oculos = new Oculos();
////                        String codigoOculos;
////                        String item;
////
////                        JsonObject row = jsonArray.get(i).getAsJsonObject();
////
//////                        codigoOculos = row.get("code").getAsString();
//////                        oculos = StaticData.OculosData.getOculosByCodigo(codigoOculos);
//////
//////                        if (null != oculos) {
//////                            compra.getItem().add(item);
//////                            favoritos.getOculos().add(oculos);
//////                            success = true;
//////                        }
////                    }
////                }

                response.close();

            } else if (operation.equals("CREATE")) {
                JsonObject jsonParent = new JsonObject();
                JsonObject jsonPurchase = new JsonObject();

                JsonArray itens = new JsonArray();

                for (Item item : StaticData.UserData.getCarrinho().getItens()) {
                    JsonObject itemJson = new JsonObject();

                    itemJson.addProperty("glassesCode", item.getOculos().getCodigo());
                    itemJson.addProperty("itemQuantity", item.getQuantidade());

                    itens.add(itemJson);
                }

                jsonPurchase.addProperty("shippingAddressCode", "001");
                jsonPurchase.addProperty("cardCode", "001");
                jsonPurchase.add("items", itens);

                jsonParent.add("purchase", jsonPurchase);

                OkHttpClient client = new OkHttpClient();

                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, jsonParent.toString());
                Request request = new Request.Builder()
                        .url(URL + "?email=" + params[0])
                        .post(body)
                        .addHeader("Content-Type", "application/json")
                        .addHeader("cache-control", "no-cache")
                        .build();

                Response response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    success = true;
                }

                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return compra;
    }

    @Override
    protected void onPostExecute(Compra compra) {
        if (operation.equals("GET")) {
            // TODO
        } else if (operation.equals("CREATE")) {
            if (success) {
                CarrinhoService carrinhoService = new CarrinhoService(context, "DELETE");
                carrinhoService.execute(StaticData.UserData.getUsuario().getEmail(), "DELETE_ALL");

                Toast.makeText(context, "Compra solicitada com sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Deu merda cadastrando a compra no servidor!", Toast.LENGTH_SHORT).show();
            }
        } else if (operation.equals("UPDATE")) {
            // TODO
        } else if (operation.equals("DELETE")) {
            // TODO
        }
    }
}
