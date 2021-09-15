package com.example.caempresas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class editarEmpresas extends AppCompatActivity {

    EditText nit;
    EditText nombreEmpresa;
    EditText tipoEmpresa;
    EditText telefono;
    EditText direccion;
    EditText email;

    String dato;
    String Nit,TipoEmpresa,nombre,telefonoEmpresa,direccionEmpresa,correo;
    String resultadoNit,resultadoTipo,resultadoNombre,resultadoTelefon,resultadoDir,resultadoCorreo;

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

        dato = getIntent().getStringExtra("dato");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://192.168.0.18:3306/infoempresa", "alejandro", "ZZ11yy33Aa00");
                    String sql = "SELECT * FROM empresa WHERE nit ='"+dato.toString()+"'";
                    Statement stmt = con.createStatement();
                    ResultSet result = stmt.executeQuery(sql);
                    while(result.next()){
                        Nit = result.getString("nit");
                        TipoEmpresa = result.getString("tipoEmpresa");
                        nombre = result.getString("nombre");
                        telefonoEmpresa = result.getString("telefono");
                        direccionEmpresa = result.getString("direccion");
                        correo = result.getString("email");

                        resultadoTipo = String.valueOf(TipoEmpresa);
                        resultadoNit = String.valueOf(Nit);
                        resultadoNombre = String.valueOf(nombre);
                        resultadoTelefon = String.valueOf(telefonoEmpresa);
                        resultadoCorreo = String.valueOf(correo);
                        resultadoDir = String.valueOf(direccionEmpresa);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tipoEmpresa.setText(resultadoTipo);
                                nit.setText(resultadoNit);
                                nombreEmpresa.setText(resultadoNombre);
                                telefono.setText(resultadoTelefon);
                                direccion.setText(resultadoDir);
                                email.setText(resultadoCorreo);
                            }
                        });

                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }


        }).start();
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
                            " `email` = '"+email.getText().toString()+"' WHERE nit = '"+dato.toString()+"'");

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }).start();
    }
    public void volver(View view) {
        Intent index =new Intent(this,MainActivity.class);
        startActivity(index);
    }
}