package splashdev.devmedia.edu.br.webturismotcc;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class HotelDrawner extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {

    private ListView lista;
    private AdapterListView adapterListView;
    private ArrayList<ItemListView> itens;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_drawner);
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

        lista= (ListView) findViewById(R.id.listaHoteis);
        //Define o Listener quando alguem clicar no item.



       lista.setOnItemClickListener(this);
        createListView();
    }

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
        getMenuInflater().inflate(R.menu.hotel_drawner, menu);
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
            Intent i = new Intent(HotelDrawner.this, HotelDrawner.class);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.nav_restaurante) {

        } else if (id == R.id.nav_pontost) {

        }else if (id == R.id.nav_home) {
            Intent i = new Intent(HotelDrawner.this, HomeNova.class);
            startActivity(i);

        } else if (id == R.id.nav_send) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void createListView() {
        //Criamos nossa lista que preenchera o ListView
        itens = new ArrayList<ItemListView>();
        ItemListView item1 = new ItemListView("teo1", R.drawable.slide1);
        ItemListView item2 = new ItemListView("teo2", R.drawable.slide2);
        ItemListView item3 = new ItemListView("teo3", R.drawable.slide3);


        itens.add(item1);
        itens.add(item2);
        itens.add(item3);


        //Cria o adapter
        adapterListView = new AdapterListView(this, itens);

        //Define o Adapter
        lista.setAdapter(adapterListView);
        //Cor quando a lista é selecionada para ralagem.
        lista.setCacheColorHint(Color.TRANSPARENT);

    }

    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3 )
    {
        //Pega o item que foi selecionado.
        //ItemListView item = (ItemListView) adapterListView.getItem(arg2);
        Toast.makeText(this,"aaa "+ arg2, Toast.LENGTH_SHORT).show();
        //Demostração
       //Intent i = new Intent(HotelDrawner.this,LayoutPadrao.class);
        //startActivity(i);
    }
}

