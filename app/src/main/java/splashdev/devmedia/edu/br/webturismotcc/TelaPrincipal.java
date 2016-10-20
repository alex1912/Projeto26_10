package splashdev.devmedia.edu.br.webturismotcc;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelaPrincipal extends AppCompatActivity {

    Button  btnEntrar;
    EditText edtEmail, edtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);


        btnEntrar= (Button) findViewById(R.id.btnEntrar);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtSenha = (EditText)findViewById(R.id.edtSenha);


        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity obj = new MainActivity();
                obj.cadastrado = openOrCreateDatabase("cadastrado",CONTEXT_IGNORE_SECURITY,null);
                String email = edtEmail.getText().toString();
                Cursor dados = obj.cadastrado.query("usuarios",new String[]{"usuario","senha","nome","email"},"email=?",new String[]{email},null,null,null);
                dados.moveToFirst();
                if(dados.getString(dados.getColumnIndex("senha")).equals(edtSenha.getText().toString())){
                    Intent i = new Intent(TelaPrincipal.this,HomeNova.class);
                    startActivity(i);

                }else{
                    Toast.makeText(TelaPrincipal.this, "Email e/ou senha incorretos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
