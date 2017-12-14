package app.com.superdiaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonComprar, buttonProduto, buttonCarrinho, buttonSobre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        obterBotoes();

        buttonCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), CarrinhoComprasActivity.class));
                finish();
            }
        });

        buttonComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getBaseContext(), .class));
                //finish();
            }
        });

        buttonSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), SobreActivity.class));
                finish();
            }
        });

        buttonProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), ProdutoActivity.class));
                finish();
            }
        });

    }

    private void obterBotoes() {
        buttonComprar = (Button) findViewById(R.id.button3);
        buttonProduto = (Button) findViewById(R.id.button);
        buttonCarrinho = (Button)  findViewById(R.id.button4);
        buttonSobre =  (Button) findViewById(R.id.button2);
    }

}
