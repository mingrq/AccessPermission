package ming.com.accesspermission_lib;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限检测工具
 * ===================
 * Author MingRuQi
 * E-mail mingruqi@sina.cn
 * DateTime 2018/12/19 11:32
 */
public class AccessPermissionUtil {

    /**
     * ---------------------------------------------------
     */
    private Activity activity;
    private RequestPerssionCallBack callBack;
    private String[] permission;
    private int customRequest;

    public AccessPermissionUtil(Activity activity) {
        this.activity = activity;
    }

    public void setcheckPermissions(String... permission) {
        this.permission = permission;
    }

    /**
     * 获取权限
     */
    public void checkPermissions(int requestCode,RequestPerssionCallBack callBack) {
        this.callBack = callBack;
        customRequest = requestCode;
        List<String> permissionList = new ArrayList<>();
        for (int i = 0; i < permission.length; i++) {
            String per = permission[i];
            if (per != null) {
                if (ContextCompat.checkSelfPermission(activity, per) != PackageManager.PERMISSION_GRANTED) {
                    permissionList.add(per);
                }
            }
        }
        if (permissionList.size() > 0) {
            ActivityCompat.requestPermissions(activity, permissionList.toArray(new String[permissionList.size()]), requestCode);
        } else {
            callBack.onPermissionAllow(requestCode, permissionList.toArray(new String[permissionList.size()]));
        }
    }

    /**
     * 获取权限返回回执
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == customRequest) {
            List<String> deniedPermissions = new ArrayList<>();//拒绝授权的集合
            List<String> noShowPermission = new ArrayList<>();//没有显示授权的集合
            for (int k = 0; k < grantResults.length; k++) {
                if (grantResults[k] != PackageManager.PERMISSION_GRANTED) {
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, permissions[k])) {
                        noShowPermission.add(permissions[k]);
                    }
                    deniedPermissions.add(permissions[k]);
                }
            }
            if (deniedPermissions.size() > 0) {
                //部分权限没有授权
                if (deniedPermissions.equals(noShowPermission)) {
                    //所有拒绝授权的权限点击不再提示权限，获取权限窗口没有展示
                    callBack.onPerpetualPermissionDenied(requestCode, deniedPermissions.toArray(new String[deniedPermissions.size()]));
                } else {
                    callBack.onPermissionDenied(requestCode, deniedPermissions.toArray(new String[deniedPermissions.size()]));
                }
            } else {
                //用户全部授权
                callBack.onPermissionAllow(requestCode, permissions);
            }
        }
    }


    public interface RequestPerssionCallBack {
        /**
         *  授权权限失败
         */
        void onPermissionDenied(int requestCode, String[] permissions);

        /**
         * 全部授权权限成功
         */
        void onPermissionAllow(int requestCode, String[] permissions);

        /**
         * 权限拒绝并且设置了不在提示
         */
        void onPerpetualPermissionDenied(int requestCode, String[] permissions);
    }
}
