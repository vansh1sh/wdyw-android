package app.com.example.vansh.wdyw.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import app.com.example.vansh.wdyw.R;
import app.com.example.vansh.wdyw.model.BorrowerLoanData;
import app.com.example.vansh.wdyw.model.LenderDetails;
import app.com.example.vansh.wdyw.model.LoanPostRequest;

public class LoanDetailsAdapter extends RecyclerView.Adapter<LoanDetailsAdapter.MovieViewHolder> {

    private List<BorrowerLoanData> stock;
    private int rowLayout;
    private Context context;


    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout stockLayout;
        TextView productid;
        TextView stock;
        TextView costprice;
        TextView rating;


        public MovieViewHolder(View v) {
            super(v);
            stockLayout = (LinearLayout) v.findViewById(R.id.stock_layout);
            productid = (TextView) v.findViewById(R.id.productid);
            stock = (TextView) v.findViewById(R.id.stock);
            costprice = (TextView) v.findViewById(R.id.costprice);
            rating = (TextView) v.findViewById(R.id.rating);
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
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        holder.productid.setText(stock.get(position).getCity());
        holder.stock.setText(stock.get(position).getLoanAmt());



    }


    @Override
    public int getItemCount() {
        return stock.size();
    }
}