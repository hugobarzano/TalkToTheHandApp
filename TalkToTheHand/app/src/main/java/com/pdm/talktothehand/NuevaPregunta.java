package com.pdm.talktothehand;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NuevaPregunta extends AppCompatActivity implements View.OnClickListener {

    private EditText titulo;
    private EditText descripcion;
    private Button crear;
    Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_pregunta);
        titulo = (EditText) findViewById(R.id.titulo);
        descripcion = (EditText) findViewById(R.id.descripcion);

        crear = (Button) findViewById(R.id.crear);

        crear.setOnClickListener(this);

        c=this.getBaseContext();

        ControladorListaPrincipal.setC(this.getBaseContext());


    }

    @Override
    public void onClick(View v) {

        if (v == crear) {
             String URL_BASE = "http://dispositivosmoviles.herokuapp.com";
            String URL_JSON = "/TalkToTheHand/nuevaSalaAndroid/";
            Map<String, String> params = new HashMap<String, String>();

            params.put("username", datosUsuario.getNombre_usuario());
            params.put("nombre", titulo.getText().toString());
            params.put("descripcion", descripcion.getText().toString());


            CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, URL_BASE + URL_JSON, params, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    Log.d("Response: ", response.toString());

                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError response) {
                    Log.d("Response: ", response.toString());
                }
            });
            Toast.makeText(this, "Cuestion Creada", Toast.LENGTH_SHORT).show();
            gestorPeticiones.setCola(ControladorListaPrincipal.getC());
            gestorPeticiones.getCola().add(jsObjRequest);

            this.finish();

        }

    }

}

