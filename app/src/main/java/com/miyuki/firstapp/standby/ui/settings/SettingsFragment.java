package com.miyuki.firstapp.standby.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.miyuki.firstapp.standby.AnoneActivity;
import com.miyuki.firstapp.standby.R;

public class SettingsFragment extends Fragment {

    private SettingsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        notificationsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        Button anone = (Button) root.findViewById(R.id.anone);
        anone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AnoneActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }
}