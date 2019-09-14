package com.ming.accesspermission;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import ming.com.accesspermission_lib.AccessPermissionUtil;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv_activity = findViewById(R.id.acc_activity);
        TextView tv_fragment = findViewById(R.id.acc_fragment);
        tv_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AccessActivity.class);
                startActivity(intent);
            }
        });
        tv_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AccessFragmentActivity.class);
                startActivity(intent);
            }
        });
    }


}
