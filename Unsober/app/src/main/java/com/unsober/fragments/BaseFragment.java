package com.unsober.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.View;

import com.unsober.R;
import com.unsober.activities.MainCategoryActivity;
import com.unsober.activities.SubCategoryActivity;
import com.unsober.database.DbRepository;
import com.unsober.utils.ServerSyncManager;
import com.unsober.utils.SessionManager;

import java.util.Stack;


/**
 * Created by akshay on 18-06-2016.
 */
public class BaseFragment extends Fragment implements SubCategoryActivity.BackPressListener {
    protected ServerSyncManager mServerSyncManager = null;
    protected DbRepository mDbRepository = null;
    protected static SessionManager mSessionManager = null;
    public static Stack<BaseFragment> stackFragment = new Stack<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSessionManager = SessionManager.getInstance(getActivity().getApplicationContext());
        Log.d("##", "##" + mSessionManager.getDatabaseDeviceFullPath());
        mServerSyncManager = new ServerSyncManager(getActivity().getApplicationContext(), mSessionManager);
        mDbRepository = new DbRepository(getActivity().getApplicationContext(), mSessionManager);
        SubCategoryActivity.setOnBackPress(this);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    protected void showProgress(final boolean show, final View hideFormView, final View showProgressView) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            if (hideFormView != null) {
                hideFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                hideFormView.animate().setDuration(shortAnimTime).alpha(
                        show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        hideFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                    }
                });
            }
            if (showProgressView != null) {
                showProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                showProgressView.animate().setDuration(shortAnimTime).alpha(
                        show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        showProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                    }
                });
            }
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            showProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            hideFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    protected void customAlterDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity().getApplicationContext());
        builder.setTitle("" + title);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    @Override
    public void OnBackPress() {
        if (stackFragment.isEmpty()) {
            startActivity(new Intent(getActivity().getApplicationContext(), MainCategoryActivity.class));
            getActivity().finish();
        } else {
            BaseFragment fragment = stackFragment.pop();
            getFragmentManager().beginTransaction().replace(R.id.fragment_frame_lay, fragment).commit();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

