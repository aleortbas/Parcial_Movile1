package com.example.caempresas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class crearEmpresa extends AppCompatActivity {

    EditText nit;
    EditText nombreEmpresa;
    EditText tipoEmpresa;
    EditText telefono;
    EditText direccion;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_empresa);

        nit = findViewById(R.id.nit);
        nombreEmpresa = findViewById(R.id.nombre);
        tipoEmpresa = findViewById(R.id.tipo);
        telefono = findViewById(R.id.telefono);
        direccion = findViewById(R.id.direccion);
        email = findViewById(R.id.email);
    }

    public void registrar(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                //conexion
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://192.168.0.18:3306/infoempresa","alejandro", "ZZ11yy33Aa00");
                    Statement stmt =  con.createStatement();
                    stmt.executeUpdate("INSERT INTO empresa VALUES(NULL,'"+nit.getText().toString()+"','"+tipoEmpresa.getText().toString()+"','"+nombreEmpresa.getText().toString()+"','"+telefono.getText().toString()+"','"+direccion.getText().toString()+"','"+email.getText().toString()+"')");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }).start();
    }

    public void volver(View view) {
    }
}