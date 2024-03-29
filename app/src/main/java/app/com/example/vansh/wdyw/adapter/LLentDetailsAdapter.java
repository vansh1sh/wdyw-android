package app.com.example.vansh.wdyw.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import app.com.example.vansh.wdyw.R;
import app.com.example.vansh.wdyw.activity.LFindProfileActivity;
import app.com.example.vansh.wdyw.model.LlentDatum;

public class LLentDetailsAdapter extends RecyclerView.Adapter<LLentDetailsAdapter.MovieViewHolder> {

    private List<LlentDatum> stock;
    private int rowLayout;
    private Context context;

    String idd;
    int lastPosition=-1;


    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout stockLayout;
        TextView productid;
        TextView costprice;
        TextView expected;
        TextView amount;
        TextView type;
        TextView interest;
        Button lend;
        Button details;
        CardView cardView;



        public MovieViewHolder(View v) {
            super(v);
            stockLayout = (LinearLayout) v.findViewById(R.id.stock_layout);
            productid = (TextView) v.findViewById(R.id.name);
            costprice = (TextView) v.findViewById(R.id.city);
            type = (TextView) v.findViewById(R.id.loantype);
            interest = (TextView) v.findViewById(R.id.interestrate);
            expected = (TextView) v.findViewById(R.id.expected);
            amount = (TextView) v.findViewById(R.id.rating);
            lend = (Button) v.findViewById(R.id.lend);
            cardView = (CardView)v.findViewById(R.id.card_view);
            //details = (Button) v.findViewById(R.id.details);
            //details.setVisibility(v.GONE);
        }
    }

    public LLentDetailsAdapter(List<LlentDatum> stock, int rowLayout, Context context) {
        this.stock = stock;
        this.rowLayout = rowLayout;
        this.context = context;

    }

    @Override
    public LLentDetailsAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                                  int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {
        holder.productid.setText(stock.get(position).getCustomer().getName());

        holder.type.setText(stock.get(position).getLoan().getLoanType().toString());
        holder.amount.setText("₹"+stock.get(position).getLoan().getLoanAmt().toString());
      //  holder.costprice.setText(stock.get(position).getCustomer().getName().toString());
        holder.costprice.setText(stock.get(position).getLoan().getCity());
        holder.interest.setText(stock.get(position).getLoan().getExpectedInterest()+"% exp.");
        holder.expected.setText(stock.get(position).getLoan().getReason().toString());


        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_top);
        // holder.itemView.animate().translationYBy(-  holder.itemView.getHeight()/100).start();
        holder.cardView.startAnimation(animation);
        lastPosition = position;


                            holder.lend.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                idd=stock.get(position).getCustomer().getId();
                                                Intent intent = new Intent(context, LFindProfileActivity.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                intent.putExtra("id",idd);
                                                context.startActivity(intent);

                                            }
                                        });
                                    }




            @Override
            public int getItemCount() {
                return stock.size();
            }
        }