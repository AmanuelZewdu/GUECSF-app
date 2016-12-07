package com.amanuel.guecsftrial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Amanuel on 8/9/2016.
 */
public class feedback extends Fragment {
    @Nullable
    //@Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


         View v= inflater.inflate(R.layout.feed_back, container,false);
        return v;
    }
}
