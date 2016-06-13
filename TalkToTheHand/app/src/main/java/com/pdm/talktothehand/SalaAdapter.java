package com.pdm.talktothehand;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SalaAdapter extends ArrayAdapter {

    //private RequestQueue requestQueue;
    // Atributos

    private static final String TAG = "PostAdapterItem";
    List<Sala> salas;


    public SalaAdapter(Context context, JSONObject jsonObject) {
        super(context, 0);

        salas = parseJson(jsonObject);
        //Funcionaaaaaaaaaaaaaaaaaaaaaaaaaaaa///////////////////////
        // requestQueue = Volley.newRequestQueue(context);

    }




    public List<Sala> parseJson(JSONObject jsonObject){
        // Variables locales
        List<Sala> salas = new ArrayList();
        JSONArray jsonArray= null;

        try {
            // Obtener el array del objeto
            jsonArray = jsonObject.getJSONArray("salas");

            for(int i=0; i<jsonArray.length(); i++){

                try {
                    JSONObject objeto= jsonArray.getJSONObject(i);

                    Sala sala = new Sala(
                            objeto.getString("_id"),
                            objeto.getString("nombre"),
                            objeto.getString("descripcion"),
                            objeto.getString("fecha"));


                    salas.add(sala);

                } catch (JSONException e) {
                    Log.e(TAG, "Error de parsing: " + e.getMessage());
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return salas;
    }

    @Override
    public int getCount() {
        return salas != null ? salas.size() : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        // View auxiliar
        View listItemView;

        //Comprobando si el View no existe
        if (null == convertView) {
            //Si no existe, entonces inflarlo
            listItemView = layoutInflater.inflate(
                    R.layout.sala,
                    parent,
                    false);
        } else listItemView = convertView;


        // Obtener el item actual
        Sala sala = salas.get(position);
        //Item item = items.getItems().get(position);

        // Obtener Views
        TextView textoNombre = (TextView) listItemView.findViewById(R.id.nombre);
        TextView textoFecha = (TextView) listItemView.findViewById(R.id.fecha);
        TextView textoDescripcion = (TextView) listItemView.findViewById(R.id.descripcion);

        // Actualizar los Views
        textoNombre.setText(sala.getNombre());
        textoFecha.setText(sala.getFecha());
        textoDescripcion.setText(sala.getDescripcion());



        return listItemView;
    }
    //metodos para acceder a la lista de objetos items
    public List<Sala> getListaItems(){
        return salas;
    }
    public Sala getItemFromAdapter(int posicion){
        return salas.get(posicion);
    }

}
