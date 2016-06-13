package com.pdm.talktothehand;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hugo on 12/04/16.
 */
public class gestorGlobal {

    private static JSONObject listaSalas;



    public static JSONObject getListaItemsUsuario() {
        return listaSalas;
    }

    public static void setListaItemsUsuario(Context c) {

        String URL_BASE = "http://dispositivosmoviles.herokuapp.com";
        String URL_JSON = "/TalkToTheHand/salasJson/";
        Map<String, String> params = new HashMap<String, String>();

        //params.put("username", datosUsuario.getNombre_usuario());


        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, URL_BASE + URL_JSON, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("Response: ", response.toString());
               listaSalas=response;

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError response) {
                Log.d("Response: ", response.toString());
            }
        });

        gestorPeticiones.setCola(c);
        gestorPeticiones.getCola().add(jsObjRequest);


    }









}
