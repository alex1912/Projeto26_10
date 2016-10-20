package splashdev.devmedia.edu.br.webturismotcc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class bemVindo extends AppCompatActivity {

    Button btnEntrar,btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bem_vindo);

        btnEntrar = (Button)findViewById(R.id.btnEntrar);
        btnCadastrar= (Button)findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(bemVindo.this,TelaCadastro.class);
                startActivity(i);
                // fecha o activity atual
                finish();

            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(bemVindo.this,TelaPrincipal.class);
                startActivity(i);
                // fecha o activity atual
                finish();

            }
        });

    }
}
