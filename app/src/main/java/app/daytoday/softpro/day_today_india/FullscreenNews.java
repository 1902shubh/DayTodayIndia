package app.daytoday.softpro.day_today_india;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class FullscreenNews extends AppCompatActivity {

    TextView tv_head, tv_desc, tv_date, tv_time, pre_date, pre_time;
    ImageView news_img, gmail, whatsapp, twitter, share;
    DatabaseReference newsref;
    LinearLayout layout, fullScreenImage;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_news);

        layout = findViewById(R.id.fullscreenlayout);
        radioGroup = findViewById(R.id.rgroup);
        tv_head = findViewById(R.id.f_tv_head);
        tv_desc = findViewById(R.id.f_tv_desc);
        tv_date = findViewById(R.id.f_tv_date);
        tv_time = findViewById(R.id.f_tv_time);
        pre_date = findViewById(R.id.preview_date);
        pre_time = findViewById(R.id.preview_time);
        news_img = findViewById(R.id.f_img);
        fullScreenImage = findViewById(R.id.fullScreenImage);
        newsref = FirebaseDatabase.getInstance().getReference().child("News Record").child("Favourite");
        tv_head.setText(getIntent().getStringExtra("head"));
        tv_desc.setText(getIntent().getStringExtra("desc"));
        tv_date.setText(getIntent().getStringExtra("date"));
        tv_time.setText(getIntent().getStringExtra("time"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tv_desc.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        }
        Picasso.get().load(getIntent().getStringExtra("img")).into(news_img);
        gmail = findViewById(R.id.btn_gmail);
        whatsapp = findViewById(R.id.btn_whatsapp);
        twitter = findViewById(R.id.btn_twitter);
        share = findViewById(R.id.btn_share);
        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, getIntent().getStringExtra("head"));
                i.putExtra(Intent.EXTRA_TEXT, getIntent().getStringExtra("desc"));
                i.setPackage("com.google.android.gm");
                startActivity(i);
            }
        });
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj = new Intent(Intent.ACTION_SEND);
                obj.setType("text/plain");
                obj.putExtra(Intent.EXTRA_TEXT, "*" + getIntent().getStringExtra("head") + "* " + getIntent().getStringExtra("desc"));
                obj.setPackage("com.whatsapp");
                startActivity(obj);
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String uniquekey = newsref.push().getKey();
                String head = tv_head.getText().toString().trim();
                String desc = tv_desc.getText().toString().trim();
                String url;
                url = String.valueOf(news_img);
                HashMap hp = new HashMap();
                hp.put("Headline", head);
                hp.put("Description", desc);
                hp.put("imgurl", getIntent().getStringExtra("img"));
                hp.put("Key", uniquekey);
                newsref.child(uniquekey).updateChildren(hp).addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(FullscreenNews.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(FullscreenNews.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj = new Intent(Intent.ACTION_SEND);
                obj.setType("text/plain");
                obj.putExtra(Intent.EXTRA_TEXT, "*" + getIntent().getStringExtra("head") + "*" + getIntent().getStringExtra("desc"));
                startActivity(obj);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.darkmode) {
                    layout.setBackgroundColor(Color.parseColor("#2c3335"));
                    fullScreenImage.setBackgroundColor(Color.parseColor("#2c3335"));
                    tv_head.setTextColor(Color.parseColor("#ffffff"));
                    tv_desc.setTextColor(Color.parseColor("#ffffff"));
                    tv_date.setTextColor(Color.parseColor("#ffffff"));
                    tv_time.setTextColor(Color.parseColor("#ffffff"));
                    pre_date.setTextColor(Color.parseColor("#ffffff"));
                    pre_time.setTextColor(Color.parseColor("#ffffff"));
                } else if (checkedId == R.id.lightmode) {
                    layout.setBackgroundColor(Color.parseColor("#ffffff"));
                    fullScreenImage.setBackgroundColor(Color.parseColor("#ffffff"));
                    tv_head.setTextColor(Color.parseColor("#000000"));
                    tv_desc.setTextColor(Color.parseColor("#000000"));
                    tv_date.setTextColor(Color.parseColor("#000000"));
                    pre_time.setTextColor(Color.parseColor("#000000"));
                    pre_date.setTextColor(Color.parseColor("#000000"));
                    tv_time.setTextColor(Color.parseColor("#000000"));
                }
            }
        });
    }
}
