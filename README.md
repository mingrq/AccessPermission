# 权限监测

最新版本2.0.1

#
### 使用
#
```
在 build.gradle 中添加
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
```
dependencies {
	        implementation 'com.github.mingrq:AccessPermission:Tag'
	}
```
#
```
在需要检测权限的Activity或Fragment中重写 onRequestPermissionsResult 方法
在方法中添加
 accessPermission.onRequestPermissionsResult(requestCode, permissions, grantResults);
 
 如下
 @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        accessPermission.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
```

### 方法
#
###  void setcheckPermissions(String... permission)
设置需要检测的权限数组
```
accessPermission.setcheckPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE);
```
#
### void checkPermissions(int requestCode,RequestPerssionCallBack callBack)
检测权限
```
requestCode 请求码
callBack 检测权限后的回调
```
```
public interface RequestPerssionCallBack {
        /**
         * 授权权限失败
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
```
