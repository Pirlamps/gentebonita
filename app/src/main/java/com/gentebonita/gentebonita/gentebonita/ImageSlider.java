package com.gentebonita.gentebonita.gentebonita;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class ImageSlider extends Fragment {

    public String url;
    public ImageSlider() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.slide_image, container, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);

        ImageView img1 = (ImageView) view.findViewById(R.id.imgprinc);
        if(url.equals("0"))
        {
            img1.setImageResource(R.drawable.fotodefault);
        }
        else if(url.equals("1"))
        {
            img1.setImageResource(R.drawable.fotoslide1);
        }
        else if(url.equals("2"))
        {
            img1.setImageResource(R.drawable.fotoslide2);
        }
        else if(url.equals("3"))
        {
            img1.setImageResource(R.drawable.fotoslide3);
        }
    }
}