package com.uso.exma2rg17i04002;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.uso.exma2rg17i04002.adapters.jugadorAdapter;
import com.uso.exma2rg17i04002.models.usuarioJugador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Mayor_puntaje extends AppCompatActivity {

    private List<usuarioJugador> players = usuarioJugador.lstJugadores;
    private List<usuarioJugador> listaPlayers = new ArrayList<>();
    private RecyclerView recyclerPuntaje;
    private jugadorAdapter adapter;
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mayor_puntaje);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.recyclerPuntaje = findViewById(R.id.rcwPuntajes);
        try {
            ordenarPuntajes();
            this.manager = new LinearLayoutManager(this);
            this.adapter = new jugadorAdapter(listaPlayers);

            this.recyclerPuntaje.setHasFixedSize(true);
            this.recyclerPuntaje.setLayoutManager(this.manager);
            this.recyclerPuntaje.setAdapter(this.adapter);

            this.recyclerPuntaje.setNestedScrollingEnabled(false);
            recyclerPuntaje.setOnTouchListener(new View.OnTouchListener()
            {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true; }
            });
        }catch (Exception ex){
            Toast.makeText(Mayor_puntaje.this,"Error: " + ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    private void ordenarPuntajes() {
        Collections.sort(players, new Comparator<usuarioJugador>() {
            @Override
            public int compare(usuarioJugador p1, usuarioJugador p2) {
                return new Integer(p2.getScore()).compareTo(new Integer(p1.getScore()));
            }
        });

        for (usuarioJugador player: players) {
            listaPlayers.add(player);
        }
    }
}