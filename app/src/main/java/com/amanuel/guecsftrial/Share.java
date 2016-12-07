package com.amanuel.guecsftrial;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Amanuel on 9/9/2016.
 */
public class Share extends Fragment {
    //@Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.share);

        Intent sendInt = new Intent(Intent.ACTION_SEND);
        sendInt.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
        sendInt.putExtra(Intent.EXTRA_TEXT, "GUECSF App\n\"" + getString(R.string.app_name)
                + "\" \nhttps://play.google.com/store/apps/details?id=" );
        sendInt.setType("text/plain");
        startActivity(Intent.createChooser(sendInt, "Share"));


    }
     @Override
     public View onCreateView(LayoutInflater inflater,
                              @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.share, container, false);
        return v;

    }
    }
