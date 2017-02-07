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
import app.com.example.vansh.wdyw.model.LenderDetails;
import app.com.example.vansh.wdyw.model.LloanIdRequest;
import app.com.example.vansh.wdyw.rest.ApiClient;
import app.com.example.vansh.wdyw.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LenderDetailsAdapter extends RecyclerView.Adapter<LenderDetailsAdapter.MovieViewHolder> {

    private List<LenderDetails> stock;
    private int rowLayout;
    private Context context;
    String idd;


    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout stockLayout;
        TextView productid;
        TextView stock;
        TextView costprice;
        TextView rating;
        Button makeoffer, details;


        public MovieViewHolder(View v) {
            super(v);
            stockLayout = (LinearLayout) v.findViewById(R.id.stock_layout);
            productid = (TextView) v.findViewById(R.id.productid);
            stock = (TextView) v.findViewById(R.id.stock);
            costprice = (TextView) v.findViewById(R.id.costprice);
            rating = (TextView) v.findViewById(R.id.rating);
            makeoffer = (Button) v.findViewById(R.id.makeoffer);
            details = (Button) v.findViewById(R.id.details);
            details.setVisibility(v.GONE);
        }
    }

    public LenderDetailsAdapter(List<LenderDetails> stock, int rowLayout, Context context) {
        this.stock = stock;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public LenderDetailsAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                                   int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {
        holder.productid.setText(stock.get(position).getCity());
        holder.stock.setText(stock.get(position).getName());
        holder.rating.setText(stock.get(position).getPhone().toString());
        holder.makeoffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LloanIdRequest lloanIdRequest = new LloanIdRequest();
                final ApiInterface apiInterface = ApiClient.getClient(context).create(ApiInterface.class);
                lloanIdRequest.setLender(stock.get(position).getId().toString());

                Call<LloanIdRequest> call = apiInterface.id(lloanIdRequest);

                call.enqueue(new Callback<LloanIdRequest>() {
                    @Override
                    public void onResponse(Call<LloanIdRequest> call, Response<LloanIdRequest> response) {
                        holder.makeoffer.setVisibility(View.GONE);
                        holder.details.setVisibility(View.VISIBLE);
                        holder.details.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<LloanIdRequest> call, Throwable t) {


                    }
                });
            }
        });

    }


    @Override
    public int getItemCount() {
        return stock.size();
    }
}