package com.example.decoration.ui.fragments.Second;

import static android.content.Context.ACTIVITY_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import com.example.decoration.R;
import com.example.decoration.activity.ArActivity;
import com.example.decoration.base.BaseMainFragment;

public class DecorationFragment extends BaseMainFragment {
    public static DecorationFragment newInstance() {
        Bundle args = new Bundle();
        DecorationFragment fragment = new DecorationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.decorationfragment, container, false);
        Button button = view.findViewById(R.id.open_arCamera);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ArActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
