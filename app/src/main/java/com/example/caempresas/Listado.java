package com.example.caempresas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Listado extends AppCompatActivity {

    TextView lista;
    String Nit,TipoEmpresa,nombre,telefonoEmpresa,direccionEmpresa,correo;
    String resultadoNit,resultadoTipo,resultadoNombre,resultadoTelefon,resultadoDir,resultadoCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        lista = findViewById(R.id.lista);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://192.168.0.18:3306/infoempresa", "alejandro", "ZZ11yy33Aa00");
                    String sql = "SELECT * FROM empresa";
                    Statement stmt = con.createStatement();
                    ResultSet result = stmt.executeQuery(sql);
                    while(result.next()){
                        Nit = result.getString("nit");
                        TipoEmpresa = result.getString("tipoEmpresa");
                        nombre = result.getString("nombre");
                        telefonoEmpresa = result.getString("telefono");
                        direccionEmpresa = result.getString("direccion");
                        correo = result.getString("email");

                        resultadoDir += String.valueOf(Nit) +" "+ "Nombre: " + nombre + "Tipo: " +TipoEmpresa +" Telefono: " + telefonoEmpresa +
                                "Direccion :"+direccionEmpresa+"\n" + "\n";
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                lista.setText(resultadoDir);
                            }
                        });

                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }


        }).start();
    }

    public void atras(View view) {
        Intent index =new Intent(this,MainActivity.class);
        startActivity(index);
    }
}