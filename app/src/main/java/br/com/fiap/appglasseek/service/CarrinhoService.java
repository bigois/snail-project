package br.com.fiap.appglasseek.service;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import br.com.fiap.appglasseek.dao.StaticData;
import br.com.fiap.appglasseek.model.Carrinho;
import br.com.fiap.appglasseek.model.Item;
import br.com.fiap.appglasseek.model.Oculos;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CarrinhoService extends AsyncTask<String, Void, Carrinho> implements Service {
    static String operation;
    private String URL;
    private Context context;
    private Boolean success;

    public CarrinhoService(Context context, String operation) {
        this.context = context;
        this.operation = operation;
        this.URL = Service.URL + "/05Cart";
        this.success = Service.success;
    }

    @Override
    protected void onPreExecute() {
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

                if (response.isSuccessful()) {
                    for (int i = 0; i < jsonArray.size(); i++) {
                        Oculos oculos;
                        Double total;
                        String codigoOculos;
                        Integer quantity;

                        JsonObject row = jsonArray.get(i).getAsJsonObject();

                        codigoOculos = row.get("code").getAsString();
                        quantity = row.get("item").getAsInt();

                        oculos = StaticData.OculosData.getOculosByCodigo(codigoOculos);
                        total = oculos.getPreco() * quantity;
                        Item item = new Item(oculos, quantity, total);

                        if (null != oculos) {
                            carrinho.getItens().add(item);
                        }

                    }
                }

                response.close();
            } else if (operation.equals("CREATE")) {
                JsonObject jsonList = new JsonObject();
                JsonArray cartProperty = new JsonArray();
                JsonObject wishJson = new JsonObject();

                wishJson.addProperty("code", params[1]);
                wishJson.addProperty("quantity", Integer.parseInt(params[2]));

                cartProperty.add(wishJson);
                jsonList.add("cart", cartProperty);

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
                response.close();
            } else if (operation.equals("UPDATE")) {
                JsonObject jsonList = new JsonObject();
                JsonArray cartProperty = new JsonArray();
                JsonObject wishJson = new JsonObject();

                wishJson.addProperty("code", params[1]);
                wishJson.addProperty("quantity", Integer.parseInt(params[2]));

                cartProperty.add(wishJson);
                jsonList.add("cart", cartProperty);

                OkHttpClient client = new OkHttpClient();

                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, jsonList.toString());
                Request request = new Request.Builder()
                        .url(URL + "?email=" + params[0])
                        .put(body)
                        .addHeader("Content-Type", "application/json")
                        .addHeader("cache-control", "no-cache")
                        .build();

                Response response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    List<Item> itemList;
                    itemList = carrinho.getItens();

                    for (Item item : itemList) {
                        if (item.getOculos().getCodigo().equals(params[1])) {
                            item.setQuantidade(Integer.parseInt(params[2]));
                        }
                    }
                    carrinho.setItens(itemList);
                    success = true;
                }
                response.close();

            } else if (operation.equals("DELETE")) {
                JsonObject jsonList = new JsonObject();

                JsonArray cartProperty = new JsonArray();

                JsonObject wishJson = new JsonObject();

                if (params.length == 2) {
                    if (params[1].equals("DELETE_ALL")) {
                        for (Item item : StaticData.UserData.getCarrinho().getItens()
                        ) {
                            wishJson = new JsonObject();
                            wishJson.addProperty("code", item.getOculos().getCodigo());
                            cartProperty.add(wishJson);
                        }
                    } else {
                        wishJson.addProperty("code", params[1]);
                        cartProperty.add(wishJson);
                    }
                }

                jsonList.add("cart", cartProperty);

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
//                if (response.isSuccessful()) {
//                    success = true;
//                    if (params.length == 2) {
//                        if (params[1].equals("DELETE_ALL")) {
                            StaticData.UserData.setCarrinho(new Carrinho());
//                        }
//                    }
//                }

                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return carrinho;
    }

    @Override
    protected void onPostExecute(Carrinho carrinho) {
        if (operation.equals("GET")) {
            if (carrinho.getItens().size() > 0) {
                StaticData.UserData.setCarrinho(carrinho);
            }
        } else if (operation.equals("DELETE")) {
            // TODO
        }
    }
}

