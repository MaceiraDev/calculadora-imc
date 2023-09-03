package com.example.trabalho_calculadora_imc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnNext;
    Button btnCalcular;
    EditText textAltura, textPeso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textAltura = findViewById(R.id.textAltura);
        textPeso = findViewById(R.id.textPeso);
        btnCalcular = findViewById(R.id.btnCalcular);
        btnNext = findViewById(R.id.btnNext);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcular();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirTelaInformation();
            }
        });
    }
    private void calcular(){
        if (TextUtils.isEmpty(textAltura.getText().toString())){
            textAltura.setError("Campo Obrigatório");
            return;
        }
        if (TextUtils.isEmpty(textPeso.getText().toString())){
            textPeso.setError("Campo Obrigatório");
            return;
        }
        try {
                double valorAltura = Double.parseDouble(textAltura.getText().toString());
                double valorPeso = Double.parseDouble(textPeso.getText().toString());
                double resultado =  valorPeso / (valorAltura * valorAltura);

                if (resultado < 18.5){
                    mensagem("Resultado IMC: Margreza");
                } else if (resultado < 24.9) {
                    mensagem("Resultado IMC: Peso Normal");
                } else if (resultado < 29.9) {
                    mensagem("Resultado IMC: Sobrepeso");
                } else if (resultado < 39.9) {
                    mensagem("Resultado IMC: Obeso");
                }else {
                    mensagem("Resultado IMC: Obesidade Grave");
                }

        }catch (Exception e) {}
    }
    private  void mensagem(String texto){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Seu Resultado");
        alerta.setMessage(texto);
        alerta.setNeutralButton("OK" , null);
        alerta.show();
    }
    private void abrirTelaInformation() {
        Intent telaInformarion = new Intent(MainActivity.this, InformationActivity.class);
        startActivity(telaInformarion);
    }
}
