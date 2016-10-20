package splashdev.devmedia.edu.br.webturismotcc;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class HomeNova extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ListView lv;
    EditText edtProcurar;
    Button sv;
    SQLiteDatabase banco;
    Cursor dados;
    ArrayAdapter<String> adapter;
    ArrayList<String> lista;
    String Cidade;
    LinearLayout imagem;
    public int currentimageindex=0;
    Timer timer;
    TimerTask task;
    ImageView slidingimage;

    private int[] IMAGE_IDS = {R.drawable.hotel, R.drawable.castelinho};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_nova);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        sv = (Button) findViewById(R.id.sv);
        edtProcurar = (EditText)findViewById(R.id.edtProcurar);
        lv = (ListView)findViewById(R.id.lv);

        banco = openOrCreateDatabase("cadastrado",CONTEXT_IGNORE_SECURITY,null);
        try {
            banco.execSQL("create table if not exists locais(id integer not null primary key autoincrement, nome text not null, endereco text)");
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(HomeNova.this, "Erro ao criar o banco", Toast.LENGTH_SHORT).show();
        }
        // TESTE PARA PESQUISA
        banco.execSQL("INSERT INTO locais (nome,endereco) VALUES ('A','A')");
        banco.execSQL("INSERT INTO locais (nome,endereco) VALUES ('B','B')");
        banco.execSQL("INSERT INTO locais (nome,endereco) VALUES ('C','C')");


        pegaTodosDados();

        // AÇÃO DO BOTÃO, QUE INICIA A PROCURA
        sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cidade = edtProcurar.getText().toString();
                pegaDados(Cidade);
            }
        });// FIM DO BOTÃO



      // LÓGICA DE SLIDERSHOW

        final Handler mHandler = new Handler();

        // Create runnable for posting
        final Runnable mUpdateResults = new Runnable() {
            public void run() {
                AnimateandSlideShow(); // MÉTODO SLIDE, ESTÁ NO FIM DA CLASSE
            }
        };

        int delay = 1000; // delay for 1 sec.

        int period = 4000; // repeat every 4 sec.

        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                mHandler.post(mUpdateResults);
            }

        }, delay, period);


      // FIM DA LÓGICA DE SLIDE

    } // FIM DO ON CREATE


    //MOSTRA TODOS OS DADOS NO LIST VIEW
    public void pegaTodosDados(){
        dados = banco.query("locais",new String[]{"nome","endereco"},null,null,null,null,"nome");
        lista = new ArrayList<String>();
        while(dados.moveToNext()){
            lista.add(dados.getString(dados.getColumnIndex("nome")));
            lv.setAdapter(adapter);
        }
        adapter = new ArrayAdapter<>(HomeNova.this,android.R.layout.simple_list_item_1,lista);
        lv.setAdapter(adapter);
    } // FIM DO MOSTRA TODOS


    // PEGA DETERMINADO  CARACTER
    public void pegaDados(String Cidade){

        dados = banco.query("locais",new String[]{"nome","endereco"},"nome=?",new String[]{Cidade},null,null,null);

        lista = new ArrayList<String>();
        while(dados.moveToNext()){
            lista.add(dados.getString(dados.getColumnIndex("nome")));
            lv.setAdapter(adapter);
        }
        adapter = new ArrayAdapter<>(HomeNova.this,android.R.layout.simple_list_item_1,lista);
        lv.setAdapter(adapter);
    } // FIM DO DETERMINADO

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_nova, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_hoteis) {
            Intent i = new Intent(HomeNova.this, HotelDrawner.class);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.nav_restaurante) {

        } else if (id == R.id.nav_pontost) {

        }else if (id == R.id.nav_home) {
            Intent i = new Intent(HomeNova.this, HomeNova.class);
            startActivity(i);

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

   // LÓGICA DO SLIDE
    private void AnimateandSlideShow() {

        slidingimage = (ImageView)findViewById(R.id.imageView2);
        slidingimage.setImageResource(IMAGE_IDS[currentimageindex%IMAGE_IDS.length]);

        currentimageindex++;

        //Animation rotateimage = AnimationUtils.loadAnimation(this, R.anim.custom_anim);

        //slidingimage.startAnimation(rotateimage);

    } //FIM DA LÓGICA SLIDE

}


