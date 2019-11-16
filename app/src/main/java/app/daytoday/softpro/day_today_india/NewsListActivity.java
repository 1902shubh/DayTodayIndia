package app.daytoday.softpro.day_today_india;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NewsListActivity extends AppCompatActivity {

    ProgressBar progressBar;
    DatabaseReference postref;
    private RecyclerView recyclerView;
    private List<news_data> examplelist = new ArrayList<>();
    private RelativeLayout errorLayout;
    private ImageView errorImage;
    private Button btnretry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        progressBar = findViewById(R.id.progressbar);
        recyclerView = findViewById(R.id.news_list);
        errorLayout = findViewById(R.id.errorLayout);
        errorImage = findViewById(R.id.errorImage);
        btnretry = findViewById(R.id.btnretry);
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
                NewsAdaptor adaptor = new NewsAdaptor(NewsListActivity.this, examplelist);
                recyclerView.setAdapter(adaptor);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(NewsListActivity.this, "Data Fetching Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showErrorMessage(int imageView) {
        if (errorLayout.getVisibility() == View.GONE) {
            errorLayout.setVisibility(View.VISIBLE);
        }
        errorImage.setImageResource(imageView);
        btnretry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
