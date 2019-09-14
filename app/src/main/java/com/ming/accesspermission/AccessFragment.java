package com.ming.accesspermission;


import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import ming.com.accesspermission_lib.AccessPermissionUtil;

public class AccessFragment extends Fragment {

    private AccessPermissionUtil permissionUtil;

    public AccessFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accessfragment,container,false);
        init(view);
        return view;
    }
    private void init(View view){
        permissionUtil = new AccessPermissionUtil(this);
        TextView textView = view.findViewById(R.id.tv_getper_fragment);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permissionUtil.setcheckPermissions(Manifest.permission.CAMERA);
                permissionUtil.checkPermissions(123, new AccessPermissionUtil.RequestPerssionCallBack() {
                    @Override
                    public void onPermissionDenied(int requestCode, String[] permissions) {
                        Toast.makeText(getContext(),"拒绝："+permissions[0],Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionAllow(int requestCode, String[] permissions) {
                        Toast.makeText(getContext(),permissions[0],Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPerpetualPermissionDenied(int requestCode, String[] permissions) {

                    }
                });
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
