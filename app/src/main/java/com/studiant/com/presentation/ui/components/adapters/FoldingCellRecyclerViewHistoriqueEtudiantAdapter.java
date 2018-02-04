package com.studiant.com.presentation.ui.components.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ramotion.foldingcell.FoldingCell;
import com.studiant.com.R;
import com.studiant.com.presentation.presenters.model.Job;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/**
 * Created by groupama on 18/05/2017.
 */

public class FoldingCellRecyclerViewHistoriqueEtudiantAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Job> contents;

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private View.OnClickListener defaultRequestBtnClickListener;

    public FoldingCellRecyclerViewHistoriqueEtudiantAdapter(ArrayList<Job> contents) {
        this.contents = contents;
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_historique_job_etudiant, parent, false));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder cellViewHolder = (ViewHolder) holder;
        cellViewHolder.foldingCell.fold(true);
        cellViewHolder.titleDescription.setText(contents.get(position).getCategorie());
        String prix = contents.get(position).getPrix()+" €";
        cellViewHolder.price.setText(prix);
        cellViewHolder.cityTitle.setText(contents.get(position).getCity());
        cellViewHolder.time.setText(contents.get(position).getHeure());
        cellViewHolder.date.setText(contents.get(position).getDate());
        //viewHolder.toAddress.setText(item.getToAddress());

        //Content
        cellViewHolder.name.setText(contents.get(position).getUtilisateur().getFirstName() + " " +contents.get(position).getUtilisateur().getLastName());
        cellViewHolder.priceContent.setText(contents.get(position).getPrix() + " €");
        //cellViewHolder.dateContent.setText(contents.get(position).getDate());
        cellViewHolder.adresseContent.setText(contents.get(position).getAdresse());
        cellViewHolder.timeHeure.setText(contents.get(position).getHeure());
        cellViewHolder.contentDate.setText(contents.get(position).getDate());
        cellViewHolder.description.setText(contents.get(position).getDescription());
        cellViewHolder.phoneContent.setText(contents.get(position).getUtilisateur().getTelephone());

        /*cellViewHolder.contentRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "OKOKOKOK" + position, Toast.LENGTH_SHORT).show();
            }
        });*/
        // set custom btn handler for list item from that item
        if (contents.get(position).getRequestBtnClickListener() != null) {
            cellViewHolder.contentRequestBtn.setOnClickListener(contents.get(position).getRequestBtnClickListener());
        } else {
            // (optionally) add "default" handler if no handler found in item
            cellViewHolder.contentRequestBtn.setOnClickListener(defaultRequestBtnClickListener);
        }

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

    public View.OnClickListener getDefaultRequestBtnClickListener() {
        return defaultRequestBtnClickListener;
    }

    public void setDefaultRequestBtnClickListener(View.OnClickListener defaultRequestBtnClickListener) {
        this.defaultRequestBtnClickListener = defaultRequestBtnClickListener;
    }

    // View lookup cache
    private static class ViewHolder extends RecyclerView.ViewHolder {
        TextView price;
        TextView titleDescription;
        TextView contentRequestBtn;
        //TextView pledgePrice;
        //TextView toAddress;
        TextView date;
        TextView time;
        TextView timeHeure;
        TextView cityTitle;
        TextView adresseContent;
        FoldingCell foldingCell;

        //Content Cell
        TextView name;
        TextView priceContent;
        TextView contentDate;
        TextView dateContent;
        TextView phoneContent;
        TextView description;

        public ViewHolder(View itemView){
            super(itemView);
            titleDescription = (TextView) itemView.findViewById(R.id.categorieTextView);
            price = (TextView) itemView.findViewById(R.id.tarifTextView);
            time = (TextView) itemView.findViewById(R.id.title_time_label);
            timeHeure = (TextView) itemView.findViewById(R.id.content_heure_job);
            cityTitle = itemView.findViewById(R.id.cityTextView);
            adresseContent = itemView.findViewById(R.id.adresseTextView);
            date = (TextView) itemView.findViewById(R.id.title_date_label);
            phoneContent = itemView.findViewById(R.id.phoneTextView);
            contentRequestBtn = (TextView) itemView.findViewById(R.id.content_request_btn);
            foldingCell = (FoldingCell) itemView.findViewById(R.id.job_folding_cell);
            //Content
            name = (TextView) itemView.findViewById(R.id.content_name_postulant);
            priceContent = (TextView) itemView.findViewById(R.id.content_price_job);
            contentDate = (TextView) itemView.findViewById(R.id.content_date);
            //dateContent = (TextView) itemView.findViewById(R.id.content_price_job);
            description = (TextView) itemView.findViewById(R.id.content_description_postulant);

        }
    }
}
