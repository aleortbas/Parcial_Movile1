package com.example.caempresas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class empresas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresas);
    }

    public void Crear(View view) {
        Intent empresas =new Intent(this,crearEmpresa.class);
        startActivity(empresas);
    }

    public void Editar(View view) {
        Intent empresas =new Intent(this,editarEmpresas.class);
        startActivity(empresas);
    }

    public void borrar(View view) {
        Intent empresas =new Intent(this,eliminarEmpresa.class);
        startActivity(empresas);
    }
}