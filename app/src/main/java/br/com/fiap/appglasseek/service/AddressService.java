package br.com.fiap.appglasseek.service;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import br.com.fiap.appglasseek.dao.StaticData;
import br.com.fiap.appglasseek.model.Endereco;
import cc.cloudist.acplibrary.ACProgressFlower;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddressService extends AsyncTask<String, Void, Endereco> implements Service{
    static String operation;
    private String URL;
    private Context context;
    private ACProgressFlower dialog;
    private Boolean success;

    public AddressService(Context context, String operation) {
        this.context = context;
        this.operation = operation;
        this.URL = Service.URL + "/01Address";
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
    protected Endereco doInBackground(String... params) {
        JsonObject jsonObject;
        Endereco endereco = new Endereco();

        try {
            if (operation.equals("GET")) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(URL + "?email=" + params[0])
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .build();

                Response response = client.newCall(request).execute();
                jsonObject = new Gson().fromJson(response.body().string(),JsonObject.class).getAsJsonArray("addresses").get(0).getAsJsonObject();


                if(response.isSuccessful()){
                    endereco.setNumero(jsonObject.get("number").getAsString());
                    endereco.setEndereco(jsonObject.get("address").getAsString());
                    endereco.setCep(jsonObject.get("postalCode").getAsString());
                    endereco.setEstado(jsonObject.get("state").getAsString());
                    if (!(jsonObject.get("complement") instanceof JsonNull)){
                        endereco.setComplemento(jsonObject.get("complement").getAsString());
                    }
                    endereco.setCidade(jsonObject.get("city").getAsString());
                    endereco.setMunicipio(jsonObject.get("municipality").getAsString());

                    success = true;
                }
                response.close();


            } else if (operation.equals("CREATE")) {
                JsonObject jsonList = new JsonObject();

                JsonArray enderecos = new JsonArray();

                JsonObject enderecoJson = new JsonObject();
                enderecoJson.addProperty("number", params[1]);
                enderecoJson.addProperty("address", params[2]);
                enderecoJson.addProperty("postalCode", params[3]);
                enderecoJson.addProperty("state", params[4]);
                enderecoJson.addProperty("complement", params[5]);
                enderecoJson.addProperty("city", params[6]);
                enderecoJson.addProperty("municipality", null == params[7] ? "null" : params[7]);

                enderecos.add(enderecoJson);
                jsonList.add("addresses",enderecos);

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
                    //String cep, String destinatario, String endereco, String numero, String cidade, String municipio, String estado, String complemento, String telefone
                    endereco = new Endereco();
                    endereco.setCep(params[3]);
                    endereco.setEndereco(params[2]);
                    endereco.setNumero(params[1]);
                    endereco.setCidade(params[6]);
                    endereco.setMunicipio(params[6]);
                    endereco.setEstado(params[4]);
                    endereco.setComplemento(params[5]);
                    endereco.setDestinatario(params[9]);
                    endereco.setTelefone(params[10]);

                    StaticData.UserData.getUsuario().getEnderecos().add(endereco);
                    success = true;
                }
                response.close();


            } else if (operation.equals("UPDATE")) {
                JsonObject jsonList = new JsonObject();

                JsonArray enderecos = new JsonArray();

                JsonObject enderecoJson = new JsonObject();
                enderecoJson.addProperty("number", params[1]);
                enderecoJson.addProperty("address", params[2]);
                enderecoJson.addProperty("postalCode", params[3]);
                enderecoJson.addProperty("state", params[4]);
                enderecoJson.addProperty("complement", params[5]);
                enderecoJson.addProperty("city", params[6]);

                enderecoJson.addProperty("municipality", null == params[7] ? "null" : params[7]);

                enderecos.add(enderecoJson);
                jsonList.add("addresses",enderecos);

                OkHttpClient client = new OkHttpClient();

                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType,jsonList.toString());
                Request request = new Request.Builder()
                        .url(URL + "?email=" + params[0])
                        .put(body)
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Accept", "*/*")
                        .addHeader("Connection", "keep-alive")
                        .addHeader("cache-control", "no-cache")
                        .build();

                Response response = client.newCall(request).execute();

                if(response.isSuccessful()){
                    StaticData.UserData.getUsuario().getEnderecos().remove(0);
                    success = true;
                }else{
                    StaticData.UserData.getUsuario().getEnderecos().remove(1);
                }
                response.close();



            } else if (operation.equals("DELETE")) {

            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return endereco;
    }

    @Override
    protected void onPostExecute(Endereco endereco) {
        //dialog.hide();
        if (operation.equals("GET")) {
            if(endereco.getCep() == null){
                //Toast.makeText(context, "Endereço inexistente!", Toast.LENGTH_SHORT).show();
            } else {
                StaticData.UserData.getUsuario().getEnderecos().add(endereco);
            }
        } else if (operation.equals("CREATE")) {
            if (success) {
                Toast.makeText(context, "Dados de endereço cadastrados com sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Não foi possível cadastrar os dados de endereço, tente novamente!", Toast.LENGTH_SHORT).show();
            }

        } else if (operation.equals("UPDATE")) {
            if (success) {
                Toast.makeText(context, "Dados de endereço alterados com sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Não foi possível alterar os dados de endereço, tente novamente!", Toast.LENGTH_SHORT).show();
            }
        } else if (operation.equals("DELETE")) {
//            if (endereco.getCep() == null) {
//                LoginUtility.logOut(context);
//                Toast.makeText(context, "Usuário deletado, você foi desconectado!", Toast.LENGTH_SHORT).show();
//            }
        }
    }
}
