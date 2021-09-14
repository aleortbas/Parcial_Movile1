package com.example.caempresas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class crearEmpleado extends AppCompatActivity {

    EditText identificacion;
    EditText nombreEmpleado;
    EditText apellido;
    EditText telefono;
    EditText direccion;
    EditText email;
    EditText numeroEmpresa;
    EditText cargo;
    EditText salario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_empleado);

        identificacion = findViewById(R.id.ident);
        nombreEmpleado = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellidos);
        telefono = findViewById(R.id.telefono);
        direccion = findViewById(R.id.direccion);
        email = findViewById(R.id.email);
        numeroEmpresa = findViewById(R.id.idEmpresa);
        cargo = findViewById(R.id.cargo);
        salario = findViewById(R.id.salario);
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
                    stmt.executeUpdate("INSERT INTO empleado VALUES(NULL,'"+identificacion.getText().toString()+"','"+nombreEmpleado.getText().toString()+"','"+apellido.getText().toString()+"', '"+telefono.getText().toString()+"', '"+direccion.getText().toString()+"', '"+email.getText().toString()+"','"+numeroEmpresa.getText().toString()+"','"+cargo.getText().toString()+"','"+salario.getText().toString()+"')");

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }).start();
    }

    public void volver(View view) {
        Intent empleado =new Intent(this,empleados.class);
        startActivity(empleado);
    }
}