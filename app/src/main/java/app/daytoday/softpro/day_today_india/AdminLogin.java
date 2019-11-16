package app.daytoday.softpro.day_today_india;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminLogin extends AppCompatActivity {

    EditText userid, password;
    Button btn;
    FirebaseAuth auth;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Toast.makeText(this, "You are already Login", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(AdminLogin.this, AdminDashBoard.class);
            startActivity(i);
            finish();
        }
        pd = new ProgressDialog(this);
        userid = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        btn = findViewById(R.id.btn_login);
        FirebaseApp.initializeApp(AdminLogin.this);
        auth = FirebaseAuth.getInstance();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid = userid.getText().toString();
                String pwd = password.getText().toString();
                if (uid.isEmpty()) {
                    userid.setError("Please Input your email");
                    userid.requestFocus();
                } else if (pwd.isEmpty()) {
                    password.setError("Please Enter your Password");
                    password.requestFocus();
                } else {
                    pd.setMessage("Loading...");
                    pd.show();
                    pd.setCanceledOnTouchOutside(false);
                    auth.signInWithEmailAndPassword(uid.trim(), pwd.trim()).addOnCompleteListener(AdminLogin.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                pd.dismiss();
                                startActivity(new Intent(AdminLogin.this, AdminDashBoard.class));
                                finish();
                            } else {
                                pd.dismiss();
                                Toast.makeText(AdminLogin.this, "Invalid Userid or Password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
