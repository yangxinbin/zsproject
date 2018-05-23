package com.mango.leo.zsproject.industrialservice.createrequirements.carditems;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.base.BaseActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.BusinessPlanActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.adapter.GridImageAdapter;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.basecard.BaseCardActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardFirstItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.presenter.UpdateItemPresenter;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.presenter.UpdateItemPresenterImpl;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.view.UpdateItemView;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.viewutil.FullyGridLayoutManager;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.viewutil.LinedEditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CardFirstItemActivity extends BaseCardActivity implements UpdateItemView {

    @Bind(R.id.imageView1_back)
    ImageView imageView1Back;
    @Bind(R.id.textView1_save)
    TextView textView1Save;
    @Bind(R.id.item_title)
    EditText itemTitle;
    @Bind(R.id.item_content)
    LinedEditText itemContent;
    @Bind(R.id.recycler)
    RecyclerView recyclerView;
    private int themeId;
    GridImageAdapter adapter;
    private List<LocalMedia> selectList = new ArrayList<>();
    private CardFirstItemBean cardFirstItemBean;
    private List<LocalMedia> itemImagesPath = new ArrayList<>();
    private UpdateItemPresenter updateItemPresenter;
    public static final int TYPE1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_first_item);
        ButterKnife.bind(this);
        themeId = R.style.picture_default_style;
        initAddImage();
        updateItemPresenter = new UpdateItemPresenterImpl(this);
        cardFirstItemBean = new CardFirstItemBean();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void card1EventBus(CardFirstItemBean bean) {
        itemTitle.setText(bean.getItemName());
        itemContent.setText(bean.getItemContent());
        adapter.setList(bean.getItemImagePath());
        selectList = bean.getItemImagePath();
        cardFirstItemBean.setProjectId(bean.getProjectId());
    }

    private void initDate() {
        cardFirstItemBean.setItemName(itemTitle.getText().toString());
        cardFirstItemBean.setItemContent(itemContent.getText().toString());
        cardFirstItemBean.setItemImagePath(selectList);
    }

    private void initAddImage() {
        FullyGridLayoutManager manager = new FullyGridLayoutManager(CardFirstItemActivity.this, 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new GridImageAdapter(CardFirstItemActivity.this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(5);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).themeStyle(themeId).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(CardFirstItemActivity.this).themeStyle(themeId).openExternalPreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(CardFirstItemActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(CardFirstItemActivity.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            // 进入相册 以下是例子：不需要的api可以不写
            PictureSelector.create(CardFirstItemActivity.this)
                    .openGallery(PictureMimeType.ofAll())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    .theme(themeId)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                    .maxSelectNum(5)// 最大图片选择数量
                    .minSelectNum(1)// 最小选择数量
                    .imageSpanCount(4)// 每行显示个数
                    .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                    .previewImage(false)// 是否可预览图片
                    .previewVideo(false)// 是否可预览视频
                    .enablePreviewAudio(false) // 是否可播放音频
                    .isCamera(true)// 是否显示拍照按钮
                    .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                    //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                    //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                    .enableCrop(false)// 是否裁剪
                    .compress(false)// 是否压缩
                    .synOrAsy(true)//同步true或异步false 压缩 默认同步
                    //.compressSavePath(getPath())//压缩图片保存地址
                    //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                    .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                    .withAspectRatio(3, 4)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                    .hideBottomControls(false)// 是否显示uCrop工具栏，默认不显示
                    .isGif(false)// 是否显示gif图片
                    .freeStyleCropEnabled(false)// 裁剪框是否可拖拽
                    .circleDimmedLayer(false)// 是否圆形裁剪
                    .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                    .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                    .openClickSound(false)// 是否开启点击声音
                    .selectionMedia(selectList)// 是否传入已选图片
                    //.isDragFrame(false)// 是否可拖动裁剪框(固定)
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
                    //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                    //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                    .minimumCompressSize(100)// 小于100kb的图片不压缩
                    //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                    //.rotateEnabled(true) // 裁剪是否可旋转图片
                    //.scaleEnabled(true)// 裁剪是否可放大缩小图片
                    //.videoQuality()// 视频录制质量 0 or 1
                    //.videoSecond()//显示多少秒以内的视频or音频也可适用
                    //.recordVideoSecond()//录制视频秒数 默认60s
                    .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code

        }

    };

    @OnClick({R.id.imageView1_back, R.id.textView1_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView1_back:
                Intent intent = new Intent(this, BusinessPlanActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.textView1_save:
                initDate();
                if (!TextUtils.isEmpty(itemTitle.getText().toString()) && !TextUtils.isEmpty(itemContent.getText().toString()) && cardFirstItemBean != null) {
                    updateItemPresenter.visitUpdateItem(this, TYPE1, cardFirstItemBean);//更新后台数据
                    EventBus.getDefault().postSticky(cardFirstItemBean);
                    //intent = new Intent(this, BusinessPlanActivity.class);
                    //startActivity(intent);
                    //finish();
                } else {
                    AppUtils.showSnackar(textView1Save, "必填项不能为空！");
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void showUpdateStateView(final String string) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AppUtils.showToast(getApplicationContext(), string);
            }
        });
    }

    @Override
    public void showUpdateFailMsg(final String string) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AppUtils.showToast(getApplicationContext(), string);
            }
        });
    }
}
