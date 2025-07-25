package com.example.menu_dinamico;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle; // Agregado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configurar DrawerLayout y NavigationView
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);

        // Configurar ícono del menú correctamente con ActionBarDrawerToggle
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState(); // Sincroniza el ícono con el DrawerLayout
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar , menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getTitle().toString()) {
            case "Nuevo":
                Toast.makeText(this.getApplicationContext(), "Ha presionado opción Nuevo ", Toast.LENGTH_LONG).show();
                return true;
            case "Buscar":
                Toast.makeText(this.getApplicationContext(), "Ha presionado opción Buscar ", Toast.LENGTH_LONG).show();
                return true;
            case "Config":
                Toast.makeText(this.getApplicationContext(), "Ha presionado opción Setting", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        boolean fragmentTransaction = false;
        Fragment fragment = null;

        switch (menuItem.getTitle().toString()) {
            case "Sección 1":
                fragment = new Fragment1();
                fragmentTransaction = true;
                break;
            case "Sección 2":
                fragment = new Fragment2();
                fragmentTransaction = true;
                break;
            case "Sección 3":
                fragment = new Fragment3();
                fragmentTransaction = true;
                break;
        }

        if (fragmentTransaction) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();

            menuItem.setChecked(true);
            getSupportActionBar().setTitle(menuItem.getTitle());
        }

        drawerLayout.closeDrawers();
        return true;
    }

    // Método para botón flotante
    public void onClickFAB(View view) {
        Snackbar.make(view, "Se presionó el FAB", Snackbar.LENGTH_LONG)
                .setAction("Acción", null).show();
    }
}
