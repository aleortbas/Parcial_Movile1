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

    int IDempleado,Telefono,NumeroIDEMPRESA,Salario;
    String TipoID,Nombres,Apellidos,Direccion,Correo,Cargo;
    String resulatoNombre,resulatoTipo,resulatoApellido,resulatoTel,resulatoDir,resulatoEmail,resulatoNumEmpre,resulatoCargo,resulatoSalario;
    String dato;

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

        dato = getIntent().getStringExtra("dato");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://192.168.0.18:3306/infoempresa", "alejandro", "ZZ11yy33Aa00");
                    String sql = "SELECT * FROM empleado WHERE nombre ='"+dato.toString()+"'";
                    Statement stmt = con.createStatement();
                    ResultSet result = stmt.executeQuery(sql);
                    while(result.next()){
                        IDempleado = result.getInt("numeroID");
                        TipoID = result.getString("tipoID");
                        Nombres = result.getString("nombre");
                        Apellidos = result.getString("apellidos");
                        Telefono = result.getInt("telefono");
                        Direccion = result.getString("direccion");
                        Correo = result.getString("email");
                        NumeroIDEMPRESA = result.getInt("numeroIdEmpresa");
                        Cargo= result.getString("cargo");
                        Salario = result.getInt("salario");

                        resulatoTipo = String.valueOf(TipoID);
                        resulatoNombre = String.valueOf(Nombres);
                        resulatoApellido = String.valueOf(Apellidos);
                        resulatoTel = String.valueOf(Telefono);
                        resulatoDir = String.valueOf(Direccion);
                        resulatoEmail = String.valueOf(Correo);
                        resulatoNumEmpre = String.valueOf(NumeroIDEMPRESA);
                        resulatoCargo = String.valueOf(Cargo);
                        resulatoSalario = String.valueOf(Salario);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                identificacion.setText(resulatoTipo);
                                nombreEmpleado.setText(resulatoNombre);
                                apellido.setText(resulatoApellido);
                                telefono.setText(resulatoTel);
                                direccion.setText(resulatoDir);
                                email.setText(resulatoEmail);
                                numeroEmpresa.setText(resulatoNumEmpre);
                                cargo.setText(resulatoCargo);
                                salario.setText(resulatoSalario);
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

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://192.168.0.18:3306/infoempresa","alejandro", "ZZ11yy33Aa00");
                    Statement stmt =  con.createStatement();
                    stmt.executeUpdate("UPDATE empleado SET  `tipoID` = '"+identificacion.getText().toString()+"', `nombre` = '"+nombreEmpleado.getText().toString()+"', " +
                           "`apellidos` = '"+apellido.getText().toString()+"', `telefono` = '"+telefono.getText().toString()+"', `direccion` = '"+direccion.getText().toString()+"'," +
                           " `email` = '"+email.getText().toString()+"', `cargo` = '"+cargo.getText().toString()+"', `salario` = '"+salario.getText().toString()+"' WHERE nombre = '"+dato.toString()+"'");

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