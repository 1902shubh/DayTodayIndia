package app.daytoday.softpro.day_today_india;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class AdminDashBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash_board);
    }

    public void addPost(View v) {
        startActivity(new Intent(AdminDashBoard.this, AddPost.class));
        finish();
    }

    public void upDelData(View v) {
        startActivity(new Intent(AdminDashBoard.this, UpdateDeletePost.class));
    }

    public void logout(View v) {
        FirebaseAuth mauth = FirebaseAuth.getInstance();
        mauth.signOut();
        Toast.makeText(this, "Logout Successfull", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(AdminDashBoard.this, AdminLogin.class));
        finish();
    }
}
