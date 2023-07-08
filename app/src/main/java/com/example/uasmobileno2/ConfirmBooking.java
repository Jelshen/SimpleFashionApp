package com.example.uasmobileno2;

import android.content.Context;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uasmobileno2.adapter.FragmentAdapter;
import com.example.uasmobileno2.objects.Orders;
import com.example.uasmobileno2.objects.Products;

import java.util.ArrayList;
import java.util.Objects;

public class ConfirmBooking extends androidx.fragment.app.DialogFragment {

    private ArrayList<Products> productsList = new ArrayList<>();
    private Context context;

    public ConfirmBooking(Context context, ArrayList<Products> productsList) {
        this.context = context;
        this.productsList = productsList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.bookinglayout, container, false);

        // RecyclerView initialization
        RecyclerView bookingRV = (RecyclerView) view.findViewById(R.id.fragmentRV);
        FragmentAdapter adapter = new FragmentAdapter(context, productsList);
        bookingRV.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        bookingRV.setAdapter(adapter);

        int totalItemPrice = 0;
        for(int i = 0; i < productsList.size(); i++) {
            totalItemPrice += productsList.get(i).getAmount() * productsList.get(i).getProductPrice();
        }

        TextView totalPrice = (TextView) view.findViewById(R.id.totalPrice);
        totalPrice.setText(String.valueOf(totalItemPrice));

        Button confirm = (Button) view.findViewById(R.id.confirmFragmentBtn);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler handler = new DBHandler(context);
                handler.addNewOrder(productsList);
                Log.d("Fragment Btn", "onClick: Success");
                requireActivity().onBackPressed();
            }
        });

        return view;
    }
}
