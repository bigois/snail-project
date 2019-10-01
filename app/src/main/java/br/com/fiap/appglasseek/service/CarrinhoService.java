package br.com.fiap.appglasseek.service;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import br.com.fiap.appglasseek.model.Carrinho;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class CarrinhoService extends AsyncTask<String, Void, Carrinho> implements Service{
    static String operation;
    private String URL;
    private Context context;
    private ACProgressFlower dialog;
    private Boolean success;

    public CarrinhoService(Context context, String operation) {
        this.context = context;
        //AddressService.operation = operation;
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
        JsonObject jsonObject;
        Carrinho carrinho = new Carrinho();

        try {
            if (operation.equals("GET")) {


//                jsonObject = new Gson().fromJson(response.body().string(), JsonObject.class);
//
//                if(response.isSuccessful()){
//                    endereco.setNumero(jsonObject.get("").getAsString());
//                    endereco.setEndereco(jsonObject.get("").getAsString());
//                    endereco.setCep(jsonObject.get("").getAsString());
//                    endereco.setEstado(jsonObject.get("").getAsString());
//                    endereco.setComplemento(jsonObject.get("").getAsString());
//                    endereco.setCidade(jsonObject.get("").getAsString());
//                    endereco.setMunicipio(jsonObject.get("").getAsString());
//
//                }

            } else if (operation.equals("CREATE")) {

            } else if (operation.equals("UPDATE")) {

            } else if (operation.equals("DELETE")) {

            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Carrinho carrinho) {
        dialog.hide();
        if (operation.equals("GET")) {
//            if (oculos.getCodigo() == null) {
//                Toast.makeText(context, "Lista de óculos indisponíveis!", Toast.LENGTH_SHORT).show();
//            } else {
            // TODO -> COLOCAR LISTA DE OCULOS COMO RETORNO //StaticData.OculosData.setOculosList(usuario);
//                LoginUtility.logIn(context.getApplicationContext(), usuario.getEmail());
//                Toast.makeText(context, "Conectado com sucesso!", Toast.LENGTH_SHORT).show();
//
//            }
        } else if (operation.equals("CREATE")) {
//

        } else if (operation.equals("UPDATE")) {
//
        } else if (operation.equals("DELETE")) {
//
        }
    }
}

