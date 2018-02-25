package com.studiant.com.presentation.ui.components.adapters;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class FoldingCellRecyclerViewEtudiantAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Job> contents;

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private View.OnClickListener defaultRequestBtnClickListener;

    public FoldingCellRecyclerViewEtudiantAdapter(ArrayList<Job> contents) {
        this.contents = contents;
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_job_etudiant, parent, false));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder cellViewHolder = (ViewHolder) holder;
        cellViewHolder.foldingCell.fold(true);
//        cellViewHolder.price.setText(prix);
        String prix = contents.get(position).getPrix()+" €";
        cellViewHolder.time.setText(contents.get(position).getHeure());
        cellViewHolder.date.setText(contents.get(position).getDate());
        //viewHolder.toAddress.setText(item.getToAddress());
        cellViewHolder.cityTextView.setText(contents.get(position).getCity());
        cellViewHolder.cityContentTextView.setText(contents.get(position).getCity());

        Categorie categorie = new Categorie(contents.get(position).getCategorie());
        cellViewHolder.categorieTextView.setText(contents.get(position).getCategorie());
        cellViewHolder.categorieImageView.setImageResource(categorie.getImageRessource());

        //Content
        cellViewHolder.name.setText(contents.get(position).getUtilisateur().getFirstName());

        cellViewHolder.dateContent.setText(contents.get(position).getDate());
        cellViewHolder.timeContent.setText(contents.get(position).getHeure());
        cellViewHolder.description.setText(contents.get(position).getDescription());
        switch (contents.get(position).getModePaiement()){
            case "CB":
                cellViewHolder.modePaiementImage.setImageResource(R.drawable.credit_card);
                cellViewHolder.modePaiementImageTitle.setImageResource(R.drawable.credit_card);
                float commission = Float.parseFloat(contents.get(position).getPrix())*15/100;
                float finalPrice = Float.parseFloat(contents.get(position).getPrix()) - commission;
                prix = String.valueOf(finalPrice) + " €";
                break;
            case "ESPECES":
                cellViewHolder.modePaiementImage.setImageResource(R.drawable.change);
                cellViewHolder.modePaiementImageTitle.setImageResource(R.drawable.change);
                break;
            case "CESU":
                cellViewHolder.modePaiementImage.setImageResource(R.drawable.check);
                cellViewHolder.modePaiementImageTitle.setImageResource(R.drawable.check);
                break;
        }

        cellViewHolder.tarifTextView.setText(prix);
        cellViewHolder.priceContent.setText(prix);
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
        TextView categorieTextView;
        TextView contentRequestBtn;
        TextView cityTextView;
        TextView cityContentTextView;
        ImageView categorieImageView;
        TextView tarifTextView;
        //TextView pledgePrice;
        //TextView toAddress;
        TextView date;
        TextView time;
        FoldingCell foldingCell;
        ImageView modePaiementImage;
        ImageView modePaiementImageTitle;

        //Content Cell
        TextView name;
        TextView priceContent;
        TextView timeContent;
        TextView dateContent;
        TextView description;

        public ViewHolder(View itemView){
            super(itemView);
            categorieTextView = (TextView) itemView.findViewById(R.id.categorieTextView);
            categorieImageView = (ImageView) itemView.findViewById(R.id.categorieHistoriqueJobImageView);
            cityTextView = (TextView) itemView.findViewById(R.id.cityTextView);
            cityContentTextView = (TextView) itemView.findViewById(R.id.content_city);
            tarifTextView = (TextView) itemView.findViewById(R.id.tarifTextView);
            price = (TextView) itemView.findViewById(R.id.title_price);
            time = (TextView) itemView.findViewById(R.id.title_time_label);
            date = (TextView) itemView.findViewById(R.id.title_date_label);
            //viewHolder.toAddress = (TextView) cell.findViewById(R.id.title_to_address);
            //viewHolder.pledgePrice = (TextView) cell.findViewById(R.id.title_pledge);
            contentRequestBtn = (TextView) itemView.findViewById(R.id.content_request_btn);
            foldingCell = (FoldingCell) itemView.findViewById(R.id.job_folding_cell);
            modePaiementImage = itemView.findViewById(R.id.imageViewModePaiementt);
            modePaiementImageTitle = itemView.findViewById(R.id.imageViewModePaiementTitle);
            //Content
            name = (TextView) itemView.findViewById(R.id.content_name_postulant);
            timeContent = (TextView) itemView.findViewById(R.id.content_heure_job);
            priceContent = (TextView) itemView.findViewById(R.id.content_price_job);
            dateContent = (TextView) itemView.findViewById(R.id.content_date);
            description = (TextView) itemView.findViewById(R.id.content_description_postulant);

        }
    }
}
