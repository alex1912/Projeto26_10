package splashdev.devmedia.edu.br.webturismotcc;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends ActionBarActivity {
    SQLiteDatabase cadastrado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cadastrado = openOrCreateDatabase("cadastrado",CONTEXT_IGNORE_SECURITY,null);
        try {
            cadastrado.execSQL("create table if not exists usuarios(id integer not null primary key autoincrement, usuario text not null, nome text, senha text , email text unique)");
            cadastrado.execSQL("create table if not exists hoteis(id integer not null primary key autoincrement, nome text, descricao text, foto blob)");

            //Bitmap bitmap = R.drawable.praia1.getDrawable().getBitmap();
            //ByteArrayOutputStream saida = new ByteArrayOutputStream();
            //bitmap.compress(Bitmap.CompressFormat.PNG,100,saida);
            //byte[] img = saida.toByteArray();

        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Erro ao criar o banco", Toast.LENGTH_SHORT).show();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // este método será executado por 5 segundos
                Intent i = new Intent(MainActivity.this,bemVindo.class);
                startActivity(i);
                // fecha o activity atual
                finish();

            }
        }, Tempo_Splash);
    }
    private static int Tempo_Splash = 2500;
}
