package com.example.decoration.ui.fragments.Third;
import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.decoration.R;

import com.example.decoration.base.BaseMainFragment;
import com.example.decoration.model.Moment;
import com.wx.goodview.GoodView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bingoogolapple.baseadapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.baseadapter.BGAOnRVItemLongClickListener;
import cn.bingoogolapple.baseadapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.baseadapter.BGARecyclerViewHolder;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPreviewActivity;
import cn.bingoogolapple.photopicker.imageloader.BGARVOnScrollListener;
import cn.bingoogolapple.photopicker.widget.BGANinePhotoLayout;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class CircleAttentionFragment extends BaseMainFragment implements EasyPermissions.PermissionCallbacks, BGANinePhotoLayout.Delegate, BGAOnRVItemClickListener, BGAOnRVItemLongClickListener {

    private static final int PRC_PHOTO_PREVIEW = 1;
    GoodView mGoodView;
    private static final int RC_ADD_MOMENT = 1;

    private RecyclerView mMomentRv;
    private MomentAdapter mMomentAdapter;

    /**
     * 设置图片预览时是否具有保存图片功能「测试接口用的」
     */
    private CheckBox mDownLoadableCb;

    private BGANinePhotoLayout mCurrentClickNpl;
    private Button mButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
     //  getActivity().startActivity(new Intent(getActivity(), MomentListActivity.class));
        mGoodView = new GoodView(getContext());
        super.onCreate(savedInstanceState);
    }

    public static CircleAttentionFragment newInstance() {
        Bundle args = new Bundle();
        CircleAttentionFragment fragment = new CircleAttentionFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getLayoutId() {
        return 0;
    }


    @Override
    public void onRVItemClick(ViewGroup parent, View itemView, int position) {

    }

    @Override
    public boolean onRVItemLongClick(ViewGroup parent, View itemView, int position) {
        return false;
    }

    @Override
    public void onClickNinePhotoItem(BGANinePhotoLayout ninePhotoLayout, View view, int position, String model, List<String> models) {
        mMomentAdapter.photoPreviewWrapper();
    }

    @Override
    public void onClickExpand(BGANinePhotoLayout ninePhotoLayout, View view, int position, String model, List<String> models) {

    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_moment_list, container, false);
//        mDownLoadableCb = view.findViewById(R.id.cb_moment_list_downloadable);
        mMomentRv = view.findViewById(R.id.rv_moment_list_moments);
        mMomentAdapter = new MomentAdapter(mMomentRv);
        mMomentAdapter.setOnRVItemClickListener(this);
        mMomentAdapter.setOnRVItemLongClickListener(this);
        mMomentRv.addOnScrollListener(new BGARVOnScrollListener(getActivity()));
        mMomentRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mMomentRv.setAdapter(mMomentAdapter);
        addNetImageTestData();
        return view;
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == RC_ADD_MOMENT) {
           // mMomentAdapter.addFirstItem(MomentAddActivity.getMoment(data));
            mMomentRv.smoothScrollToPosition(0);
        }
    }


    /**
     * 添加网络图片测试数据
     */
    private void addNetImageTestData() {
        List<Moment> moments = new ArrayList<>();

        moments.add(new Moment("说说你家装修落地花了多少钱？ \n大价钱买的飘窗，最后只用来堆放衣服？教你一招，再也不怕飘窗浪费了。", new ArrayList<>(Arrays.asList("https://images.unsplash.com/photo-1517581177682-a085bb7ffb15?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1172&q=80"))));
        moments.add(new Moment("2张网络图片", new ArrayList<>(Arrays.asList("https://images.unsplash.com/photo-1681817170711-8e6278223a60?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1964&q=80", "https://images.unsplash.com/photo-1618832515490-e181c4794a45?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8cmVub3ZhdGlvbnxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=300&q=60"))));
        moments.add(new Moment("9张网络图片", new ArrayList<>(Arrays.asList("https://images.unsplash.com/photo-1600585152220-90363fe7e115?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTB8fHJlbm92YXRpb258ZW58MHx8MHx8&auto=format&fit=crop&w=300&q=60", "https://images.unsplash.com/photo-1600566752355-35792bedcfea?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjN8fHJlbm92YXRpb258ZW58MHx8MHx8&auto=format&fit=crop&w=300&q=60", "https://images.unsplash.com/photo-1561817223-f67e69e6bd6a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NTB8fHJlbm92YXRpb258ZW58MHx8MHx8&auto=format&fit=crop&w=300&q=60", "https://images.unsplash.com/photo-1631456754232-1a3278e76deb?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NTl8fHJlbm92YXRpb258ZW58MHx8MHx8&auto=format&fit=crop&w=300&q=60", "https://images.unsplash.com/photo-1521783593447-5702b9bfd267?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NTZ8fHJlbm92YXRpb258ZW58MHx8MHx8&auto=format&fit=crop&w=300&q=60", "https://images.unsplash.com/photo-1591240193130-add0e75860f9?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NjZ8fHJlbm92YXRpb258ZW58MHx8MHx8&auto=format&fit=crop&w=300&q=60", "https://images.unsplash.com/photo-1556910638-6cdac31d44dc?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nzd8fHJlbm92YXRpb258ZW58MHx8MHx8&auto=format&fit=crop&w=300&q=60", "https://images.unsplash.com/photo-1502005097973-6a7082348e28?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80", "https://images.unsplash.com/photo-1513506003901-1e6a229e2d15?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OTJ8fHJlbm92YXRpb258ZW58MHx8MHx8&auto=format&fit=crop&w=300&q=60"))));
        moments.add(new Moment("5张网络图片", new ArrayList<>(Arrays.asList("https://images.unsplash.com/photo-1604159848821-104723525eb4?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTR8fHJlbm92YXRpb258ZW58MHx8MHx8&auto=format&fit=crop&w=300&q=60", "https://images.unsplash.com/flagged/photo-1573168710465-7f7da9a23a15?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTJ8fHJlbm92YXRpb258ZW58MHx8MHx8&auto=format&fit=crop&w=300&q=60", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered13.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered14.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered15.png"))));
        moments.add(new Moment("3张网络图片", new ArrayList<>(Arrays.asList("http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered4.png", "https://images.unsplash.com/photo-1584622650111-993a426fbf0a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTd8fHJlbm92YXRpb258ZW58MHx8MHx8&auto=format&fit=crop&w=300&q=60", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered6.png"))));
        moments.add(new Moment("8张网络图片", new ArrayList<>(Arrays.asList("https://images.unsplash.com/photo-1561817223-f67e69e6bd6a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NTB8fHJlbm92YXRpb258ZW58MHx8MHx8&auto=format&fit=crop&w=300&q=60", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered12.png", "https://images.unsplash.com/photo-1501183638710-841dd1904471?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NDV8fHJlbm92YXRpb258ZW58MHx8MHx8&auto=format&fit=crop&w=300&q=60", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered14.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered15.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered16.png", "https://images.unsplash.com/photo-1521783593447-5702b9bfd267?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NTZ8fHJlbm92YXRpb258ZW58MHx8MHx8&auto=format&fit=crop&w=300&q=60", "https://images.unsplash.com/photo-1591240193130-add0e75860f9?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NjZ8fHJlbm92YXRpb258ZW58MHx8MHx8&auto=format&fit=crop&w=300&q=60"))));
        moments.add(new Moment("4张网络图片", new ArrayList<>(Arrays.asList("http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered7.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered8.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered9.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered10.png"))));
        moments.add(new Moment("2张网络图片", new ArrayList<>(Arrays.asList("http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered2.png", "https://images.unsplash.com/photo-1501183638710-841dd1904471?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NDV8fHJlbm92YXRpb258ZW58MHx8MHx8&auto=format&fit=crop&w=300&q=60"))));
        moments.add(new Moment("3张网络图片", new ArrayList<>(Arrays.asList("http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered4.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered5.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered6.png"))));
        moments.add(new Moment("4张网络图片", new ArrayList<>(Arrays.asList("http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered7.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered8.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered9.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered10.png"))));
        moments.add(new Moment("9张网络图片", new ArrayList<>(Arrays.asList("https://images.unsplash.com/photo-1600566752355-35792bedcfea?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjN8fHJlbm92YXRpb258ZW58MHx8MHx8&auto=format&fit=crop&w=300&q=60", "https://images.unsplash.com/photo-1521783593447-5702b9bfd267?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NTZ8fHJlbm92YXRpb258ZW58MHx8MHx8&auto=format&fit=crop&w=300&q=60", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered13.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered14.png", "https://images.unsplash.com/photo-1600566752355-35792bedcfea?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjN8fHJlbm92YXRpb258ZW58MHx8MHx8&auto=format&fit=crop&w=300&q=60", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered16.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered17.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered18.png", "https://images.unsplash.com/photo-1591240193130-add0e75860f9?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NjZ8fHJlbm92YXRpb258ZW58MHx8MHx8&auto=format&fit=crop&w=300&q=60"))));
        moments.add(new Moment("1张网络图片", new ArrayList<>(Arrays.asList("http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered1.png"))));
        moments.add(new Moment("5张网络图片", new ArrayList<>(Arrays.asList("http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered11.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered12.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered13.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered14.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered15.png"))));
        moments.add(new Moment("6张网络图片", new ArrayList<>(Arrays.asList("http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered11.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered12.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered13.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered14.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered15.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered16.png"))));
        moments.add(new Moment("7张网络图片", new ArrayList<>(Arrays.asList("http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered11.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered12.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered13.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered14.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered15.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered16.png", "https://images.unsplash.com/photo-1600566752355-35792bedcfea?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjN8fHJlbm92YXRpb258ZW58MHx8MHx8&auto=format&fit=crop&w=300&q=60"))));
        moments.add(new Moment("8张网络图片", new ArrayList<>(Arrays.asList("http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered11.png", "https://images.unsplash.com/photo-1501183638710-841dd1904471?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NDV8fHJlbm92YXRpb258ZW58MHx8MHx8&auto=format&fit=crop&w=300&q=60", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered15.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered16.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered17.png", "http://bgashare.bingoogolapple.cn/refreshlayout/images/staggered18.png","https://foter.com/photos/425/farmhouse-coffee-table-1.jpeg?s=art", "https://foter.com/photos/425/farmhouse-coffee-table-1.jpeg?s=art"))));
        ArrayList<String> photos = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            photos.add("https://foter.com/photos/425/industrial-coffee-table-"+i+".jpeg?s=art");
        }
        moments.add(new Moment("9张网络图片", photos));

        mMomentAdapter.setData(moments);
    }

    public void good(View view) {

        ((ImageView) view).setImageResource(R.drawable.thumbsup);
        mGoodView.setText("+1");
        mGoodView.show(view);
    }

    private class MomentAdapter extends BGARecyclerViewAdapter<Moment> {

        public MomentAdapter(RecyclerView recyclerView) {
            super(recyclerView, R.layout.item_moment);
        }
        /**
         * 图片预览，兼容6.0动态权限
         */
        @AfterPermissionGranted(PRC_PHOTO_PREVIEW)
        private void photoPreviewWrapper() {
            if (mCurrentClickNpl == null) {
                return;
            }

            String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (EasyPermissions.hasPermissions(getContext(), perms)) {
                File downloadDir = new File(Environment.getExternalStorageDirectory(), "BGAPhotoPickerDownload");
                BGAPhotoPreviewActivity.IntentBuilder photoPreviewIntentBuilder = new BGAPhotoPreviewActivity.IntentBuilder(getContext());

                if (mDownLoadableCb.isChecked()) {
                    // 保存图片的目录，如果传 null，则没有保存图片功能
                    System.out.println("-------------"+downloadDir+"-----------------");
                    photoPreviewIntentBuilder.saveImgDir(downloadDir);
                }

                if (mCurrentClickNpl.getItemCount() == 1) {
                    // 预览单张图片
                    photoPreviewIntentBuilder.previewPhoto(mCurrentClickNpl.getCurrentClickItem());
                } else if (mCurrentClickNpl.getItemCount() > 1) {
                    // 预览多张图片
                    photoPreviewIntentBuilder.previewPhotos(mCurrentClickNpl.getData())
                            .currentPosition(mCurrentClickNpl.getCurrentClickItemPosition()); // 当前预览图片的索引
                }
                startActivity(photoPreviewIntentBuilder.build());
            } else {
                EasyPermissions.requestPermissions(getActivity(), "图片预览需要以下权限:\n\n1.访问设备上的照片", PRC_PHOTO_PREVIEW, perms);

            }
        }

        @Override
        public BGARecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return super.onCreateViewHolder(parent, viewType);
        }
public void onclick(BGAViewHolderHelper helper){
    final GoodView goodView = new GoodView(getContext());
    ImageView thumsup = helper.getImageView(R.id.item_mo_thumbsup);
    thumsup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            goodView.setText("+1");
            ((ImageView) view).setImageResource(R.drawable.good_checked);
            goodView.show(view);
        }
    });

    ImageView collect =helper.getImageView(R.id.item_mo_collect);
    collect.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            goodView.setText("+1");
            ((ImageView) view).setImageResource(R.drawable.collection_checked);
            goodView.show(view);
        }
    });
}
        @Override
        protected void fillData(BGAViewHolderHelper helper, int position, Moment moment) {
            onclick(helper);

            if (TextUtils.isEmpty(moment.content)) {
                helper.setVisibility(R.id.tv_item_moment_content, View.GONE);
            } else {
                helper.setVisibility(R.id.tv_item_moment_content, View.VISIBLE);
                helper.setText(R.id.tv_item_moment_content, moment.content);
            }
            BGANinePhotoLayout ninePhotoLayout = helper.getView(R.id.npl_item_moment_photos);
           // ninePhotoLayout.setDelegate(getActivity());
            ninePhotoLayout.setData(moment.photos);
        }

    }
}
