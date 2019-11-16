package app.daytoday.softpro.day_today_india;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NewsHeadline extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    RecyclerView.LayoutManager manager;

    DatabaseReference postref;
    String trndingApi = "https://newsapi.org/v2/top-headlines?country=in&apiKey=43fe1cfba39e4e25b8aca6449b002a4d";
    private List<news_data> examplelist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_headline);
        recyclerView = findViewById(R.id.recycler_news);
        progressBar = findViewById(R.id.progress_news);
        postref = FirebaseDatabase.getInstance().getReference().child("News Record").child(getIntent().getStringExtra("category"));

        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
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
                NewsAdaptor adaptor = new NewsAdaptor(NewsHeadline.this, examplelist);
                recyclerView.setAdapter(adaptor);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(NewsHeadline.this, "Data Fetching Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
