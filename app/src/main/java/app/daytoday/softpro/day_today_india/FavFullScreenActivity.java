package app.daytoday.softpro.day_today_india;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class FavFullScreenActivity extends AppCompatActivity {
    TextView tv_head, tv_desc;
    ImageView news_img, gmail, whatsapp, share;
    DatabaseReference newsref;
    LinearLayout layout, favfullcreenimage;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_full_screen);

        radioGroup = findViewById(R.id.fav_rgroup);
        layout = findViewById(R.id.fav_layout);
        tv_head = findViewById(R.id.fav_tv_head);
        tv_desc = findViewById(R.id.fav_tv_desc);
        news_img = findViewById(R.id.fav_img);
        gmail = findViewById(R.id.btn_fav_gmail);
        whatsapp = findViewById(R.id.btn_fav_whatsapp);
        share = findViewById(R.id.btn_fav_share);
        favfullcreenimage = findViewById(R.id.favfullscreenimage);

        newsref = FirebaseDatabase.getInstance().getReference().child("News Record").child("Favourite");
        tv_head.setText(getIntent().getStringExtra("head"));
        tv_desc.setText(getIntent().getStringExtra("desc"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tv_desc.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        }
        Picasso.get().load(getIntent().getStringExtra("img")).into(news_img);
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
                if (checkedId == R.id.fav_darkmode) {
                    layout.setBackgroundColor(Color.parseColor("#2c3335"));
                    tv_head.setTextColor(Color.parseColor("#ffffff"));
                    tv_desc.setTextColor(Color.parseColor("#ffffff"));
                    favfullcreenimage.setBackgroundColor(Color.parseColor("#2c3335"));
                } else if (checkedId == R.id.fav_lightmode) {
                    layout.setBackgroundColor(Color.parseColor("#ffffff"));
                    tv_head.setTextColor(Color.parseColor("#000000"));
                    tv_desc.setTextColor(Color.parseColor("#000000"));
                    favfullcreenimage.setBackgroundColor(Color.parseColor("#ffffff"));
                }
            }
        });
    }
}
