package com.pdm.talktothehand;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hugo on 13/06/16.
 */
public class ControladorListaPrincipal {




    public static ListView listView;
    public static Context c;

    public static SalaAdapter getAdapterTotal() {
        return adapterTotal;
    }

    public static void setAdapterTotal(SalaAdapter adapterTotal) {
        ControladorListaPrincipal.adapterTotal = adapterTotal;
    }

    public static SalaAdapter adapterTotal;


    public static ListView getListView() {
        return listView;
    }

    public static void setListView(ListView listView) {
        ControladorListaPrincipal.listView = listView;
    }
    public static Context getC() {
        return c;
    }

    public static void setC(Context c) {
        ControladorListaPrincipal.c = c;
    }

    public static void updateListaPrincipal(){
        String URL_BASE = "http://dispositivosmoviles.herokuapp.com";
        String URL_JSON = "/TalkToTheHand/salasJson/";
        Map<String, String> params = new HashMap<String, String>();

        //params.put("username", datosUsuario.getNombre_usuario());


        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, URL_BASE + URL_JSON, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("Response: ", response.toString());
                //actualizar lista
                adapterTotal = new SalaAdapter(ControladorListaPrincipal.c, response);
                ControladorListaPrincipal.getListView().setAdapter(adapterTotal);
                //Asocio el menu contextual a la vista de la lista
                //registerForContextMenu(ControladorListaPrincipal.getListView());

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError response) {
                Log.d("Response: ", response.toString());
            }
        });
        gestorPeticiones.setCola(ControladorListaPrincipal.c);
        gestorPeticiones.getCola().add(jsObjRequest);


    }
}
