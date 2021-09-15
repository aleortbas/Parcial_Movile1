package com.example.caempresas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Listado extends AppCompatActivity {

    EditText prueba;
    Spinner lista;
    String resultado="",Nombre,dato;
    int IDempresa;
    ArrayList<String> opciones2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        prueba = findViewById(R.id.datos);
        lista = findViewById(R.id.lista);
        opciones2 = new ArrayList<String>();
        opciones2.add("Seleccione una empresa");

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://192.168.0.18:3306/infoempresa", "alejandro", "ZZ11yy33Aa00");
                    String sql2 = "SELECT * FROM `empresa`";
                    Statement stmt2 = con.createStatement();
                    ResultSet result2 = stmt2.executeQuery(sql2);
                    while(result2.next()){

                        IDempresa = result2.getInt("Id_empresa");
                        Nombre = result2.getString("nombre");
                        resultado = String.valueOf("")+ "ID empresa: " + IDempresa + " Nombre: " + Nombre + "\n";
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                opciones2.add(resultado);
                            }
                        });
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    con.close();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }


        }).start();

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, opciones2);
        lista.setAdapter(adapter2);


    }

    public void atras(View view) {
        Intent index =new Intent(this,MainActivity.class);
        startActivity(index);
    }

    public void Buscar(View view) {
        dato = lista.getSelectedItem().toString();
        prueba.setText(dato);
        Intent empresas =new Intent(this,ListaEmpresas.class);
        empresas.putExtra("dato", dato);
    }
}