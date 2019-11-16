package app.daytoday.softpro.day_today_india;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdaptor extends RecyclerView.Adapter<MyAdaptor.ViewHolder> {
    private Context context;
    private List<Article> articles;

    public MyAdaptor(Context context, List<Article> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_news_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Article item = articles.get(position);
        holder.tv_headline.setText(item.getTitle());
        holder.tv_date.setText(item.getPublishedAt());
        Picasso.get().load(item.getUrlToImage()).into(holder.newsimage);

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView newsimage;
        public TextView tv_headline;
        public TextView tv_date;
        public TextView tv_time;
        public LinearLayout itemclick;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newsimage = itemView.findViewById(R.id.news_list_img);
            tv_headline = itemView.findViewById(R.id.news_head_tv);
            tv_date = itemView.findViewById(R.id.news_date_tv);
            tv_time = itemView.findViewById(R.id.news_time_tv);
            itemclick = itemView.findViewById(R.id.nextactivity);
        }
    }
}
