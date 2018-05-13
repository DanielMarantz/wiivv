package com.wiivv.assessment.view.fragment.base;

import android.os.Build;
import android.support.annotation.TransitionRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.transition.TransitionInflater;
import android.transition.Visibility;
import android.view.View;
import android.widget.Toast;

public class BaseFragment extends Fragment {

    public void showMessage(String msg) {
        if (msg != null) {
            Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
        }
    }

    public void showSnackBar(View view, String msg) {
        if (view != null) {
            Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
        }
    }

    public void enterTransition(Visibility transition) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setEnterTransition(transition);
        }
    }

    public void exitTransition(Visibility transition) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setExitTransition(transition);
        }
    }

    public void sharedMaterialEnterTransition(@TransitionRes int resource) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(resource));
        }
    }

    public void sharedMaterialReturnTransition(@TransitionRes int resource) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementReturnTransition(TransitionInflater.from(getContext()).inflateTransition(resource));
        }
    }
}
