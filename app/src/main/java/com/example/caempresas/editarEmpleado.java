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

public class editarEmpleado extends AppCompatActivity {

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
        setContentView(R.layout.activity_editar_empleado);

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
                    stmt.executeUpdate("UPDATE empleado SET  `tipoID` = '"+identificacion.getText().toString()+"', `nombre` = '"+nombreEmpleado.getText().toString()+"', " +
                           "`apellidos` = '"+apellido.getText().toString()+"', `telefono` = '"+telefono.getText().toString()+"', `direccion` = '"+direccion.getText().toString()+"'," +
                           " `email` = '"+email.getText().toString()+"', `cargo` = '"+cargo.getText().toString()+"', `salario` = '"+salario.getText().toString()+"' WHERE numeroID = '8'");

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