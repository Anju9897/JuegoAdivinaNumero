package com.uso.exma2rg17i04002;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.uso.exma2rg17i04002.models.usuarioJugador;

import java.util.ArrayList;

public class Juego_principal extends Activity {

    TextView usuario;
    TextView nivel;
    TextView intentos;
    Button intentar;
    EditText ValorIntento;
    private SharedPreferences configuracion;
    private int nIntentos = 1;
    private int score = 0;
    private usuarioJugador player;

    //para el movimiento de pantalla
    private WindowManager administradorPantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_principal);

        this.usuario = findViewById(R.id.txtNickUsuario);
        this.nivel = findViewById(R.id.txtNivelUsuario);
        this.intentos = findViewById(R.id.txtIntentos);
        this.intentar = findViewById(R.id.btnAdivinar);
        this.ValorIntento = findViewById(R.id.txtNumero);
        this.configuracion = getSharedPreferences(Configuraciones.ARCHIVO,MODE_PRIVATE);
        this.administradorPantalla = getWindowManager();

        this.player = (usuarioJugador) getLastNonConfigurationInstance();

        if(this.player == null){
            this.player = new usuarioJugador();
        }

        cargarInformacion();
        final int numAleatorio = generarNumero();
        this.intentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ValorIntento.getText().toString().isEmpty()) {
                    if (numAleatorio == Integer.parseInt(ValorIntento.getText().toString())) {
                        //Ha acertado
                        calcularPuntaje();
                        player = new usuarioJugador();
                        player.setNick(usuario.getText().toString());
                        player.setNivel(nivel.getText().toString());
                        player.setIntentos(nIntentos);
                        player.setScore(score);
                        usuarioJugador.lstJugadores.add(player);
                        Toast.makeText(Juego_principal.this, "Bravo! Has acertado al intento numero: " + Integer.toString(nIntentos), Toast.LENGTH_LONG).show();
                        Toast.makeText(Juego_principal.this, "Tu puntuacion final es: " + Integer.toString(score), Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        //aumenta el numero de intentos
                        nIntentos++;
                        Toast.makeText(Juego_principal.this, "Ups! no has acertado, vuelve a intentarlo", Toast.LENGTH_SHORT).show();
                        ValorIntento.setText("");
                        intentos.setText(Integer.toString(nIntentos));
                    }
                } else {
                    ValorIntento.setError("Debe de ingresar un numero");
                }
            }
        });
    }

    private void calcularPuntaje() {
        final int puntajeBase = 100;
        if(nIntentos == 1){
            score = puntajeBase;
        }else if(nIntentos > 1){
            score = Math.round(puntajeBase/nIntentos);
        }
    }

    private int generarNumero() {
        int numero = 0;
        if(nivel.getText().toString().equals("Facil")){
            //hasta 50 numeros
            numero = (int) (Math.random()*50) + 1;
        } else if(nivel.getText().toString().equals("Medio")) {
            numero = (int) (Math.random()*100) + 1;
        } else if(nivel.getText().toString().equals("Dificil")){
            numero = (int) (Math.random()*150) + 1;
        }
        return numero;
    }

    private void cargarInformacion() {
        this.usuario.setText(configuracion.getString(Configuraciones.JUGADOR,""));
        this.nivel.setText(configuracion.getString(Configuraciones.NIVEL,""));
        this.intentos.setText(Integer.toString(nIntentos));
    }

    public Object onRetainNonConfigurationInstance() {
        super.onRetainNonConfigurationInstance();
        return  this.player;
    }

    //GUARDAMOS EL ESTADO DE VARIABLES SENSILLAS
    public  void onSaveInstanceState( Bundle estado){
        super.onSaveInstanceState(estado);
        //aqui guardamos las variables nativas
        estado.putInt("Intentos", nIntentos);
    }
    // RECUPERAMOS EL ESTADO ANTERIOR
    public  void  onRestoreInstanceState(Bundle estadoAnterior){
        super.onRestoreInstanceState(estadoAnterior);
        //recuperarmos el estado antes del cambio
        this.nIntentos = estadoAnterior.getInt("Intentos");
        this.intentos.setText(Integer.toString(this.nIntentos));
    }



}