package com.submi.goodquotes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Home extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Home() {
        // Required empty public constructor
    }

    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
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
        final View mContainer = inflater.inflate(R.layout.fragment_home, null);

        final SearchView searchView = (SearchView) mContainer.findViewById(R.id.searchbox);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                LinearLayout linearLayout = mContainer.findViewById(R.id.home_layout);
                linearLayout.removeAllViewsInLayout();
                quoteArray ob = new quoteArray();
                final String array[][] = ob.quotes;
                int len = array.length;
                boolean anyFound = false;
                for (int i=0;i<len;++i){
                    if (array[i][0].toLowerCase().indexOf(query.toLowerCase())>0 || array[i][1].toLowerCase().indexOf(query.toLowerCase())>0){
                        anyFound = true;
                        TextView quote = new TextView(getActivity());
                        quote.setLayoutParams(new ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.FILL_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                        quote.setPadding(40,40,40,0);
                        quote.setText(array[i][0]);
                        quote.setTypeface(null, Typeface.BOLD);
                        quote.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
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
                        writer.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
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
                                3));
                        separator.setBackgroundColor(Color.parseColor("#B3B3B3"));

                        try{
                            linearLayout.addView(quote);
                            linearLayout.addView(writer);
                            linearLayout.addView(separator);
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
                if (!anyFound){
                    TextView error = new TextView(getActivity());
                    error.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.FILL_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    error.setPadding(40,40,40,0);
                    error.setText("No result found for "+query);
                    error.setTypeface(null, Typeface.BOLD);
                    error.setTextSize(TypedValue.COMPLEX_UNIT_SP, 19);
                    try{
                        linearLayout.addView(error);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }

                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

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
