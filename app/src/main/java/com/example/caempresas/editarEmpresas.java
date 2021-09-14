package com.example.caempresas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class editarEmpresas extends AppCompatActivity {

    EditText nit;
    EditText nombreEmpresa;
    EditText tipoEmpresa;
    EditText telefono;
    EditText direccion;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_empresas);

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
                    stmt.executeUpdate("UPDATE empresa SET  `nit` = '"+nit.getText().toString()+"', `tipoEmpresa` = '"+tipoEmpresa.getText().toString()+"', " +
                            "`nombre` = '"+nombreEmpresa.getText().toString()+"', `telefono` = '"+telefono.getText().toString()+"', `direccion` = '"+direccion.getText().toString()+"'," +
                            " `email` = '"+email.getText().toString()+"' WHERE Id_empresa = '2'");

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }).start();
    }
}