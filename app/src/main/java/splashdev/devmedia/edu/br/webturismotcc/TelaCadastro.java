package splashdev.devmedia.edu.br.webturismotcc;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelaCadastro extends AppCompatActivity {
        SQLiteDatabase cadastrado;
        EditText edtNome, edtEmail, edtSenha,edtUsuario;
        Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastro);

        cadastrado = openOrCreateDatabase("cadastrado",CONTEXT_IGNORE_SECURITY,null);


        edtNome = (EditText)findViewById(R.id.edtNome);
        edtUsuario = (EditText)findViewById(R.id.edtUsuario);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtSenha = (EditText)findViewById(R.id.edtSenha);
        btnCadastrar = (Button)findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                if (!(edtEmail.getText().toString().trim().equals(""))){
                    if (!(edtNome.getText().toString().trim().equals(""))){
                        if (!(edtSenha.getText().toString().trim().equals(""))){
                            if (!(edtUsuario.getText().toString().trim().equals(""))){

                                String nome = edtNome.getText().toString();
                                String usuario = edtUsuario.getText().toString();
                                String senha = edtSenha.getText().toString();
                                String email = edtEmail.getText().toString();




                    cadastrado.execSQL("INSERT into usuarios(usuario,nome,senha,email) VALUES('"+usuario+"', '"+nome+"','"+senha+"','"+email+"')");
                    Toast.makeText(TelaCadastro.this, "Turista Cadastrado Com Sucesso", Toast.LENGTH_SHORT).show();


                edtNome.setText(null);
                edtUsuario.setText(null);
                edtEmail.setText(null);
                edtSenha.setText(null);
                Intent i = new Intent(TelaCadastro.this,TelaPrincipal.class);
                startActivity(i);


            }else{
                Toast.makeText(TelaCadastro.this, "Preencher o campo Usuario.", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(TelaCadastro.this, "Preencher o campo Senha.", Toast.LENGTH_SHORT).show();
        }

    }else{
        Toast.makeText(TelaCadastro.this, "Preencher o campo Nome.", Toast.LENGTH_SHORT).show();
    }

}else{
    Toast.makeText(TelaCadastro.this, "Preencher o campo Email.", Toast.LENGTH_SHORT).show();
            }
                } catch (SQLException e) {
                    e.printStackTrace();
                    Toast.makeText(TelaCadastro.this, "Erro ao cadastrar", Toast.LENGTH_SHORT).show();
                }

                }});
}}


