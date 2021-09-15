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

public class empresas extends AppCompatActivity {

    EditText nit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresas);

        nit = findViewById(R.id.datos);
    }

    public void Crear(View view) {
        Intent empresas =new Intent(this,crearEmpresa.class);
        startActivity(empresas);
    }

    public void Editar(View view) {
        Intent empresas =new Intent(this,editarEmpresas.class);
        empresas.putExtra("dato", nit.getText().toString());
        startActivity(empresas);
    }

    public void borrar(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://192.168.0.18:3306/infoempresa", "alejandro", "ZZ11yy33Aa00");
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate("DELETE FROM empleado WHERE nombre = '"+nit.getText().toString()+"'");


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