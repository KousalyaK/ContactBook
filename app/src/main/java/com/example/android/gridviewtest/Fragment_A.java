package com.example.android.gridviewtest;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;



/**
 * Created by anjana on 9/28/15.
 */

public class Fragment_A extends Fragment {

    private Communicationinterface mListener;
    Fragment_B fragment_b;
    public interface Communicationinterface{
        public void onSentText(String key);
    }

    GridView gridView;

    static final String[] numbers = new String[] {
            "A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_a, container, false);
        gridView = (GridView) view.findViewById(R.id.gridView1);

        final SearchView sv = (SearchView) view.findViewById(R.id.serchkey);
        ButtonAdapter adapter = new ButtonAdapter(getActivity());

        sv.setImeOptions(EditorInfo.IME_ACTION_SEARCH | EditorInfo.IME_FLAG_NO_EXTRACT_UI | EditorInfo.IME_FLAG_NO_FULLSCREEN);
        sv.setIconifiedByDefault(true);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mListener.onSentText(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("entred ", newText);
                fragment_b = (Fragment_B) getFragmentManager().findFragmentById(R.id.lstFragment);
                fragment_b.filterValueset(newText);
                mListener.onSentText(newText);
                return false;
            }
        });

       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, numbers);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Button button = (Button) view.findViewById(R.id.button_alpha);
                String keyvalue = button.getText().toString();
                Log.d("KeyValue: ", keyvalue);
                ((MainActivity) getActivity()).setActionBarTitle(keyvalue);
                fragment_b = (Fragment_B) getFragmentManager().findFragmentById(R.id.lstFragment);
                fragment_b.filterValueset(keyvalue);

                Toast.makeText(getActivity(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (Communicationinterface) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }



}

