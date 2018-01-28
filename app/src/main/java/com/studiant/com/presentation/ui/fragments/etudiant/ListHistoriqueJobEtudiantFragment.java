package com.studiant.com.presentation.ui.fragments.etudiant;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.studiant.com.R;
import com.studiant.com.domain.executor.impl.ThreadExecutor;
import com.studiant.com.presentation.presenters.impl.DashboardEtudiantPresenterImpl;
import com.studiant.com.presentation.presenters.impl.HistoriqueJobEtudiantPresenterImpl;
import com.studiant.com.presentation.presenters.interfaces.DashboardEtudiantPresenter;
import com.studiant.com.presentation.presenters.interfaces.HistoriqueJobEtudiantPresenter;
import com.studiant.com.presentation.presenters.model.Job;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.presentation.ui.activities.etudiant.DashboardEtudiantActivity;
import com.studiant.com.presentation.ui.components.adapters.FoldingCellRecyclerViewEtudiantAdapter;
import com.studiant.com.presentation.ui.components.adapters.FoldingCellRecyclerViewHistoriqueEtudiantAdapter;
import com.studiant.com.storage.impl.GCMMessageRepositoryImpl;
import com.studiant.com.storage.impl.JobRepositoryImpl;
import com.studiant.com.storage.impl.PostulantRepositoryImpl;
import com.studiant.com.storage.network.WSException;
import com.studiant.com.threading.MainThreadImpl;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.facebook.FacebookSdk.getApplicationContext;
import static com.studiant.com.storage.Constants.HTTP_CODE_500;
import static com.studiant.com.storage.Constants.INTENT_USER;

public class ListHistoriqueJobEtudiantFragment extends Fragment implements HistoriqueJobEtudiantPresenter.View{

    private static final boolean GRID_LAYOUT = false;
    @BindView(R.id.mainListView) RecyclerView mRecyclerView;


    private ProgressDialog progressDialog;
    User user;

    private HistoriqueJobEtudiantPresenter mPresenter;

    public ListHistoriqueJobEtudiantFragment() {
        // Required empty public constructor
    }

    public static ListHistoriqueJobEtudiantFragment newInstance(User user) {
        ListHistoriqueJobEtudiantFragment fragment = new ListHistoriqueJobEtudiantFragment();
        Bundle args = new Bundle();
        args.putSerializable(INTENT_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = (User) getArguments().getSerializable(INTENT_USER);

            mPresenter = new HistoriqueJobEtudiantPresenterImpl(
                    ThreadExecutor.getInstance(),
                    MainThreadImpl.getInstance(),
                    this,
                    new JobRepositoryImpl()
            );
        }
        progressDialog = new ProgressDialog(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      return inflater.inflate(R.layout.fragment_list_historique_job_etudiant, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mPresenter.getHistoriqueJobs(user);

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
        if (e.getHttpCode() == HTTP_CODE_500)
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_already_postule), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onJobsRetrieve(final ArrayList<Job> jobArrayList) {
        // prepare elements to display
        //final ArrayList<Item> items = Item.getTestingList();

        // create custom adapter that holds elements and their state (we need hold a id's of unfolded elements for reusable elements)
        final FoldingCellRecyclerViewHistoriqueEtudiantAdapter adapter = (new FoldingCellRecyclerViewHistoriqueEtudiantAdapter(jobArrayList));

        for (int i = 0 ; i<jobArrayList.size();i++){
            final int j = i;
            jobArrayList.get(i).setRequestBtnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPostulerClick(jobArrayList.get(j));
                }
            });
        }

        //setup materialviewpager

        if (GRID_LAYOUT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        mRecyclerView.setHasFixedSize(true);

        //Use this now
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mRecyclerView.setAdapter(adapter);
    }

    public void onPostulerClick(Job job){
        //Toast.makeText(getApplicationContext(), "CUSTOM HANDLER FOR "+job.getDescription(), Toast.LENGTH_SHORT).show();
        //mPresenter.insertPostulant(job, user);
        System.out.println("statut : "+job.getStatut());
        if (!job.getStatut().equals("2")){
            ((DashboardEtudiantActivity)getActivity()).displaySetStudiantCode(job);
        }else{
            Toast.makeText(getActivity(), "Ce job à déja été validé", Toast.LENGTH_LONG).show();
        }

    }
}
