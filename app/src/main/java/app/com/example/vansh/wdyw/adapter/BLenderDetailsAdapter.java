package app.com.example.vansh.wdyw.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import app.com.example.vansh.wdyw.R;
import app.com.example.vansh.wdyw.activity.BFindProfileActivity;
import app.com.example.vansh.wdyw.model.LlentDatum;

public class BLenderDetailsAdapter extends RecyclerView.Adapter<BLenderDetailsAdapter.MovieViewHolder> {

    private List<LlentDatum> stock;
    private int rowLayout;
    private Context context;

    String idd;


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
            //details = (Button) v.findViewById(R.id.details);
            //details.setVisibility(v.GONE);
        }
    }

    public BLenderDetailsAdapter(List<LlentDatum> stock, int rowLayout, Context context) {
        this.stock = stock;
        this.rowLayout = rowLayout;
        this.context = context;

    }

    @Override
    public BLenderDetailsAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                                    int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {
        holder.productid.setText(stock.get(position).getLender().getName());

        holder.amount.setText(stock.get(position).getLender().getPhone().toString());
      //  holder.costprice.setText(stock.get(position).getCustomer().getName().toString());
        holder.costprice.setText(stock.get(position).getLender().getAddress());
        holder.interest.setText(stock.get(position).getLender().getCity());
        holder.expected.setText(stock.get(position).getLender().getEmail());



                            holder.lend.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                idd=stock.get(position).getLender().getId();
                                                Intent intent = new Intent(context, BFindProfileActivity.class);
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