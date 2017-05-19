package com.studiant.com.presentation.ui.components;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ramotion.foldingcell.FoldingCell;
import com.studiant.com.R;

import java.util.HashSet;
import java.util.List;


/**
 * Created by groupama on 18/05/2017.
 */

public class FoldingCellRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Item> contents;

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    private HashSet<Integer> unfoldedIndexes = new HashSet<>();

    public FoldingCellRecyclerViewAdapter(List<Item> contents) {
        this.contents = contents;
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell, parent, false));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder cellViewHolder = (ViewHolder) holder;
        cellViewHolder.foldingCell.fold(true);
        cellViewHolder.price.setText(contents.get(position).getPrice());
        cellViewHolder.time.setText(contents.get(position).getTime());
        cellViewHolder.date.setText(contents.get(position).getDate());
        cellViewHolder.fromAddress.setText(contents.get(position).getFromAddress());
        //viewHolder.toAddress.setText(item.getToAddress());
        cellViewHolder.requestsCount.setText(String.valueOf(contents.get(position).getRequestsCount()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // toggle clicked cell state
                ((FoldingCell) v).toggle(false);
                // register in adapter that state for selected cell is toggled
                registerToggle(position);
            }
        });
    }

    // simple methods for register cell state changes
    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }

    // View lookup cache
    private static class ViewHolder extends RecyclerView.ViewHolder {
        TextView price;
        TextView contentRequestBtn;
        //TextView pledgePrice;
        TextView fromAddress;
        //TextView toAddress;
        TextView requestsCount;
        TextView date;
        TextView time;
        FoldingCell foldingCell;

        public ViewHolder(View itemView){
            super(itemView);
            price = (TextView) itemView.findViewById(R.id.title_price);
            time = (TextView) itemView.findViewById(R.id.title_time_label);
            date = (TextView) itemView.findViewById(R.id.title_date_label);
            fromAddress = (TextView) itemView.findViewById(R.id.title_from_address);
            //viewHolder.toAddress = (TextView) cell.findViewById(R.id.title_to_address);
            requestsCount = (TextView) itemView.findViewById(R.id.title_requests_count);
            //viewHolder.pledgePrice = (TextView) cell.findViewById(R.id.title_pledge);
            contentRequestBtn = (TextView) itemView.findViewById(R.id.content_request_btn);
            foldingCell = (FoldingCell) itemView.findViewById(R.id.job_folding_cell);

        }
    }
}
