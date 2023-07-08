package com.example.uasmobileno2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewAccount extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewprofile);

        ImageView backarrow = (ImageView) findViewById(R.id.viewaccountarrow);

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Back Arrow", "onClick: This back arrow is clicked");
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("key", "haha lmao");
                setResult(69, i);
                finish();
            }
        });
    }
}
