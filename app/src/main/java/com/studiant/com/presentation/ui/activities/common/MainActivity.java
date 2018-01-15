package com.studiant.com.presentation.ui.activities.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.studiant.com.R;
import com.studiant.com.presentation.ui.fragments.common.ConnexionFragment;
import com.studiant.com.presentation.ui.fragments.common.LoginFragment;
import com.studiant.com.presentation.ui.fragments.common.MainFragment;
import com.studiant.com.presentation.ui.fragments.etudiant.ProfilEtudiant2Fragment;
import com.studiant.com.presentation.ui.fragments.particulier.AddJobFragment;
import com.studiant.com.presentation.ui.fragments.particulier.CategoriesFragment;
import com.studiant.com.presentation.ui.fragments.particulier.InscriptionParticulierFragment;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInteractionListener,
        CategoriesFragment.OnFragmentInteractionListener, ConnexionFragment.OnFragmentInteractionListener,
        InscriptionParticulierFragment.OnFragmentInteractionListener, ProfilEtudiant2Fragment.OnFragmentInteractionListener,
        AddJobFragment.OnFragmentInteractionListener, LoginFragment.OnFragmentInteractionListener{


    /*@BindView(R.id.btn_particulier) Button btn_particulier;
    @BindView(R.id.btn_etudiant) Button btn_etudiant;*/
    Fragment currentFragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        transitionFragment(MainFragment.newInstance(), R.anim.fade_in, R.anim.slide_left_out, R.anim.slide_left_in, R.anim.slide_left_out);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void transitionFragment(Fragment destinationFragment, int in, int out, int popEnter, int PopExit){
        if(destinationFragment != null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            currentFragment = destinationFragment;
            ft.setCustomAnimations(in, out, popEnter, PopExit);
            ft.addToBackStack(null);
            ft.replace(android.R.id.content,destinationFragment, destinationFragment.getClass().getName()).commit();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(android.R.id.content);
        fragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {

        Fragment myFragment = getSupportFragmentManager().findFragmentByTag(MainFragment.class.getName());
        if (myFragment != null && myFragment.isVisible()) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else {
            super.onBackPressed();
        }

    }

    /*@OnClick(R.id.btn_particulier)
    void navigateToParticulierForm() {
        Intent intentToLaunch = new Intent(this, ChooseActivity.class);
        intentToLaunch.putExtra(STATUS_USER, STATUS_PARTICULIER);
        this.startActivity(intentToLaunch);
    }

    @OnClick(R.id.btn_etudiant)
    void navigateToConnexionActivity() {
        Intent intentToLaunch = new Intent(this, ConnexionActivity.class);
        intentToLaunch.putExtra(STATUS_USER, STATUS_ETUDIANT);
        this.startActivity(intentToLaunch);
    }*/

}
