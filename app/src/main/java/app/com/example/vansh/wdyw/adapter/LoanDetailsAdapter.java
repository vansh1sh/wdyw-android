package app.com.example.vansh.wdyw.adapter;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import app.com.example.vansh.wdyw.R;
import app.com.example.vansh.wdyw.activity.BorrowerLoanPostActivity;
import app.com.example.vansh.wdyw.activity.BorrowerMainActivity;
import app.com.example.vansh.wdyw.model.BorrowerLoanData;
import app.com.example.vansh.wdyw.model.Customer;
import app.com.example.vansh.wdyw.model.LenderDetails;
import app.com.example.vansh.wdyw.model.LloanIdRequest;
import app.com.example.vansh.wdyw.model.LoanPostRequest;
import app.com.example.vansh.wdyw.rest.ApiClient;
import app.com.example.vansh.wdyw.rest.ApiInterface;
import app.com.example.vansh.wdyw.utility.DialogUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoanDetailsAdapter extends RecyclerView.Adapter<LoanDetailsAdapter.MovieViewHolder> {

    private List<BorrowerLoanData> stock;
    private int rowLayout;
    private Context context;


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
            details = (Button) v.findViewById(R.id.details);
            details.setVisibility(v.GONE);
        }
    }

    public LoanDetailsAdapter(List<BorrowerLoanData> stock, int rowLayout, Context context) {
        this.stock = stock;
        this.rowLayout = rowLayout;
        this.context = context;

    }

    @Override
    public LoanDetailsAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {
        holder.productid.setText(stock.get(position).getCustomer().getName());

        holder.type.setText(stock.get(position).getLoanType().toString());
        holder.amount.setText("₹"+stock.get(position).getLoanAmt().toString());
      //  holder.costprice.setText(stock.get(position).getCustomer().getName().toString());
        holder.costprice.setText(stock.get(position).getCity());
        holder.interest.setText(stock.get(position).getExpectedInterest()+"% exp.");
        holder.expected.setText(stock.get(position).getReason().toString());

        //holder.duration.setText(stock.get(position).get.toString());
        holder.lend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertbox = new AlertDialog.Builder(view.getRootView().getContext());
                alertbox.setMessage("Are You Sure You Want To Contact This Borrower?");
                alertbox.setTitle("Confirmation");
                alertbox.setIcon(R.drawable.logo1);

                alertbox.setNeutralButton("Continue",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0,
                                                int arg1) {

                                final LloanIdRequest lloanIdRequest = new LloanIdRequest();
                                final ApiInterface apiInterface = ApiClient.getClient(context).create(ApiInterface.class);
                                lloanIdRequest.setCustomer(stock.get(position).getCustomer().getId().toString());
                                lloanIdRequest.setLoan(stock.get(position).getId().toString());

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