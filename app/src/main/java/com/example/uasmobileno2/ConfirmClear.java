package com.example.uasmobileno2;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uasmobileno2.adapter.FragmentAdapter;
import com.example.uasmobileno2.objects.Products;

import java.util.ArrayList;

public class ConfirmClear extends androidx.fragment.app.DialogFragment {
    private DBHandler handler;
    private Context context;

    public ConfirmClear(DBHandler handler, Context context) {
        this.handler = handler;
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.confirmclearlayout, container, false);

        Button confirm = (Button) view.findViewById(R.id.confirmClearBtn);
        Button cancel = (Button) view.findViewById(R.id.cancelClearBtn);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.deleteOrder();
                Toast.makeText(context, "Order history successfully cleared.", Toast.LENGTH_SHORT).show();
                requireActivity().onBackPressed();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return view;
    }
}
