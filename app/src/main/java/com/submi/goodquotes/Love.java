package com.submi.goodquotes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Love extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Love() {
        // Required empty public constructor
    }

    public static Love newInstance(String param1, String param2) {
        Love fragment = new Love();
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
        // Inflate the layout for this fragment
        View mContainer = inflater.inflate(R.layout.fragment_love, null);
        LinearLayout linearLayout = mContainer.findViewById(R.id.love_layout);
        quoteArray ob = new quoteArray();
        final String array[][] = ob.quotes;
        for (int i=144;i<224;++i){
            TextView quote = new TextView(getActivity());
            quote.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            quote.setPadding(40,40,40,0);
            quote.setText(array[i][0]);
            quote.setTypeface(null, Typeface.BOLD);
            quote.setTextSize(TypedValue.COMPLEX_UNIT_SP, 19);
            final int finalI = i;
            quote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_TEXT,array[finalI][0]+" "+array[finalI][1]);
                    shareIntent.setType("text/plain");
                    startActivity(shareIntent);
                }
            });

            TextView writer = new TextView(getActivity());
            writer.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            writer.setPadding(40,0,40,40);
            writer.setText(array[i][1]);
            writer.setGravity(Gravity.END);
            writer.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            writer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_TEXT,array[finalI][0]+" "+array[finalI][1]);
                    shareIntent.setType("text/plain");
                    startActivity(shareIntent);
                }
            });

            View separator = new View(getActivity());
            separator.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    10));
            separator.setBackgroundColor(Color.parseColor("#B3B3B3"));

            try{
                linearLayout.addView(quote);
                linearLayout.addView(writer);
                linearLayout.addView(separator);
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
