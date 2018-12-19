package com.ming.accesspermission;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import ming.com.accesspermission_lib.AccessPermissionUtil;

public class MainActivity extends AppCompatActivity {

    private AccessPermissionUtil accessPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.tv);
        accessPermission = new AccessPermissionUtil(MainActivity.this);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accessPermission.checkPermission(AccessPermissionUtil.CAMERA_PERMISSIONS_REQUEST_CODE, new AccessPermissionUtil.RequestPerssionCallBack() {
                    @Override
                    public void onPermissionDenied(int requestCode, String[] permissions) {
                        Toast.makeText(MainActivity.this, "拒绝", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionAllow(int requestCode, String[] permissions) {
                        Toast.makeText(MainActivity.this, "允许", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        accessPermission.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
