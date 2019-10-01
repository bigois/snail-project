package br.com.fiap.appglasseek.service;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.dao.StaticData;
import br.com.fiap.appglasseek.model.Oculos;
import cc.cloudist.acplibrary.ACProgressFlower;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static br.com.fiap.appglasseek.dao.StaticData.OculosData.imageList;

public class OculosService extends AsyncTask<String, Void, List<Oculos>> implements Service{
    static String operation;
    private String URL;
    private Context context;
    private ACProgressFlower dialog;
    private Boolean success;

    public OculosService(Context context, String operation) {
        this.context = context;
        this.operation = operation;
        this.URL = Service.URL + "/02Glasses";
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
    protected List<Oculos> doInBackground(String... params) {
        JsonArray jsonArray;
        List<Oculos> oculosList = new ArrayList<>();

        try {
            if (operation.equals("GET")) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(URL)
                        .addHeader("Accept", "*/*")
                        .addHeader("Cache-Control", "no-cache")
                        .get()
                        .build();

                Response response = client.newCall(request).execute();
                jsonArray = new Gson().fromJson(response.body().string(),JsonObject.class).getAsJsonArray("glasses");

                if(response.isSuccessful()){

                    imageList = new ArrayList<Integer>();

                    imageList.add(R.drawable.juliet1);
                    imageList.add(R.drawable.juliet2);
                    imageList.add(R.drawable.juliet3);

                    for (int i = 0; i < jsonArray.size(); i++) {
                        Oculos oculos = new Oculos();
                        JsonObject row = jsonArray.get(i).getAsJsonObject();

                        oculos.setGenero(row.get("gender").getAsString());
                        oculos.setCor(row.get("color").getAsString());
                        oculos.setMarca(row.get("brand").getAsString());
                        oculos.setCodigo(row.get("code").getAsString());
                        oculos.setTipo(row.get("type").getAsString());
                        oculos.setComprimento(row.get("length").getAsDouble());
                        oculos.setLargura(row.get("width").getAsDouble());
                        oculos.setMaterial(row.get("composition").getAsString());
                        oculos.setAltura(row.get("height").getAsDouble());
                        oculos.setPreco(row.get("price").getAsDouble());
                        oculos.setModelo(row.get("model").getAsString());
                        oculos.setImagem(R.drawable.juliet);
                        oculos.setImagens(imageList);

                        oculosList.add(oculos);

                    }

                }


            } //else if (operation.equals("CREATE")) {
//
//            } else if (operation.equals("UPDATE")) {
//
//            } else if (operation.equals("DELETE")) {
//
//            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return oculosList;
    }

    @Override
    protected void onPostExecute(List<Oculos> oculosList) {
        if (operation.equals("GET")) {
            if (oculosList.isEmpty()) {
                Toast.makeText(context, "Lista de óculos indisponíveis!", Toast.LENGTH_SHORT).show();
            } else {
                StaticData.OculosData.setOculosList(oculosList);
            }
//        } else if (operation.equals("CREATE")) {
//
//        } else if (operation.equals("UPDATE")) {
//
//        } else if (operation.equals("DELETE")) {
//
        }
    }
}
