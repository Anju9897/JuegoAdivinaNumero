package com.uso.exma2rg17i04002;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.uso.exma2rg17i04002.models.usuarioJugador;

public class MainActivity extends AppCompatActivity {

    Button btnIniciar;
    TextView usuario;
    TextView nivel;
    Button iniciarJuego;
    private SharedPreferences configuracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.btnIniciar = findViewById(R.id.btnPlay);
        this.usuario = findViewById(R.id.usuario);
        this.nivel = findViewById(R.id.nivel);
        this.iniciarJuego = findViewById(R.id.btnPlay);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.configuracion = getSharedPreferences(Configuraciones.ARCHIVO,MODE_PRIVATE);
        convectarServicio();

        this.iniciarJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!usuario.getText().toString().equals("") && !nivel.getText().toString().equals("")) {
                    Intent game = new Intent(MainActivity.this, Juego_principal.class);
                    startActivity(game);
                }else{
                    Toast.makeText(MainActivity.this,"Se deben de configurar el nick del jugador y su dificultad para empezar a jugar, ir a configuraciones",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    protected void onResume() {
        super.onResume();
        convectarServicio();
    }

    private void convectarServicio() {
        if(this.configuracion != null){
            String nickUser = this.configuracion.getString(Configuraciones.JUGADOR,"");
            String nivelUser = this.configuracion.getString(Configuraciones.NIVEL,"");
            this.usuario.setText(nickUser);
            this.nivel.setText(nivelUser);
        }
    }


    public  boolean onCreateOptionsMenu( Menu menu){
        super.onCreateOptionsMenu(menu);
        menuOpciones(menu);
        return  true;
    }
    public  boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);
        seleccionItem(item);
        return true;
    }

    private void seleccionItem(MenuItem item) {
        //validacion de la opcion seleccoionada
        switch (item.getItemId()){
            case 0:
                //pantalla de juego
                if(!usuario.getText().toString().equals("") && !nivel.getText().toString().equals("")) {
                    Intent game = new Intent(MainActivity.this, Juego_principal.class);
                    startActivity(game);
                }else{
                    Toast.makeText(MainActivity.this,"Se deben de configurar el nick del jugador y su dificultad para empezar a jugar, ir a configuraciones",Toast.LENGTH_LONG).show();
                }
                break;
            case 1:
                //mostraremos pantalla de puntajes altos
                if(usuarioJugador.lstJugadores.size() > 0) {
                    Intent points = new Intent(this, Mayor_puntaje.class);
                    startActivity(points);
                }else{
                    Toast.makeText(MainActivity.this,"No es posible abrir la lista de puntuaciones, aun no hay registros",Toast.LENGTH_LONG).show();
                }
                break;
            case 2:
                //mostraremos pantalla de cofiguracion
                Intent conf = new Intent(this,Configuraciones.class);
                startActivity(conf);
                break;

        }

    }
    private void menuOpciones(Menu menu) {
        MenuItem item = menu.add(0,0,0,"Juego");
        item.setAlphabeticShortcut('J');
        MenuItem item2 = menu.add(1,1,1,"Puntajes Altos");
        item2.setAlphabeticShortcut('P');
        MenuItem item3 = menu.add(2,2,2,"Configuraciones");
        item3.setAlphabeticShortcut('C');
    }
}