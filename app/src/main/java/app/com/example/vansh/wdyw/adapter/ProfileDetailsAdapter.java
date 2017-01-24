package app.com.example.vansh.wdyw.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import app.com.example.vansh.wdyw.R;
import app.com.example.vansh.wdyw.model.BorrowerLoanData;
import app.com.example.vansh.wdyw.model.LloanIdRequest;
import app.com.example.vansh.wdyw.rest.ApiClient;
import app.com.example.vansh.wdyw.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileDetailsAdapter extends RecyclerView.Adapter<ProfileDetailsAdapter.MovieViewHolder> {

    private List<BorrowerLoanData> stock;
    private int rowLayout;
    private Context context;


    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout stockLayout;
        TextView productid;
        TextView costprice;
        TextView expected;
        TextView amount;
        Button lend;
        Button details;




        public MovieViewHolder(View v) {
            super(v);
            stockLayout = (LinearLayout) v.findViewById(R.id.stock_layout);
            productid = (TextView) v.findViewById(R.id.loantype);
            costprice = (TextView) v.findViewById(R.id.city);
            expected = (TextView) v.findViewById(R.id.expected);
            amount = (TextView) v.findViewById(R.id.rating);
            lend = (Button) v.findViewById(R.id.lend);
            details = (Button) v.findViewById(R.id.details);
            details.setVisibility(v.GONE);
        }
    }

    public ProfileDetailsAdapter(List<BorrowerLoanData> stock, int rowLayout, Context context) {
        this.stock = stock;
        this.rowLayout = rowLayout;
        this.context = context;

    }

    @Override
    public ProfileDetailsAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                                    int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {
        holder.productid.setText(stock.get(position).getLoanType());
        holder.amount.setText("Amt."+stock.get(position).getLoanAmt().toString());
      //  holder.costprice.setText(stock.get(position).getCustomer().getName().toString());
        holder.costprice.setText(stock.get(position).getCity());
        holder.expected.setText(stock.get(position).getExpectedInterest().toString()+"% exp.");

        holder.lend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertbox = new AlertDialog.Builder(view.getRootView().getContext());
                alertbox.setMessage("Are You Sure You wanna contact this borrower?");
                alertbox.setTitle("Confirmation");
                alertbox.setIcon(R.drawable.logo1);

                alertbox.setNeutralButton("Continue",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0,
                                                int arg1) {


                                final LloanIdRequest lloanIdRequest = new LloanIdRequest();
                                final ApiInterface apiInterface = ApiClient.getClient(context).create(ApiInterface.class);

                                lloanIdRequest.setLender(stock.get(position).getId().toString());


                                Call<LloanIdRequest> call = apiInterface.id(lloanIdRequest);

                                call.enqueue(new Callback<LloanIdRequest>() {
                                    @Override
                                    public void onResponse(Call<LloanIdRequest> call, Response<LloanIdRequest> response) {
                                    holder.lend.setVisibility(View.GONE);
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
                alertbox.show();

                    }
                });

            }


            @Override
            public int getItemCount() {
                return stock.size();
            }
        }