package app.com.superdiaapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import app.com.superdiaapp.modelo.Usuario;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private EditText login, senha;
    private Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Cadastrar Usuário");

        obterInformacoes();

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Cadastrar", Toast.LENGTH_SHORT).show();
                cadastraUsuario();
            }
        });

        //TODO: Verificar se usuário já existe, solicitar cadastro, e fazer verificação de sucesso pelas mensagens retornadas
    }

    private void cadastraUsuario() {
        Usuario usuario = new Usuario();
        usuario.setUsuario(login.getText().toString());
        usuario.setSenha(senha.getText().toString());
        //usuario.setPerfil();

        if(!verificaUsuario(usuario)){
            Toast.makeText(getBaseContext(),
                    "Usuário já existe. Para ter acesso ao Super Dia ir para página de Login", Toast.LENGTH_LONG);
            return;
        }else{
            try{
                JSONObject jsonBody = new JSONObject();
                jsonBody.put("usuario", usuario.getUsuario());
                jsonBody.put("senha", usuario.getSenha());
                //jsonBody.put("perfil", );
            }catch (JSONException e){

            }

        }

    }

    private boolean verificaUsuario(Usuario usuario) {
        //TODO: retorna false caso usuario ja exista, true caso nao exista
        return false;
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
