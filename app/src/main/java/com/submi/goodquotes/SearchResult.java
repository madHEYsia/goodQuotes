package com.submi.goodquotes;

import android.content.Context;
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

import java.util.Random;

public class SearchResult extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SearchResult() {
        // Required empty public constructor
    }

    public static SearchResult newInstance(String param1, String param2) {
        SearchResult fragment = new SearchResult();
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
        View mContainer = inflater.inflate(R.layout.fragment_search_result, null);
        LinearLayout linearLayout = mContainer.findViewById(R.id.searchLayout);
        Random r = new Random();
        quoteArray ob = new quoteArray();
        String array[][] = ob.quotes;
        int length = array.length;
        for (int i=0;i<100;++i){
            int index = r.nextInt(length);
            TextView quote = new TextView(getActivity());
            quote.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            quote.setPadding(40,40,40,0);
            quote.setText(array[index][0]);
            quote.setTypeface(null, Typeface.BOLD);
            quote.setTextSize(TypedValue.COMPLEX_UNIT_SP, 19);

            TextView writer = new TextView(getActivity());
            writer.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            writer.setPadding(40,0,40,40);
            writer.setText(array[index][1]);
            writer.setGravity(Gravity.END);
            writer.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);

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
