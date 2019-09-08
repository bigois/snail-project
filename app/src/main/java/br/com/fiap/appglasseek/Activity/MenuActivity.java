package br.com.fiap.appglasseek.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import br.com.fiap.appglasseek.Fragments.AjudaFragment;
import br.com.fiap.appglasseek.Fragments.CarrinhoFragment;
import br.com.fiap.appglasseek.Fragments.ComprasFragment;
import br.com.fiap.appglasseek.Fragments.FavoritosFragment;
import br.com.fiap.appglasseek.Fragments.InicioFragment;
import br.com.fiap.appglasseek.Fragments.PerfilFragment;
import br.com.fiap.appglasseek.R;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InicioFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_inicio);
        }

        navigationView.setCheckedItem(R.id.nav_inicio);


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
        getMenuInflater().inflate(R.menu.menu, menu);
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


        if (id == R.id.nav_inicio) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InicioFragment()).commit();

        } else if (id == R.id.nav_perfil) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PerfilFragment()).commit();

        } else if (id == R.id.nav_favoritos) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FavoritosFragment()).commit();

        } else if (id == R.id.nav_compras) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ComprasFragment()).commit();

        } else if (id == R.id.nav_carrinho) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CarrinhoFragment()).commit();

        } else if (id == R.id.nav_ajuda) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AjudaFragment()).commit();

        } else if (id == R.id.nav_sair) {
            Intent intent = new Intent(MenuActivity.this, MainActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
