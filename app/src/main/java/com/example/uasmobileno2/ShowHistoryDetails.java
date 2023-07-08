package com.example.uasmobileno2;

import android.content.Context;
import android.os.Bundle;
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
import com.example.uasmobileno2.adapter.ShowHistoryAdapter;
import com.example.uasmobileno2.objects.Orders;
import com.example.uasmobileno2.objects.Products;

import java.util.ArrayList;

public class ShowHistoryDetails extends androidx.fragment.app.DialogFragment {
    private ArrayList<Orders> ordersList = new ArrayList<>();
    private Context context;
    private int position;

    public ShowHistoryDetails(Context context, ArrayList<Orders> ordersList, int position) {
        this.context = context;
        this.ordersList = ordersList;
        this.position = position;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.showhistorydetails, container, false);

        Orders order = ordersList.get(position);
        ArrayList<Products> productList = new ArrayList<>();

        int totalPrice = 0;
        String[] productNames = order.getItems().split(", ");
        String[] productPrices = order.getPrice().split(", ");
        String[] productAmount = order.getAmount().split(", ");

        for(int i = 0; i < productNames.length; i++) {
            productList.add(new Products(productNames[i], Integer.parseInt(productPrices[i]), Integer.parseInt(productAmount[i])));

//            Log.d("ProductName", "onCreateView: " + productList.get(i).getProductName());
//            Log.d("ProductPrice", "onCreateView: " + productList.get(i).getProductPrice());
//            Log.d("ProductAmount", "onCreateView: " + productList.get(i).getAmount());
        }

        // RecyclerView initialization
        RecyclerView historyRV = (RecyclerView) view.findViewById(R.id.historyRV);
        ShowHistoryAdapter adapter = new ShowHistoryAdapter(context, productList);
        historyRV.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        historyRV.setAdapter(adapter);

        for(int i = 0; i < productPrices.length; i++) {
            totalPrice += Integer.parseInt(productPrices[i]) * Integer.parseInt(productAmount[i]);
        }

        TextView purchaseDate = (TextView) view.findViewById(R.id.purchaseDate);
        TextView totalItemPrice = (TextView) view.findViewById(R.id.totalPrice);

        purchaseDate.setText(order.getDate());
        totalItemPrice.setText(String.valueOf(totalPrice));

        return view;
    }
}
