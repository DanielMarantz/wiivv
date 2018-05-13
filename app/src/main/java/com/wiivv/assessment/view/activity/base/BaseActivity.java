package com.wiivv.assessment.view.activity.base;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.test.realtor.assessment.MainApplication;

/**
 * All Activities will be extended from this class.
 */
public class BaseActivity extends AppCompatActivity {

    public MainApplication getMainApplication() {
        return (MainApplication) getApplication();
    }

    public void showMessage(String msg) {
        if (msg != null) {
            Toast.makeText(getApplication(), msg, Toast.LENGTH_LONG).show();
        }
    }

    public void showSnackBar(View view, String msg) {
        if (view != null) {
            Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
        }
    }

    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment).commit();
    }

    /**
     * Starts a FragmentTransaction with or without a shared view element for
     * Material Design transitions.
     *
     * @param sharedElement
     * @return
     */
    protected FragmentTransaction buildFragmentTransaction(View sharedElement) {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        if (sharedElement != null) {
            transaction.addSharedElement(sharedElement, ViewCompat.getTransitionName(sharedElement));
        }
        return transaction;
    }
}
