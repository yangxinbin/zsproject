package com.mango.leo.zsproject.personalcenter.show;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.base.BaseActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.util.ProjectsJsonUtils;
import com.mango.leo.zsproject.login.bean.UserMessageBean;
import com.mango.leo.zsproject.personalcenter.photoutils.PhotoUtils;
import com.mango.leo.zsproject.personalcenter.show.userchange.CompanyActivity;
import com.mango.leo.zsproject.personalcenter.show.userchange.DepartmentActivity;
import com.mango.leo.zsproject.personalcenter.show.userchange.NameActivity;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.HttpUtils;
import com.mango.leo.zsproject.utils.Urls;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class UserChangeActivity extends BaseActivity {

    @Bind(R.id.imageView_user_back)
    ImageView imageViewUserBack;
    @Bind(R.id.picture)
    RelativeLayout picture;
    @Bind(R.id.r1)
    RelativeLayout r1;
    @Bind(R.id.r2)
    RelativeLayout r2;
    @Bind(R.id.r3)
    RelativeLayout r3;
    @Bind(R.id.r4)
    RelativeLayout r4;
    @Bind(R.id.r5)
    RelativeLayout r5;
    @Bind(R.id.where)
    RelativeLayout where;
    @Bind(R.id.r1_e)
    ImageView r1E;
    @Bind(R.id.r2_e)
    ImageView r2E;
    @Bind(R.id.r3_e)
    ImageView r3E;
    @Bind(R.id.circleImageView)
    CircleImageView circleImageView;
    @Bind(R.id.textView_1)
    TextView textView1;
    @Bind(R.id.textView_2)
    TextView textView2;
    @Bind(R.id.textView)
    TextView textView3;
    @Bind(R.id.textView_4)
    TextView textView4;
    @Bind(R.id.textView_5)
    TextView textView5;
    @Bind(R.id.textView_where)
    TextView textViewWhere;
    @Bind(R.id.r4_e)
    ImageView r4E;
    @Bind(R.id.r5_e)
    ImageView r5E;
    private SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
    private Uri imageUri;
    private Uri cropImageUri;
    private static final int OUTPUT_X = 480;
    private static final int OUTPUT_Y = 480;
    private String TAG = "UserChangeActivity";
    private CityPickerView mPicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_change);
        ButterKnife.bind(this);
        //申明对象
        mPicker = new CityPickerView();
        mPicker.init(this);
        EventBus.getDefault().register(this);
        sharedPreferences = getSharedPreferences("CIFIT", MODE_PRIVATE);
        getImageAndMes();//后台请求数据
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void userMessageEventBus(UserMessageBean bean) {
        textView1.setText(bean.getResponseObject().getName());
        textView2.setText(bean.getResponseObject().getDepartment());
        textView3.setText(bean.getResponseObject().getPosition());
        textView4.setText(bean.getResponseObject().getUsername());
        textView5.setText(bean.getResponseObject().getEmail());
        textViewWhere.setText(bean.getResponseObject().getLocation().getProvince() + "-" + bean.getResponseObject().getLocation().getCity() + "-" + bean.getResponseObject().getLocation().getDistrict());
        if (bean.getResponseObject().getTenant() != null) {//认证
            r1E.setVisibility(View.GONE);
            r2E.setVisibility(View.GONE);
            r3E.setVisibility(View.GONE);
            r4E.setVisibility(View.GONE);
            r5E.setVisibility(View.GONE);
            r1.setClickable(false);
            r2.setClickable(false);
            r3.setClickable(false);
            r4.setClickable(false);
            r5.setClickable(false);
        }
        //头像
        if (bean.getResponseObject().getAvator().getId() != null) {//
            Log.v("yxbb", "dddd");
            Glide.with(this).load(Urls.HOST+"/user-service/user/get/file?fileId=" + bean.getResponseObject().getAvator().getId()).into(circleImageView);
        }

    }

    private void getImageAndMes() {
    }


    private void saveImage() {
        // 假设已知头像文件的本地存储路径如下
    }

    private void showTypeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View view = View.inflate(this, R.layout.dialog_select_photo, null);
        TextView tv_select_gallery = (TextView) view.findViewById(R.id.tv_select_gallery);
        TextView tv_select_camera = (TextView) view.findViewById(R.id.tv_select_camera);
        tv_select_gallery.setOnClickListener(new View.OnClickListener() {// 在相册中选取
            @Override
            public void onClick(View v) {
                autoObtainStoragePermission();
                dialog.dismiss();
            }
        });
        tv_select_camera.setOnClickListener(new View.OnClickListener() {// 调用照相机
            @Override
            public void onClick(View v) {
                autoObtainCameraPermission();
                dialog.dismiss();
            }
        });
        dialog.setView(view);
        dialog.show();
    }

    /**
     * 动态申请sdcard读写权限
     */
    private void autoObtainStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSIONS_REQUEST_CODE);
            } else {
                PhotoUtils.openPic(this, CODE_GALLERY_REQUEST);
            }
        } else {
            PhotoUtils.openPic(this, CODE_GALLERY_REQUEST);
        }
    }

    /**
     * 申请访问相机权限
     */
    private void autoObtainCameraPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                    AppUtils.showToast(this, "您已经拒绝过一次");
                }
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, CAMERA_PERMISSIONS_REQUEST_CODE);
            } else {//有权限直接调用系统相机拍照
                if (hasSdcard()) {
                    imageUri = Uri.fromFile(fileUri);
                    //通过FileProvider创建一个content类型的Uri
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        imageUri = FileProvider.getUriForFile(this, "com.mango.leo.zsproject", fileUri);
                    }
                    PhotoUtils.takePicture(this, imageUri, CODE_CAMERA_REQUEST);
                } else {
                    AppUtils.showToast(this, "设备没有SD卡！");
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            //调用系统相机申请拍照权限回调
            case CAMERA_PERMISSIONS_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (hasSdcard()) {
                        imageUri = Uri.fromFile(fileUri);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            //通过FileProvider创建一个content类型的Uri
                            imageUri = FileProvider.getUriForFile(this, "com.mango.leo.zsproject", fileUri);
                        }
                        PhotoUtils.takePicture(this, imageUri, CODE_CAMERA_REQUEST);
                    } else {
                        AppUtils.showToast(this, "设备没有SD卡！");
                    }
                } else {
                    AppUtils.showToast(this, "请允许打开相机！！");
                }
                break;
            }
            //调用系统相册申请Sdcard权限回调
            case STORAGE_PERMISSIONS_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    PhotoUtils.openPic(this, CODE_GALLERY_REQUEST);
                } else {
                    AppUtils.showToast(this, "请允许打操作SDCard！！");
                }
                break;
            default:
        }
    }

    //存放图片的地址，可以改动
    private Uri BitMap(Bitmap bitmap) {
        File tmpDir = new File(Environment.getExternalStorageDirectory() + "/Bmob");    //保存地址及命名
        if (!tmpDir.exists()) {
            tmpDir.mkdir(); //生成目录进行保存
        }
        File img = new File(tmpDir.getAbsolutePath() + "avatar.png");
        try {
            FileOutputStream fos = new FileOutputStream(img);
            bitmap.compress(Bitmap.CompressFormat.PNG, 85, fos);  //参:压缩的格式，图片质量85，输出流
            fos.flush();
            fos.close();
            return Uri.fromFile(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @OnClick({R.id.imageView_user_back, R.id.picture, R.id.r1, R.id.r2, R.id.r3, R.id.r4, R.id.r5, R.id.where})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageView_user_back:
                finish();
                break;
            case R.id.picture:
                showTypeDialog();
                break;
            case R.id.r1:
                intent = new Intent(this, NameActivity.class);
                intent.putExtra("name", textView1.getText());
                startActivity(intent);
                finish();
                break;
            case R.id.r2:
                intent = new Intent(this, CompanyActivity.class);//单位
                intent.putExtra("department", textView2.getText());
                startActivity(intent);
                finish();
                break;
            case R.id.r3:
                intent = new Intent(this, DepartmentActivity.class);//职称
                intent.putExtra("position", textView3.getText());
                startActivity(intent);
                finish();
                break;
            case R.id.r4:
                intent = new Intent(this, ChangePhoneActivity.class);//职称
                intent.putExtra("position", textView3.getText());
                startActivity(intent);
                break;
            case R.id.r5:
                intent = new Intent(this, ChangeEmailActivity.class);//职称
                intent.putExtra("position", textView3.getText());
                startActivity(intent);
                break;
            case R.id.where:
                showSeleteCity();
                break;
            /*case R.id.r4:
                break;
            case R.id.r5:
                break;*/
        }
    }
    private void showSeleteCity() {
        //添加默认的配置，不需要自己定义
        CityConfig cityConfig = new CityConfig.Builder().build();
        mPicker.setConfig(cityConfig);
        //监听选择点击事件及返回结果
        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                //省份
                if (province != null) {
                    //provinceString = String.valueOf(province);
                }
                //城市
                if (city != null) {
                    //cityString = String.valueOf(city);
                   /* editor.putString("position", cityString)
                            .commit();*/
                }
                //地区
                if (district != null) {
                    //districtString = String.valueOf(district);
                }
                textViewWhere.setText(province + "-" + city + "-" + district);
                //上传

                updateWhere();
            }

            @Override
            public void onCancel() {
                AppUtils.showToast(getApplicationContext(), "城市选择已取消");
            }
        });

        //显示
        mPicker.showCityPicker();
    }

    private void updateWhere() {


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult: requestCode: " + requestCode + "  resultCode:" + resultCode);
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            Log.e(TAG, "onActivityResult: resultCode!=RESULT_OK");
            return;
        }
        switch (requestCode) {
            //相机返回
            case CODE_CAMERA_REQUEST:
                cropImageUri = Uri.fromFile(fileCropUri);
                PhotoUtils.cropImageUri(this, imageUri, cropImageUri, 1, 1, OUTPUT_X, OUTPUT_Y, CODE_RESULT_REQUEST);
                //upLoadMap(cropImageUri);
                break;
            //相册返回
            case CODE_GALLERY_REQUEST:
                if (hasSdcard()) {
                    //upLoadMap(cropImageUri);
                    cropImageUri = Uri.fromFile(fileCropUri);
                    Uri newUri = Uri.parse(PhotoUtils.getPath(this, data.getData()));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        newUri = FileProvider.getUriForFile(this, "com.mango.leo.zsproject", new File(newUri.getPath()));
                    }
                    PhotoUtils.cropImageUri(this, newUri, cropImageUri, 1, 1, OUTPUT_X, OUTPUT_Y, CODE_RESULT_REQUEST);
                } else {
                    AppUtils.showToast(this, "设备没有SD卡！");
                }
                break;
            //裁剪返回
            case CODE_RESULT_REQUEST:
                Bitmap bitmap = PhotoUtils.getBitmapFromUri(cropImageUri, this);
                upLoadMap(cropImageUri);
                //这里上传文件
                if (bitmap != null) {
                    showImages(bitmap);
                }
                break;
            default:
        }
    }

    private void upLoadMap(Uri uri) {
        Log.v("upLoadMap", uri.toString() + "-----upLoadMap-----" + sharedPreferences.getString("token", ""));
        HttpUtils.doPostWithAll(Urls.HOST_AVATAR, getRealFilePath(this, uri), sharedPreferences.getString("token", ""), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //listener.onFailure("MES_FAILURE",e);
                Log.v("upLoadMap", e.toString() + "-----MES_FAILURE-----" + e.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AppUtils.showToast(getBaseContext(), "头像上传失败");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (String.valueOf(response.code()).startsWith("2")) {
                    //listener.onSuccess("MES_SUCCESS");//异步请求
                    //注册时以及获取Token这里不用重复获取了
                        /*UserMessageBean bean = ProjectsJsonUtils.readJsonUserMessageBeans(response.body().string());//data是json字段获得data的值即对象
                        listener.getSuccessUserMessage(bean);*/
                    UserMessageBean bean = ProjectsJsonUtils.readJsonUserMessageBeans(response.body().string());
                    EventBus.getDefault().postSticky(bean);
//                    Log.v("upLoadMap", "-----bean-----" + bean.getResponseObject().getAvator().toString());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AppUtils.showToast(getBaseContext(), "头像上传成功");
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AppUtils.showToast(getBaseContext(), "头像上传失败");
                        }
                    });
                    Log.v("upLoadMap", response.body().string() + "******" + response.code() + Urls.HOST_AVATAR);
                    //listener.onSuccess("MES_FAILURE");
                }
            }
        });

    }

    /**
     * Try to return the absolute file path from the given Uri
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    private void showImages(Bitmap bitmap) {
        circleImageView.setImageBitmap(bitmap);
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        ButterKnife.unbind(this);
    }
}
