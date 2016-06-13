package com.pdm.talktothehand;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class Principal extends AppCompatActivity {



    public final static String ACTIVIDAD_CHAT = "com.pdm.talktothehand.CHATEAR";
    SalaAdapter adapter;
    Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ControladorListaPrincipal.setC(this.getBaseContext());
        ControladorListaPrincipal.updateListaPrincipal();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        // Obtener instancia de la lista
        ControladorListaPrincipal.setListView((ListView) findViewById(R.id.listView));
        c = this.getBaseContext();


       /* String URL_BASE = "http://dispositivosmoviles.herokuapp.com";
        String URL_JSON = "/TalkToTheHand/salasJson/";
        Map<String, String> params = new HashMap<String, String>();

        //params.put("username", datosUsuario.getNombre_usuario());


        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, URL_BASE + URL_JSON, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("Response: ", response.toString());
                //actualizar lista
                adapter = new SalaAdapter(c, response);
                ControladorListaPrincipal.getListView().setAdapter(adapter);
                //Asocio el menu contextual a la vista de la lista
                registerForContextMenu(ControladorListaPrincipal.getListView());

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError response) {
                Log.d("Response: ", response.toString());
            }
        });
        Toast.makeText(this, "Lista Actualizada", Toast.LENGTH_SHORT).show();
        gestorPeticiones.setCola(this);
        gestorPeticiones.getCola().add(jsObjRequest);

        //Asocio el menu contextual a la vista de la lista
        */
        registerForContextMenu(ControladorListaPrincipal.getListView());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.nueva) {
            Intent intent = new Intent(this, NuevaPregunta.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.logOut) {
            SharedPreferences preferences = getSharedPreferences("temp", getApplicationContext().MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("username", "");
            editor.putString("dni", "");
            editor.commit();
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_sala, menu);

    }
    //Controlo del elemento dle menu selecionado
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Sala i;
        Intent intent;
        switch (item.getItemId()) {

            case R.id.entrar:
                i=this.adapter.getItemFromAdapter(info.position);
                Log.d("Nombre: ", i.getNombre());
                intent = new Intent(this, Chaterar.class);
                intent.putExtra(ACTIVIDAD_CHAT, i.get_id());
                startActivity(intent);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
}