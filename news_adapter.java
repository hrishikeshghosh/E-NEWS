package com.example.news_app;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class news_adapter extends RecyclerView.Adapter<news_adapter.ViewHolder> {

    private List<Article>articles;
    private Context context;
    private View.OnContextClickListener contextClickListener;

    public news_adapter(List<Article> articles,Context context) {
        this.articles = articles;
        this.context=context;
    }

    @NonNull
    @Override
    public news_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.items_alyout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final news_adapter.ViewHolder holder, int position) {

        final Article article=articles.get(position);

        String title=article.getTitle();
        String src=article.getSource().getName();
        String date=article.getPublishedAt();
        String imageUrl= article.getUrlToImage();
        String webUrl=article.getUrl();
        String desc=article.getDescription();
        holder.setData(title,src,date,imageUrl,webUrl,desc);




    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_title,tv_source,tv_date;
        private ImageView imageView;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title=itemView.findViewById(R.id.tv_id);
            tv_source=itemView.findViewById(R.id.tv_source);
            tv_date=itemView.findViewById(R.id.tv_date);
            imageView=itemView.findViewById(R.id.news_image);
            cardView=itemView.findViewById(R.id.row_parent);

        }

        private void setData(final String article_title, final String article_source, final String publised_date, final String url, final String webURL, final String article_desc){

            tv_title.setText(article_title);
            tv_source.setText(article_source);
            tv_date.setText("\u2022"+dateTime(publised_date));

            Glide.with(itemView.getContext()).load(url).into(imageView);


            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent webIntent=new Intent(context,WebActivity.class);
                    webIntent.putExtra("title",article_title);
                    webIntent.putExtra("source",article_source);
                    webIntent.putExtra("time",dateTime(publised_date));
                    webIntent.putExtra("imageUrl",url);
                    webIntent.putExtra("url",webURL);
                    webIntent.putExtra("desc",article_desc);
                    context.startActivity(webIntent);
                }
            });


        }

    }
    public String dateTime(String t){

        PrettyTime prettyTime=new PrettyTime(new Locale(getCountry()));
        String time =null;

        try {
            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:",Locale.ENGLISH);
            Date date=dateFormat.parse(t);
            time=prettyTime.format(date);
        }catch (Exception e){
            e.printStackTrace();
        }



        return time;
    }

    public  String getCountry(){

        Locale locale=Locale.getDefault();
        String country=locale.getCountry();
        return  country.toLowerCase();
    }


}

