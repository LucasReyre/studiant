package com.studiant.com.presentation.ui.activities.particulier;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.studiant.com.R;
import com.studiant.com.domain.executor.impl.ThreadExecutor;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.presentation.presenters.converters.PresentationModelConverter;
import com.studiant.com.presentation.presenters.impl.ChoosePostulantPresenterImpl;

import com.studiant.com.presentation.presenters.interfaces.ChoosePostulantPresenter;
import com.studiant.com.presentation.presenters.model.Job;
import com.studiant.com.presentation.ui.activities.etudiant.DashboardEtudiantActivity;
import com.studiant.com.presentation.ui.components.adapters.FoldingCellRecyclerViewPostulantAdapter;
import com.studiant.com.storage.impl.JobRepositoryImpl;
import com.studiant.com.storage.impl.PostulantRepositoryImpl;
import com.studiant.com.storage.network.WSException;
import com.studiant.com.threading.MainThreadImpl;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.studiant.com.AndroidApplication.getContext;
import static com.studiant.com.storage.Constants.INTENT_JOB;
import static com.studiant.com.storage.Constants.INTENT_USER;
import static com.studiant.com.storage.Constants.STATUS_ETUDIANT;
import static com.studiant.com.storage.Constants.STATUS_PARTICULIER;

public class ListPostulantActivity extends Activity implements ChoosePostulantPresenter.View {


    @BindView(R.id.EtudiantRecyclerView)
    RecyclerView mRecyclerView;

    private ArrayList<User> postulantArrayList;
    private Job job;
    private User user;

    ChoosePostulantPresenter mPresenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_etudiant);
        ButterKnife.bind(this);

        mPresenter = new ChoosePostulantPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new JobRepositoryImpl()
        );

        progressDialog = new ProgressDialog(this);

        user = (User) getIntent().getSerializableExtra(INTENT_USER);
        //postulantArrayList = (ArrayList<User>) getIntent().getSerializableExtra(INTENT_LIST_JOB);

        job = PresentationModelConverter.convertToJobPresenterModel((com.studiant.com.domain.model.Job) getIntent().getSerializableExtra(INTENT_JOB));
        createListPostulant(job.getPostulants());


    }

    private void createListPostulant(final ArrayList<User> postulantArrayList) {
        // create custom adapter that holds elements and their state (we need hold a id's of unfolded elements for reusable elements)
        System.out.println("createListPostulant "+job.getStatut());
        System.out.println("createListPostulant "+job.getModePaiement());
        final FoldingCellRecyclerViewPostulantAdapter adapter = new FoldingCellRecyclerViewPostulantAdapter(this,postulantArrayList, job);

        for (int i = 0 ; i<postulantArrayList.size();i++){
            final int j = i;
            postulantArrayList.get(i).setRequestBtnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onChoosePostulantClick(postulantArrayList.get(j));
                }
            });
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);
    }

    private void onChoosePostulantClick(com.studiant.com.presentation.presenters.model.User user){
        Log.d("onChoosePostulantClick", " choose "+user.getFirstName());
        mPresenter.choosePostulant(PresentationModelConverter.convertToUserDomainModel(user), PresentationModelConverter.convertToJobDomainModel(job));
    }


    @Override
    public void onPostulantChoosed() {
        Intent intent = new Intent();
        intent = new Intent(getContext(), DashboardParticulierActivity.class);
        intent.putExtra(INTENT_USER, user);
        startActivity(intent);
    }

    @Override
    public void showProgress() {
        progressDialog.setMessage(getResources().getString(R.string.get_message));
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }

    @Override
    public void showError(WSException e) {

    }
}
