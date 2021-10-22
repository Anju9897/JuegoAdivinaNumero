package com.uso.exma2rg17i04002.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uso.exma2rg17i04002.R;
import com.uso.exma2rg17i04002.models.usuarioJugador;
import com.uso.exma2rg17i04002.viewHolders.jugadorViewHolder;

import java.util.ArrayList;
import java.util.List;

import static androidx.appcompat.content.res.AppCompatResources.getDrawable;

public class jugadorAdapter extends RecyclerView.Adapter<jugadorViewHolder> {

    private List<usuarioJugador> lstJugadores = new ArrayList<>();
    public jugadorAdapter(List<usuarioJugador> data){
        this.lstJugadores = data;
    }

    @NonNull
    @Override
    public jugadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_plantilla_puntaje,parent,false);
        jugadorViewHolder jvh = new jugadorViewHolder(v);
        return jvh;
    }

    @Override
    public void onBindViewHolder(@NonNull jugadorViewHolder holder, int position) {
        List<usuarioJugador> lst = this.lstJugadores;
        if (position < 3){
            holder.getNickPlayer().setText(lst.get(position).getNick());
            holder.getScorePlayer().setText(Integer.toString(lst.get(position).getScore()));
            holder.getNivelPlayer().setText(lst.get(position).getNivel());
        }
        if(position == 0) {
            holder.getPositionImg().setBackground(getDrawable(holder.itemView.getContext(),R.drawable.puzzle_oro));
        }
        if(position == 1) {
            holder.getPositionImg().setBackground(getDrawable(holder.itemView.getContext(),R.drawable.puzzle_plata));
        }
        if(position == 2) {
            holder.getPositionImg().setBackground(getDrawable(holder.itemView.getContext(),R.drawable.puzzle_bronce));
        }

        if(position >=3){
            holder.itemView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return this.lstJugadores.size();
    }
}
