package com.lookout.keyswap.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.lookout.keyswap.gpg.GPGFactory;
import com.lookout.keyswap.R;

public class PublicKeysFragment extends Fragment {
    SimpleAdapter adapter;

    public PublicKeysFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_key, container, false);

        GPGFactory.buildPublicKeyList();

        ListView lv = (ListView) rootView.findViewById(R.id.keyView);
        String[] from = { "full_name", "short_id", "email" };
        int[] to = { R.id.full_name, R.id.short_id, R.id.email };
        adapter = new SimpleAdapter(rootView.getContext(), GPGFactory.getKeys(), R.layout.key_list_item, from, to);
        lv.setAdapter(adapter);

        getActivity().setTitle("Public Keys");
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}
