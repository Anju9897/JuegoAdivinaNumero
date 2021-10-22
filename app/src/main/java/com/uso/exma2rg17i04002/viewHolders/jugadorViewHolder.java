package com.uso.exma2rg17i04002.viewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uso.exma2rg17i04002.R;

public class jugadorViewHolder extends RecyclerView.ViewHolder {

    private TextView nickPlayer;
    private TextView scorePlayer;
    private ImageView positionImg;
    private  TextView nivelPlayer;

    public jugadorViewHolder(@NonNull View itemView) {
        super(itemView);
        this.nickPlayer = itemView.findViewById(R.id.txtNick);
        this.scorePlayer = itemView.findViewById(R.id.txtPuntaje);
        this.positionImg = itemView.findViewById(R.id.imgLugar);
        this.nivelPlayer = itemView.findViewById(R.id.txtNivel);
    }

    public TextView getNickPlayer() {
        return nickPlayer;
    }

    public void setNickPlayer(TextView nickPlayer) {
        this.nickPlayer = nickPlayer;
    }

    public TextView getScorePlayer() {
        return scorePlayer;
    }

    public void setScorePlayer(TextView scorePlayer) {
        this.scorePlayer = scorePlayer;
    }

    public ImageView getPositionImg() {
        return positionImg;
    }

    public void setPositionImg(ImageView positionImg) {
        this.positionImg = positionImg;
    }

    public TextView getNivelPlayer() {
        return nivelPlayer;
    }

    public void setNivelPlayer(TextView nivelPlayer) {
        this.nivelPlayer = nivelPlayer;
    }
}
