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

public class empleados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleados);


    }
    public void Crear(View view) {
        Intent empleado =new Intent(this,crearEmpleado.class);
        startActivity(empleado);
    }

    public void Editar(View view) {
        Intent empleado =new Intent(this,editarEmpleado.class);
        startActivity(empleado);
    }

    public void borrar(View view) {
        Intent empleado =new Intent(this,eliminarEmpleado.class);
        startActivity(empleado);
    }
}