package app.com.example.vansh.wdyw.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import app.com.example.vansh.wdyw.R;
import app.com.example.vansh.wdyw.model.Radio;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mertsimsek on 31/08/15.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RadioViewHolder> {

    private Context context;

    private List<Radio> radioList;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
        radioList = new ArrayList<>();
    }

    public void setRadioList(List<Radio> radioList){
        this.radioList = radioList;
        notifyDataSetChanged();
    }

    @Override
    public RadioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_card, parent, false);
        RadioViewHolder viewHolder = new RadioViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RadioViewHolder holder, final int position) {

        Radio radio = radioList.get(position);
        holder.textViewRadioName.setText(radio.getRadioName());
        holder.textViewRadioDial.setText("(" + radio.getRadioDial() + ")");
        Picasso.with(context).load(radio.getRadioArt()).into(holder.imageViewRadioLogo);
        holder.textViewRadioTags.setText("VERIFIED");
    }

    @Override
    public int getItemCount() {
        return radioList.size();
    }

    public class RadioViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.textview_radio_name)
        TextView textViewRadioName;

        @Bind(R.id.textview_radio_dial)
        TextView textViewRadioDial;

        @Bind(R.id.textview_tags)
        TextView textViewRadioTags;

        @Bind(R.id.imageview_radio_logo)
        ImageView imageViewRadioLogo;


        public RadioViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }

}
