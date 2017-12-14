package app.com.superdiaapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.com.superdiaapp.modelo.Usuario;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private EditText login, senha;
    private Button cadastrar;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Cadastrar Usuário");

        obterInformacoes();

        usuario = new Usuario();

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Cadastrar", Toast.LENGTH_SHORT).show();
            }
        });

        //TODO: Verificar se usuário já existe, solicitar cadastro, e fazer verificação de sucesso pelas mensagens retornadas

    }

    private void obterInformacoes() {
        login = (EditText) findViewById(R.id.editTextNomeUsuario);
        senha = (EditText) findViewById(R.id.editTextSenha);
        cadastrar = (Button) findViewById(R.id.buttonCadastrar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
