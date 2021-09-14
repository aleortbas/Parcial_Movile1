package com.example.caempresas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void irEmpresa(View view) {

        Intent empresa =new Intent(this,empresas.class);
        startActivity(empresa);
    }

    public void irEmpleado(View view) {

        Intent empleado =new Intent(this,empleados.class);
        startActivity(empleado);
    }

    public void irLista(View view) {
    }

}