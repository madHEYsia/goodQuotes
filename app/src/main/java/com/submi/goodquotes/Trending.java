package com.submi.goodquotes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.Random;

public class Trending extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Trending() {
        // Required empty public constructor
    }

    public static Trending newInstance(String param1, String param2) {
        Trending fragment = new Trending();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mContainer = inflater.inflate(R.layout.fragment_trending, null);
        LinearLayout linearLayout = mContainer.findViewById(R.id.mainLayout);
        Random r = new Random();
        quoteArray ob = new quoteArray();
        final String array[][] = ob.quotes;
        int length = array.length;
        for (int i=0;i<100;++i){
            final int index = r.nextInt(length);
            TextView quote = new TextView(getActivity());
            quote.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            quote.setPadding(40,40,40,0);
            quote.setText(array[index][0]);
            quote.setTypeface(null, Typeface.BOLD);
            quote.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
            quote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_TEXT,array[index][0]+" "+array[index][1]);
                    shareIntent.setType("text/plain");
                    startActivity(shareIntent);
                }
            });

            TextView writer = new TextView(getActivity());
            writer.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            writer.setPadding(40,0,40,40);
            writer.setText(array[index][1]);
            writer.setGravity(Gravity.END);
            writer.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            writer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_TEXT,array[index][0]+" "+array[index][1]);
                    shareIntent.setType("text/plain");
                    startActivity(shareIntent);
                }
            });

            View separator = new View(getActivity());
            separator.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    3));
            separator.setBackgroundColor(Color.parseColor("#B3B3B3"));

            try{
                linearLayout.addView(quote);
                linearLayout.addView(writer);
                linearLayout.addView(separator);

                if (i!=0 && i%10==0){
                    AdView mAdView = new AdView(getActivity());
                    mAdView.setAdSize(AdSize.BANNER);
                    mAdView.setAdUnitId("ca-app-pub-7600086344629483/9920526515");
                    AdRequest adRequest = new AdRequest.Builder().build();
                    mAdView.loadAd(adRequest);
                    linearLayout.addView(mAdView);
                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }

        return mContainer;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
