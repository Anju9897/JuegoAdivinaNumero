package com.uso.exma2rg17i04002.models;

import java.io.Serializable;
import java.util.ArrayList;

public class usuarioJugador implements Serializable {

    private String nick;
    private int intentos;
    private int score;
    private String nivel;

    public static ArrayList<usuarioJugador> lstJugadores = new ArrayList<>();

    public usuarioJugador(){
        this.nick = "";
        this.intentos = 0;
        this.score = 0;
        this.nivel = "";
    }

    public usuarioJugador(String nick, int intentos, int score, String nivel) {
        this.nick = nick;
        this.intentos = intentos;
        this.score = score;
        this.nivel = nivel;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
