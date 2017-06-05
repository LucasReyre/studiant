package com.studiant.com.presentation.ui.activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.studiant.com.R;
import com.studiant.com.domain.executor.impl.ThreadExecutor;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.presentation.presenters.converters.PresentationModelConverter;
import com.studiant.com.presentation.presenters.impl.ChoosePostulantPresenterImpl;

import com.studiant.com.presentation.presenters.interfaces.ChoosePostulantPresenter;
import com.studiant.com.presentation.presenters.model.Job;
import com.studiant.com.presentation.ui.components.adapters.FoldingCellRecyclerViewPostulantAdapter;
import com.studiant.com.storage.impl.PostulantRepositoryImpl;
import com.studiant.com.threading.MainThreadImpl;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.studiant.com.storage.Constants.INTENT_JOB;
import static com.studiant.com.storage.Constants.INTENT_LIST_JOB;
import static com.studiant.com.storage.Constants.INTENT_LIST_USER;
import static com.studiant.com.storage.Constants.INTENT_USER;

public class ListPostulantActivity extends Activity implements ChoosePostulantPresenter.View {


    @BindView(R.id.EtudiantRecyclerView)
    RecyclerView mRecyclerView;

    private ArrayList<User> postulantArrayList;
    private Job job;
    private User user;

    ChoosePostulantPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_etudiant);
        ButterKnife.bind(this);

        mPresenter = new ChoosePostulantPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new PostulantRepositoryImpl()
        );

        user = (User) getIntent().getSerializableExtra(INTENT_USER);
        //postulantArrayList = (ArrayList<User>) getIntent().getSerializableExtra(INTENT_LIST_JOB);

        job = PresentationModelConverter.convertToJobPresenterModel((com.studiant.com.domain.model.Job) getIntent().getSerializableExtra(INTENT_JOB));

        createListPostulant(job.getPostulants());


    }

    private void createListPostulant(final ArrayList<User> postulantArrayList) {
        // create custom adapter that holds elements and their state (we need hold a id's of unfolded elements for reusable elements)
        final FoldingCellRecyclerViewPostulantAdapter adapter = new FoldingCellRecyclerViewPostulantAdapter(this,postulantArrayList);

        for (int i = 0 ; i<postulantArrayList.size();i++){
            final int j = i;
            postulantArrayList.get(i).setRequestBtnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onChoosePostulantClick(postulantArrayList.get(j));
                }
            });
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);
    }

    private void onChoosePostulantClick(com.studiant.com.presentation.presenters.model.User user){
        Log.d("onChoosePostulantClick", " choose "+user.getFirstName());
        mPresenter.choosePostulant(PresentationModelConverter.convertToUserDomainModel(user));
    }


    @Override
    public void onPostulantChoosed() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }
}
