package app.com.example.vansh.wdyw.utility;


import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

/**
 * Created by Vansh on 07/09/2016.
 */
public class DataFetch {
    public static void fetchImage(String url, Context context, ImageView imageView){

        String imgUrl = url;

        GlideUrl glideUrl = new GlideUrl(imgUrl , new LazyHeaders.Builder()
                .build());

        Glide.with(context).load(glideUrl)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }
}