package com.pdm.talktothehand;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Chaterar extends AppCompatActivity implements View.OnClickListener{

    Context c;
    TextView mensaje;
    Button enviar_mensaje;
    String sala;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chaterar);
        mensaje=(EditText) findViewById(R.id.mensaje);
        enviar_mensaje=(Button) findViewById(R.id.botonEnviar);



        enviar_mensaje.setOnClickListener(this);
        c=this.getBaseContext();
        Intent intent = getIntent();
        sala = intent.getStringExtra(Principal.ACTIVIDAD_CHAT);


        WebView webView = (WebView) this.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.loadUrl("http://dispositivosmoviles.herokuapp.com/TalkToTheHand/sala/" + sala);
        webView.setWebViewClient(new WebViewClient());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.botonEnviar: {

                String URL_BASE = "http://dispositivosmoviles.herokuapp.com";
                String URL_JSON = "/TalkToTheHand/enviar/";
                Map<String, String> params = new HashMap<String, String>();

                params.put("username", datosUsuario.getNombre_usuario());
                params.put("sala", sala);
                params.put("mensaje", mensaje.getText().toString());



                CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, URL_BASE + URL_JSON, params, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response: ", response.toString());
                        mensaje.setText("");

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError response) {
                        Log.d("Response: ", response.toString());
                    }
                });
                //Toast.makeText(this, "Lista Actualizada", Toast.LENGTH_SHORT).show();
                gestorPeticiones.setCola(this);
                gestorPeticiones.getCola().add(jsObjRequest);

                Toast.makeText(this, "Enviando", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}
