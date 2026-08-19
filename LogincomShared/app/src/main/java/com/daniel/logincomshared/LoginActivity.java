package com.daniel.logincomshared;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    EditText nome;
    EditText email;
    EditText senha;
    Button cadastrar, voltar;
    CheckBox lembrarCredenciais;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        initComponents(); // iniciar os componentes
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validarDados()) {
                    if (lembrarCredenciais.isChecked()) {
                        preferences = (SharedPreferences) getSharedPreferences("login", 0); // nome do arquivo, modo privado
                        SharedPreferences.Editor dados =  preferences.edit();
                        dados.putString("Nome", nome.getText().toString() );
                        dados.putString("Email", email.getText().toString() );
                        dados.putString("Senha", senha.getText().toString() );
                        dados.apply();
                    }
                }
                // limpar o campo
                nome.setText("");
                email.setText("");
                senha.setText("");

                Intent intent = new Intent(LoginActivity.this, MainActivity.class); // ir para a tela Cadastro
                startActivity(intent);
            }
        });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private boolean validarDados() {
        boolean retorno = true;

        if (nome.getText().toString().isEmpty()) {
            retorno = false;
            nome.setError("Campo Nome não pode estar vazio!");
        }
        if (email.getText().toString().isEmpty()) {
            retorno = false;
            email.setError("Campo Email não pode estar vazio!");
        }
        if (senha.getText().toString().isEmpty()) {
            retorno = false;
            senha.setError("Campo Senha não pode estar vazio!");
        }

        return retorno;
    }

    private void initComponents() {
        nome = findViewById(R.id.edt_nome);
        email = findViewById(R.id.edt_email);
        senha = findViewById(R.id.edt_senha);
        cadastrar = findViewById(R.id.btn_cadastrar);
        voltar = findViewById(R.id.btn_voltar);
        lembrarCredenciais = findViewById(R.id.checkbox_lembrar);
    }


}