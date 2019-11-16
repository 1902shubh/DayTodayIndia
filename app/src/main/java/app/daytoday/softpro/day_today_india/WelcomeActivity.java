package app.daytoday.softpro.day_today_india;

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
import android.view.View;

public class WelcomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome, menu);
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
        if (id == R.id.action_about) {
            startActivity(new Intent(WelcomeActivity.this, AboutusActivity.class));

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_admin) {
            startActivity(new Intent(WelcomeActivity.this, AdminLogin.class));
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_favourite) {
            Intent i = new Intent(WelcomeActivity.this, FavListActivity.class);
            i.putExtra("category", "Favourite");
            startActivity(i);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void trending(View v) {
        Intent i = new Intent(WelcomeActivity.this, NewsHeadline.class);
        i.putExtra("category", "Trending");
        startActivity(i);
    }

    public void sports(View v) {
        Intent i = new Intent(WelcomeActivity.this, NewsListActivity.class);
        i.putExtra("category", "Sports");
        startActivity(i);
    }

    public void politics(View v) {
        Intent i = new Intent(WelcomeActivity.this, NewsListActivity.class);
        i.putExtra("category", "Politics");
        startActivity(i);
    }

    public void education(View v) {
        Intent i = new Intent(WelcomeActivity.this, NewsListActivity.class);
        i.putExtra("category", "Education");
        startActivity(i);
    }

    public void international(View v) {
        Intent i = new Intent(WelcomeActivity.this, NewsListActivity.class);
        i.putExtra("category", "International");
        startActivity(i);
    }

    public void crime(View v) {
        Intent i = new Intent(WelcomeActivity.this, NewsListActivity.class);
        i.putExtra("category", "Crime");
        startActivity(i);
    }

    public void business(View v) {
        Intent i = new Intent(WelcomeActivity.this, NewsListActivity.class);
        i.putExtra("category", "Business");
        startActivity(i);
    }

    public void technology(View v) {
        Intent i = new Intent(WelcomeActivity.this, NewsListActivity.class);
        i.putExtra("category", "Technology");
        startActivity(i);
    }
}
