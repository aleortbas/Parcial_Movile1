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

    EditText NameMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleados);

        NameMail = findViewById(R.id.datos);
    }

    public void Crear(View view) {
        Intent empleado =new Intent(this,crearEmpleado.class);
        startActivity(empleado);
    }

    public void Editar(View view) {
        Intent empleado =new Intent(this,editarEmpleado.class);
        empleado.putExtra("dato", NameMail.getText().toString());
        startActivity(empleado);
    }

    public void borrar(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://192.168.0.18:3306/infoempresa", "alejandro", "ZZ11yy33Aa00");
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate("DELETE FROM empresa WHERE `nit` = '"+NameMail.getText().toString()+"'");


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