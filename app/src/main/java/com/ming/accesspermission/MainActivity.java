package com.ming.accesspermission;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
                accessPermission.setcheckPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                accessPermission.checkPermissions(123,new AccessPermissionUtil.RequestPerssionCallBack() {
                    @Override
                    public void onPermissionDenied(int requestCode, String[] permissions) {
                        Toast.makeText(MainActivity.this, permissions[0], Toast.LENGTH_SHORT).show();
                        for (String d : permissions) {
                            Log.e("检测", d);
                        }
                    }

                    @Override
                    public void onPermissionAllow(int requestCode, String[] permissions) {
                        Toast.makeText(MainActivity.this, "允许", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPerpetualPermissionDenied(int requestCode, String[] permissions) {
                        Toast.makeText(MainActivity.this, "永久不允许", Toast.LENGTH_SHORT).show();
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
