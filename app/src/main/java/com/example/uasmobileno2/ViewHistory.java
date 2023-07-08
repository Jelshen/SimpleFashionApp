package com.example.uasmobileno2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uasmobileno2.adapter.HistoryAdapter;
import com.example.uasmobileno2.objects.Orders;

import java.util.ArrayList;

public class ViewHistory extends AppCompatActivity implements HistoryAdapter.OnItemListener{

    private DBHandler handler;
    private ArrayList<Orders> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderhistory);

        handler = new DBHandler(ViewHistory.this);
        ImageView backarrow = (ImageView) findViewById(R.id.orderhistoryarrow);
        AppCompatButton clear = (AppCompatButton) findViewById(R.id.clearHistoryBtn);

        list = handler.readOrders();

        RecyclerView historyRV = (RecyclerView) findViewById(R.id.historyRV);
        HistoryAdapter adapter = new HistoryAdapter(list,ViewHistory.this, this);
        historyRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        historyRV.setAdapter(adapter);

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list.size() != 0) {
                    ConfirmClear display = new ConfirmClear(handler,ViewHistory.this);
                    display.show(getSupportFragmentManager(), "display");
                    Log.d("Clear History", "onClick: Successful");
                }
                else {
                    Toast.makeText(ViewHistory.this, "There is nothing inside the history..", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        handler = new DBHandler(ViewHistory.this);
        list = handler.readOrders();

        Log.d("pos", "onItemClick: " + position);

        ShowHistoryDetails display = new ShowHistoryDetails(ViewHistory.this, list, position);
        display.show(getSupportFragmentManager(), "display");

    }
}
