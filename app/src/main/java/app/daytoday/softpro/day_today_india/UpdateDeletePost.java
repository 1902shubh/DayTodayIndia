package app.daytoday.softpro.day_today_india;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class UpdateDeletePost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_post);
    }

    public void trending(View v) {
        Intent i = new Intent(UpdateDeletePost.this, DeleteList.class);
        i.putExtra("category", "Trending");
        startActivity(i);
    }

    public void sports(View v) {
        Intent i = new Intent(UpdateDeletePost.this, DeleteList.class);
        i.putExtra("category", "Sports");
        startActivity(i);
    }

    public void politics(View v) {
        Intent i = new Intent(UpdateDeletePost.this, DeleteList.class);
        i.putExtra("category", "Politics");
        startActivity(i);
    }

    public void education(View v) {
        Intent i = new Intent(UpdateDeletePost.this, DeleteList.class);
        i.putExtra("category", "Education");
        startActivity(i);
    }

    public void international(View v) {
        Intent i = new Intent(UpdateDeletePost.this, DeleteList.class);
        i.putExtra("category", "International");
        startActivity(i);
    }

    public void crime(View v) {
        Intent i = new Intent(UpdateDeletePost.this, DeleteList.class);
        i.putExtra("category", "Crime");
        startActivity(i);
    }

    public void business(View v) {
        Intent i = new Intent(UpdateDeletePost.this, DeleteList.class);
        i.putExtra("category", "Business");
        startActivity(i);
    }

    public void technology(View v) {
        Intent i = new Intent(UpdateDeletePost.this, DeleteList.class);
        i.putExtra("category", "Technology");
        startActivity(i);
    }
}
