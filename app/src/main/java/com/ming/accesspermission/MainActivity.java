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
    /**
     * 使用系统相机请求代码
     */
    public static final int CAMERA_PERMISSIONS_REQUEST_CODE = 2011;
    /**
     * 向SD中写入请求代码
     */
    public static final int WRITE_EXTERNAL_STORAGE_PERMISSIONS_REQUEST_CODE = 2012;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.tv);
        accessPermission = new AccessPermissionUtil(MainActivity.this);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accessPermission.checkPermissions(new AccessPermissionUtil.RequestPerssionCallBack() {
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
                },  Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        accessPermission.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
