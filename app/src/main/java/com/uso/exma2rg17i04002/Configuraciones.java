package com.uso.exma2rg17i04002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Configuraciones extends AppCompatActivity {

    public static final String ARCHIVO = "ConfJugador";

    //claves
    public static final String JUGADOR = "NICK";
    public static final String NIVEL = "NIVEL";

    //variables
    private SharedPreferences configuraciones;
    private EditText userNick;
    private RadioGroup rgNiveles;
    private Button guardarConf;
    private RadioButton seleccion;

    private RadioButton rbFacil;
    private RadioButton rbMedio;
    private RadioButton rbDificil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuraciones);
        this.rgNiveles = findViewById(R.id.rgNiveles);
        this.userNick = findViewById(R.id.txtJugador);
        this.guardarConf = findViewById(R.id.btnGuardarConf);

        this.rbFacil = findViewById(R.id.rbFacil);
        this.rbMedio = findViewById(R.id.rbMedio);
        this.rbDificil = findViewById(R.id.rbDificil);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.configuraciones = getSharedPreferences(ARCHIVO,MODE_PRIVATE);

        CargarConfigs();

        this.guardarConf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seleccion = findViewById(rgNiveles.getCheckedRadioButtonId());
                String nick = userNick.getText().toString();
                String nivel = seleccion.getText().toString();
                if(configuraciones != null){
                    if(ValidarValores(nivel,nick)){
                        SharedPreferences.Editor editor = configuraciones.edit();
                        editor.putString(JUGADOR,nick);
                        editor.putString(NIVEL,nivel);
                        editor.commit();

                        if(editor.commit()){
                            Toast.makeText(Configuraciones.this, "Configuracion Guardada con Exito", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(Configuraciones.this,"Error al guardar la configuracion",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(Configuraciones.this,"Debe Completar los valores",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    private void CargarConfigs() {
        if(configuraciones != null){
            userNick.setText(configuraciones.getString(JUGADOR,""));

            String nivelUsuario = configuraciones.getString(NIVEL,"");

            if(rbFacil.getText().toString().equals(nivelUsuario)){
                rbFacil.setChecked(true);
                rbMedio.setChecked(false);
                rbDificil.setChecked(false);
            }else if(rbMedio.getText().toString().equals(nivelUsuario)){
                rbFacil.setChecked(false);
                rbMedio.setChecked(true);
                rbDificil.setChecked(false);
            } else if(rbDificil.getText().toString().equals(nivelUsuario)){
                rbFacil.setChecked(false);
                rbMedio.setChecked(false);
                rbDificil.setChecked(true);
            }
        }
    }

    private boolean ValidarValores(String rgNiveles, String userNick) {
        if(rgNiveles.equals("") && userNick.equals("")){
            return false;
        }else{
            return true;
        }
    }
}