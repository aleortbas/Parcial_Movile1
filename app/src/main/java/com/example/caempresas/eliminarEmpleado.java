package com.example.caempresas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.mysql.jdbc.ResultSetImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class eliminarEmpleado extends AppCompatActivity {

    String dato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_empleado);

        dato = getIntent().getStringExtra("dato");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://192.168.0.18:3306/infoempresa", "alejandro", "ZZ11yy33Aa00");
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate("DELETE FROM empleados WHERE nombre = '"+dato.toString()+"'");


                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }


        }).start();
    }

    public void registrar(View view) {
    }

    public void volver(View view) {
    }
}