package com.studiant.com.presentation.ui.components.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ramotion.foldingcell.FoldingCell;
import com.studiant.com.R;
import com.studiant.com.domain.model.Categorie;
import com.studiant.com.presentation.presenters.model.Job;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/**
 * Created by groupama on 18/05/2017.
 */

public class FoldingCellRecyclerViewHistoriqueEtudiantAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Job> contents;

    Context context;
    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private View.OnClickListener defaultRequestBtnClickListener;

    public FoldingCellRecyclerViewHistoriqueEtudiantAdapter(ArrayList<Job> contents, Context context) {
        this.contents = contents;
        this.context = context;
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
        cellViewHolder.cityTitle.setText(contents.get(position).getCity());
        cellViewHolder.time.setText(contents.get(position).getHeure());
        cellViewHolder.date.setText(contents.get(position).getDate());
        //viewHolder.toAddress.setText(item.getToAddress());

        //Content

        if (contents.get(position).getUtilisateur() != null){
            cellViewHolder.name.setText(contents.get(position).getUtilisateur().getFirstName() + " " +contents.get(position).getUtilisateur().getLastName());
            cellViewHolder.phoneContent.setText(contents.get(position).getUtilisateur().getTelephone());
        }


        //cellViewHolder.dateContent.setText(contents.get(position).getDate());
        cellViewHolder.adresseContent.setText(contents.get(position).getAdresse());
        cellViewHolder.timeHeure.setText(contents.get(position).getHeure());
        cellViewHolder.contentDate.setText(contents.get(position).getDate());
        cellViewHolder.description.setText(contents.get(position).getDescription());

        Categorie categorie = new Categorie(contents.get(position).getCategorie());
        cellViewHolder.categorieImageView.setImageResource(categorie.getImageRessource());

        switch (contents.get(position).getModePaiement()){
            case "CB":
                cellViewHolder.modePaiementImageView.setImageResource(R.drawable.credit_card);
                float commission = Float.parseFloat(contents.get(position).getPrix())*15/100;
                float finalPrice = Float.parseFloat(contents.get(position).getPrix()) - commission;
                prix = String.valueOf(finalPrice) + " €";
                break;
            case "ESPECES":
                cellViewHolder.modePaiementImageView.setImageResource(R.drawable.change);
                break;
            case "CESU":
                cellViewHolder.modePaiementImageView.setImageResource(R.drawable.check);
                break;
        }
        cellViewHolder.price.setText(prix);
        cellViewHolder.priceContent.setText(prix);
        System.out.println("status : "+contents.get(position).getStatut() +  " "+contents.get(position).getCategorie() + " " + contents.get(position).getModePaiement());

        if (contents.get(position).getStatut().equals("2")){
            cellViewHolder.leftBackgroundRelativeLayout.setBackgroundColor(ContextCompat.getColor(context,R.color.warm_grey));
            cellViewHolder.contentRequestBtn.setBackgroundColor(ContextCompat.getColor(context,R.color.warm_grey));
        }else{
            cellViewHolder.leftBackgroundRelativeLayout.setBackground(context.getDrawable(R.drawable.background_job));
            cellViewHolder.contentRequestBtn.setVisibility(View.VISIBLE);
            cellViewHolder.contentRequestBtn.setBackgroundColor(ContextCompat.getColor(context,R.color.customRed));
        }

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
        ImageView categorieImageView;
        ImageView modePaiementImageView;
        FoldingCell foldingCell;
        RelativeLayout leftBackgroundRelativeLayout;

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
            leftBackgroundRelativeLayout = itemView.findViewById(R.id.leftBackground);
            date = (TextView) itemView.findViewById(R.id.title_date_label);
            phoneContent = itemView.findViewById(R.id.phoneTextView);
            contentRequestBtn = (TextView) itemView.findViewById(R.id.content_request_btn);
            categorieImageView = itemView.findViewById(R.id.categorieHistoriqueJobImageView);
            foldingCell = (FoldingCell) itemView.findViewById(R.id.job_folding_cell);
            modePaiementImageView =  itemView.findViewById(R.id.imageViewModePaiementTitle);
            //Content
            name = (TextView) itemView.findViewById(R.id.content_name_postulant);
            priceContent = (TextView) itemView.findViewById(R.id.content_price_job);
            contentDate = (TextView) itemView.findViewById(R.id.content_date);
            //dateContent = (TextView) itemView.findViewById(R.id.content_price_job);
            description = (TextView) itemView.findViewById(R.id.content_description_postulant);

        }
    }
}
