package com.example.uasmobileno2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uasmobileno2.adapter.ProductAdapter;
import com.example.uasmobileno2.objects.Products;

import java.util.ArrayList;

public class ViewProducts extends AppCompatActivity {
    private RecyclerView productRV;
    private ArrayList<Products> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewproducts);

        productList = new ArrayList<>();

        Products product1 = new Products("Black T-Shirt", 100000, 0);
        Products product2 = new Products("Black Pants", 250000, 0);

        productList.add(product1);
        productList.add(product2);

        Log.d("Size", "onCreate: " + productList.size());

        // RecyclerView setting
        productRV = findViewById(R.id.productRV);

        ProductAdapter adapter = new ProductAdapter(ViewProducts.this, productList);

        productRV.setLayoutManager(new LinearLayoutManager(ViewProducts.this, RecyclerView.VERTICAL, false));
        productRV.setAdapter(adapter);

        AppCompatButton confirm = (AppCompatButton) findViewById(R.id.productConfirmBtn);
        ImageView backarrow = (ImageView) findViewById(R.id.viewproductsarrow);

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Back Arrow", "onClick: This back arrow is clicked");
                finish();
//                startActivity(new Intent(ViewProducts.this, MainActivity.class));
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Products> newList = new ArrayList<>();

                for(int i = 0; i < productList.size(); i++) {
                    if(productList.get(i).getAmount() != 0) newList.add(productList.get(i));
                }

                if(newList.isEmpty()) {
                    Toast.makeText(ViewProducts.this, "Add at least one item.", Toast.LENGTH_SHORT).show();
                }
                else {
                    ConfirmBooking display = new ConfirmBooking(ViewProducts.this, newList);
                    display.show(getSupportFragmentManager(), "display");
                }
            }
        });
    }

}
