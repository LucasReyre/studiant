package com.studiant.com.presentation.ui.fragments.etudiant;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.squareup.picasso.Picasso;
import com.studiant.com.R;
import com.studiant.com.Utils.SecurityUtils;
import com.studiant.com.domain.executor.impl.ThreadExecutor;
import com.studiant.com.presentation.presenters.impl.ProfilParticulierPresenterImpl;
import com.studiant.com.presentation.presenters.interfaces.ProfilParticulierPresenter;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.presentation.ui.activities.etudiant.DashboardEtudiantActivity;
import com.studiant.com.storage.impl.UserRepositoryImpl;
import com.studiant.com.storage.network.WSException;
import com.studiant.com.threading.MainThreadImpl;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static com.studiant.com.storage.Constants.INTENT_FROM_FACEBOOK;
import static com.studiant.com.storage.Constants.INTENT_USER;
import static com.studiant.com.storage.Constants.STATUS_CONNEXION_FACEBOOK;
import static com.studiant.com.storage.Constants.STATUS_CONNEXION_NORMAL;
import static com.studiant.com.storage.Constants.STATUS_ETUDIANT;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfilEtudiant2Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfilEtudiant2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfilEtudiant2Fragment extends Fragment implements ProfilParticulierPresenter.View{
    @BindView(R.id.imageViewProfilPicture)
    ImageView profilPictureImageView;

    @BindView(R.id.editTextFirstName)
    EditText firstNameEditText;

    @BindView(R.id.editTextLastName)
    EditText lastNameEditText;

    @BindView(R.id.editTextTelephone)
    EditText telephoneEditText;

    @BindView(R.id.editTextEmail)
    EditText emailEditText;

    @BindView(R.id.editTextDiplome)
    EditText diplomeEditText;

    @BindView(R.id.editTextDescription)
    EditText descriptionEditText;

    @BindView(R.id.editTextPassword)
    EditText passwordEditText;

    @BindView(R.id.switchPermis)
    Switch permisSwitch;
    boolean fromFacebook;

    private ProgressDialog progressDialog;
    User user;
    private ProfilParticulierPresenter mPresenter;
    private OnFragmentInteractionListener mListener;
    private String encodedImage;

    private static final int SELECT_PICTURE = 1;

    public ProfilEtudiant2Fragment() {
        // Required empty public constructor
    }

    public static ProfilEtudiant2Fragment newInstance(boolean fromFacebook) {
        ProfilEtudiant2Fragment fragment = new ProfilEtudiant2Fragment();
        Bundle args = new Bundle();
        args.putBoolean(INTENT_FROM_FACEBOOK, fromFacebook);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fromFacebook = getArguments().getBoolean(INTENT_FROM_FACEBOOK);
        }
        mPresenter = new ProfilParticulierPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new UserRepositoryImpl() {
                }
        );

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil_etudiant2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mPresenter.resume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @OnClick(R.id.buttonValidateEtudiant)
    void onClickValidate() {
        System.out.println("onClickValidate");

        if (!verifForm())
            return;

        if (user != null){
            user.setDescription(descriptionEditText.getText().toString());
            user.setDiplome(diplomeEditText.getText().toString());
            user.setTelephone(telephoneEditText.getText().toString());
            user.setPermis(permisSwitch.isChecked());
            user.setTypeUser(STATUS_ETUDIANT);
            user.setTypeConnexion(STATUS_CONNEXION_FACEBOOK);

            if (FirebaseInstanceId.getInstance().getToken() != null)
                user.setFirebaseToken(FirebaseInstanceId.getInstance().getToken());

            Log.d("Token ", " - "+FirebaseInstanceId.getInstance().getToken());

            mPresenter.insertProfile(user);

        }else{
            user = new User();
            user.setLastName(lastNameEditText.getText().toString());
            user.setDescription(descriptionEditText.getText().toString());
            user.setFirstName(firstNameEditText.getText().toString());
            user.setEmail(emailEditText.getText().toString());
            user.setDiplome(diplomeEditText.getText().toString());
            user.setTelephone(telephoneEditText.getText().toString());
            user.setPermis(permisSwitch.isChecked());
            user.setTypeUser(STATUS_ETUDIANT);
            try {
                user.setPassword(SecurityUtils.hashToSha512(passwordEditText.toString()));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            user.setTypeConnexion(STATUS_CONNEXION_NORMAL);

            mPresenter.uploadImage(encodedImage);
        }

    }

    public boolean verifForm(){
        if (lastNameEditText.getText().toString().length() > 1 && firstNameEditText.getText().toString().length() > 1
            && isValidEmail(emailEditText.getText().toString()) && diplomeEditText.getText().toString().length() > 1
            && telephoneEditText.getText().toString().length() == 10 && passwordEditText.toString().length() > 1)
            return true;
        else{
            Toast.makeText(getActivity(), "Merci de vérifier votre saisie",
                    Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    @OnClick(R.id.imageViewProfilPicture)
    void onImageViewClick(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                    //InputStream inputStream = getContext().getContentResolver().openInputStream(selectedImageUri);
                    Picasso.with(getContext()).load(selectedImageUri).into(profilPictureImageView);

                InputStream imageStream = null;
                try {
                    imageStream = getActivity().getContentResolver().openInputStream(selectedImageUri);
                    Bitmap selectedImage = getResizedBitmap(BitmapFactory.decodeStream(imageStream), 200);
                    encodedImage = encodeImage(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String encodeImage(Bitmap bm)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }

        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    @Override
    public void onProfileRetrieve(User mUser) {
        user = mUser;
        firstNameEditText.setText(user.getFirstName());
        lastNameEditText.setText(user.getLastName());
        emailEditText.setText(user.getEmail());
        Picasso.with(getContext()).load(user.getProfilePicture()).into(profilPictureImageView);
    }

    @OnClick(R.id.cguTextView)
    public void onCguTextViewClick() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://studiant.fr/?page_id=987"));
        startActivity(browserIntent);
    }

    @Override
    public void onImageUpload(String urlImage) {
        System.out.println("onImageUpload" + urlImage);
        user.setProfilePicture(urlImage);
        mPresenter.insertProfile(user);
    }

    @Override
    public void onUserInsert(User user) {
        mPresenter.saveUser(user);
        Intent intent = new Intent(getActivity(), DashboardEtudiantActivity.class);
        intent.putExtra(INTENT_USER, user);
        this.startActivity(intent);
    }

    @Override
    public void showProgress() {
        progressDialog.setMessage(getResources().getString(R.string.get_message_add_rib));
        progressDialog.show();

    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }


    @Override
    public void showError(WSException e) {

    }

    public interface OnFragmentInteractionListener {
    }
}
