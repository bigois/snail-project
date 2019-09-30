package br.com.fiap.appglasseek.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.fragment.AjudaFragment;
import br.com.fiap.appglasseek.fragment.CarrinhoFragment;
import br.com.fiap.appglasseek.fragment.ComprasFragment;
import br.com.fiap.appglasseek.fragment.FavoritosFragment;
import br.com.fiap.appglasseek.fragment.InicioFragment;
import br.com.fiap.appglasseek.fragment.LoginFragment;
import br.com.fiap.appglasseek.fragment.PerfilFragment;
import br.com.fiap.appglasseek.model.Usuario;
import br.com.fiap.appglasseek.service.LoginUtility;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView userEmail, userName;
    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InicioFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_inicio);
        }

        navigationView.setCheckedItem(R.id.nav_inicio);
    }

    @Override
    public void onBackPressed() {
        getSupportFragmentManager().popBackStack();

        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (currentFragment instanceof InicioFragment){
            new AlertDialog.Builder(this)
                .setMessage("Deseja realmente sair da aplicação?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MenuActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_inicio) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InicioFragment()).addToBackStack(null).commit();

        } else if (id == R.id.nav_perfil) {
            if (LoginUtility.isLogged(getApplicationContext())) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PerfilFragment()).addToBackStack(null).commit();
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LoginFragment()).addToBackStack(null).commit();
            }
        } else if (id == R.id.nav_favoritos) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FavoritosFragment()).addToBackStack(null).commit();

        } else if (id == R.id.nav_compras) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ComprasFragment()).addToBackStack(null).commit();

        } else if (id == R.id.nav_carrinho) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CarrinhoFragment()).addToBackStack(null).commit();

        } else if (id == R.id.nav_ajuda) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AjudaFragment()).addToBackStack(null).commit();

        } else if (id == R.id.nav_sair) {
            if (LoginUtility.isLogged(getApplicationContext())) {
                LoginUtility.logOut(getApplicationContext());
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InicioFragment()).addToBackStack(null).commit();
                Toast.makeText(this, "Você foi desconectado!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Você não está conectado!", Toast.LENGTH_SHORT).show();
            }

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
