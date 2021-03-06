package com.lookout.keyswap.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import com.lookout.keyswap.gpg.GPGFactory;
import com.lookout.keyswap.R;

public class KeyTrustLevelFragment extends Fragment {
    public KeyTrustLevelFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_key_trust_level, container, false);

        Button signButton = (Button)rootView.findViewById(R.id.sign_key_button);


        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sign_trust = "unknown";

                RadioButton unknown_rb = (RadioButton)rootView.findViewById(R.id.unknown_trust_level);
                if(unknown_rb.isChecked()) {
                    sign_trust = "unknown";
                }

                RadioButton none_rb = (RadioButton)rootView.findViewById(R.id.none_trust_level);
                if(none_rb.isChecked()) {
                    sign_trust = "none";
                }

                RadioButton marginal_rb = (RadioButton)rootView.findViewById(R.id.marginal_trust_level);
                if(marginal_rb.isChecked()) {
                    sign_trust = "marginal";
                }

                RadioButton full_rb = (RadioButton)rootView.findViewById(R.id.full_trust_level);
                if(full_rb.isChecked()) {
                    sign_trust = "full";
                }

                GPGFactory.signReceivedKey(sign_trust);

                loadSendSignedFragment();
            }
        });

        getActivity().setTitle("Key Exchange");
        return rootView;
    }

    private void loadSendSignedFragment() {
        Fragment fragment = new SendSignedFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment, "send_signed").commit();
    }
}

