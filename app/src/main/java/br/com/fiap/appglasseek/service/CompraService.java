package br.com.fiap.appglasseek.service;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import br.com.fiap.appglasseek.dao.StaticData;
import br.com.fiap.appglasseek.model.Carrinho;
import br.com.fiap.appglasseek.model.Compra;
import br.com.fiap.appglasseek.model.Endereco;
import br.com.fiap.appglasseek.model.Item;
import cc.cloudist.acplibrary.ACProgressFlower;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CompraService extends AsyncTask<String, Void, Compra> implements Service{
    static String operation;
    private String URL;
    private Context context;
    private ACProgressFlower dialog;
    private Boolean success;

    public CompraService(Context context, String operation) {
        this.context = context;
        this.operation = operation;
        this.URL = Service.URL + "/06Purchase";
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
    protected Compra doInBackground(String... params) {
        JsonObject jsonObject;
        Compra compra = new Compra();

        try {
//            if (operation.equals("GET")) {
//                OkHttpClient client = new OkHttpClient();
//
//                Request request = new Request.Builder()
//                        .url(URL + "?email=" + params[0])
//                        .get()
//                        .addHeader("cache-control", "no-cache")
//                        .build();
//
//                Response response = client.newCall(request).execute();
//                jsonObject = new Gson().fromJson(response.body().string(),JsonObject.class).getAsJsonArray("addresses").get(0).getAsJsonObject();
//
//
//                if(response.isSuccessful()){
//                    endereco.setNumero(jsonObject.get("number").getAsString());
//                    endereco.setEndereco(jsonObject.get("address").getAsString());
//                    endereco.setCep(jsonObject.get("postalCode").getAsString());
//                    endereco.setEstado(jsonObject.get("state").getAsString());
//                    if (!(jsonObject.get("complement") instanceof JsonNull)){
//                        endereco.setComplemento(jsonObject.get("complement").getAsString());
//                    }
//                    endereco.setCidade(jsonObject.get("city").getAsString());
//                    endereco.setMunicipio(jsonObject.get("municipality").getAsString());
//
//                    success = true;
//                }
//                response.close();
//
//
//            }
//            else
                if (operation.equals("CREATE")) {
                    JsonObject jsonParent = new JsonObject();
                    JsonObject jsonPurchase = new JsonObject();

                    JsonArray itens = new JsonArray();

                    for (Item item:StaticData.UserData.getCarrinho().getItens()
                         ) {
                        JsonObject itemJson = new JsonObject();

                        itemJson.addProperty("glassesCode", item.getOculos().getCodigo());
                        itemJson.addProperty("itemQuantity", item.getQuantidade());

                        itens.add(itemJson);

                    }

                    jsonPurchase.addProperty("shippingAddressCode","001");
                    jsonPurchase.addProperty("cardCode","001");
                    jsonPurchase.add("items",itens);

                    jsonParent.add("purchase",jsonPurchase);

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

                if(response.isSuccessful()){

                    success = true;
                }
                response.close();


            }
//                else if (operation.equals("UPDATE")) {
//                JsonObject jsonList = new JsonObject();
//
//                JsonArray enderecos = new JsonArray();
//
//                JsonObject enderecoJson = new JsonObject();
//                enderecoJson.addProperty("number", params[1]);
//                enderecoJson.addProperty("address", params[2]);
//                enderecoJson.addProperty("postalCode", params[3]);
//                enderecoJson.addProperty("state", params[4]);
//                enderecoJson.addProperty("complement", params[5]);
//                enderecoJson.addProperty("city", params[6]);
//
//                enderecoJson.addProperty("municipality", null == params[7] ? "null" : params[7]);
//
//                enderecos.add(enderecoJson);
//                jsonList.add("addresses",enderecos);
//
//                OkHttpClient client = new OkHttpClient();
//
//                MediaType mediaType = MediaType.parse("application/json");
//                RequestBody body = RequestBody.create(mediaType,jsonList.toString());
//                Request request = new Request.Builder()
//                        .url(URL + "?email=" + params[0])
//                        .put(body)
//                        .addHeader("Content-Type", "application/json")
//                        .addHeader("Accept", "*/*")
//                        .addHeader("Connection", "keep-alive")
//                        .addHeader("cache-control", "no-cache")
//                        .build();
//
//                Response response = client.newCall(request).execute();
//
//                if(response.isSuccessful()){
//                    StaticData.UserData.getUsuario().getEnderecos().remove(0);
//                    success = true;
//                }else{
//                    StaticData.UserData.getUsuario().getEnderecos().remove(1);
//                }
//                response.close();
//
//
//
//            } else if (operation.equals("DELETE")) {
//
//            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return compra;
    }

    @Override
    protected void onPostExecute(Compra compra) {
        //dialog.hide();
        if (operation.equals("GET")) {
//            if(endereco.getCep() == null){
//                //Toast.makeText(context, "Endereço inexistente!", Toast.LENGTH_SHORT).show();
//            } else {
//                StaticData.UserData.getUsuario().getEnderecos().add(endereco);
//            }
        } else if (operation.equals("CREATE")) {
            if (success) {
                CarrinhoService carrinhoService = new CarrinhoService(context,"DELETE");
                carrinhoService.execute(StaticData.UserData.getUsuario().getEmail(),"DELETE_ALL");

                Toast.makeText(context, "Compra solicitada com sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Deu merda cadastrando a compra no servidor!", Toast.LENGTH_SHORT).show();
            }

        } else if (operation.equals("UPDATE")) {
//            if (success) {
//                Toast.makeText(context, "Dados de endereço alterados com sucesso!", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(context, "Não foi possível alterar os dados de endereço, tente novamente!", Toast.LENGTH_SHORT).show();
//            }
        } else if (operation.equals("DELETE")) {
//            if (endereco.getCep() == null) {
//                LoginUtility.logOut(context);
//                Toast.makeText(context, "Usuário deletado, você foi desconectado!", Toast.LENGTH_SHORT).show();
//            }
        }
    }
}
