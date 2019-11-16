package app.daytoday.softpro.day_today_india;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FavListActivity extends AppCompatActivity {
    ProgressBar progressBar;
    DatabaseReference postref;
    private RecyclerView recyclerView;
    private List<news_data> examplelist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_list);
        progressBar = findViewById(R.id.fav_progressbar);
        recyclerView = findViewById(R.id.fav_list);
        postref = FirebaseDatabase.getInstance().getReference().child("News Record").child(getIntent().getStringExtra("category"));
        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        DataMethod();
    }

    private void DataMethod() {
        postref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.GONE);
                for (DataSnapshot shot : dataSnapshot.getChildren()) {
                    news_data post = shot.getValue(news_data.class);
                    examplelist.add(0, post);
                }
                FavAdaptor adaptor = new FavAdaptor(FavListActivity.this, examplelist);
                recyclerView.setAdapter(adaptor);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(FavListActivity.this, "Data Fetching Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
