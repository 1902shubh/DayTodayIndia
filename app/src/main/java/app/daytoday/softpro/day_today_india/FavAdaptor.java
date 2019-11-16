package app.daytoday.softpro.day_today_india;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Shubham Pandey on 09-08-2019.
 */

public class FavAdaptor extends RecyclerView.Adapter<FavAdaptor.Exampleviewholder> {

    public List<news_data> mExamplelist;
    public Context mContext;

    public FavAdaptor(Context context, List<news_data> examplelist) {
        mContext = context;
        mExamplelist = examplelist;
    }

    @Override
    public Exampleviewholder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_news_list, viewGroup, false);
        Exampleviewholder exampleviewholder = new Exampleviewholder(view);
        return exampleviewholder;
    }

    @Override
    public void onBindViewHolder(Exampleviewholder exampleviewholder, int i) {
        final news_data currentitem = mExamplelist.get(i);
        exampleviewholder.tv_headline.setText(currentitem.getHeadline());
        exampleviewholder.tv_time.setText(currentitem.getTime());
        exampleviewholder.tv_date.setText(currentitem.getDate());
        Picasso.get().load(currentitem.getImgurl()).into(exampleviewholder.newsimage);

        exampleviewholder.itemclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, FavFullScreenActivity.class);
                i.putExtra("head", currentitem.getHeadline());
                i.putExtra("desc", currentitem.getDescription());
                i.putExtra("time", currentitem.getTime());
                i.putExtra("date", currentitem.getDate());
                i.putExtra("img", currentitem.getImgurl());
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mExamplelist.size();

    }

    public static class Exampleviewholder extends RecyclerView.ViewHolder {
        public ImageView newsimage;
        public TextView tv_headline;
        public TextView tv_date;
        public TextView tv_time;
        public LinearLayout itemclick;

        public Exampleviewholder(View itemView) {
            super(itemView);
            newsimage = itemView.findViewById(R.id.news_list_img);
            tv_headline = itemView.findViewById(R.id.news_head_tv);
            tv_date = itemView.findViewById(R.id.news_date_tv);
            tv_time = itemView.findViewById(R.id.news_time_tv);
            itemclick = itemView.findViewById(R.id.nextactivity);
        }
    }

}
