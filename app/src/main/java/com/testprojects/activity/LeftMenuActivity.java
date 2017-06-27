package com.testprojects.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.testprojects.R;
import com.testprojects.base.BaseActivity;
import com.testprojects.factory.ItemListDemoFactory;
import com.testprojects.net.bean.ItemListDemoBean;
import com.testprojects.utils.IntentUtil;

import java.util.ArrayList;
import java.util.List;

import me.xiaopan.assemblyadapter.AssemblyRecyclerAdapter;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class LeftMenuActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, EasyPermissions.PermissionCallbacks {
    /**
     * 切换 GridView 和列表类型
     */
    private Boolean isGridView;

    private static final int RC_CAMERA_PERM = 123;
    private static final int RC_LOCATION_CONTACTS_PERM = 124;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_left_menu;
    }

    @Override
    protected void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view ->
                {
                    try {
                        // 调用系统邮件 发送邮件
                        Intent data = new Intent(Intent.ACTION_SENDTO);
                        data.setData(Uri.parse("mailto:yuyexiaoxiang@gmail.com"));
                        //                    data.putExtra(Intent.EXTRA_SUBJECT, "这是标题");
                        //                    data.putExtra(Intent.EXTRA_TEXT, "这是内容");
                        startActivity(data);
                    } catch (Exception e) {
                        Log.e(TAG, "未安装Email服务或者已停用Email服务");
                    }
                }
        );

        initList();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        LinearLayout headLayout = (LinearLayout) navigationView.inflateHeaderView(R.layout.nav_header_left_menu);

        TextView userName = (TextView) headLayout.findViewById(R.id.userName);
        TextView userEmail = (TextView) headLayout.findViewById(R.id.userEmail);
        ImageView imageView = (ImageView) headLayout.findViewById(R.id.imageView);
        imageView.setOnClickListener((View view) ->
                {
                    Glide.with(this).load(R.mipmap.user_icon)
                            //                            .transition(new CropCircleTransformation(this))
                            .into(imageView);
                    userName.setText(R.string.user_name);
                    userEmail.setText(R.string.user_email);
                }
        );
    }

    /**
     * 初始化 list 列表
     */
    private void initList() {
        List<ItemListDemoBean> dataString = new ArrayList<>();
        dataString.add(new ItemListDemoBean().setItemData("摇一摇"));
        dataString.add(new ItemListDemoBean().setItemData("底部导航栏"));
        dataString.add(new ItemListDemoBean().setItemData("滑动详情页"));
        dataString.add(new ItemListDemoBean().setItemData("登录"));
        dataString.add(new ItemListDemoBean().setItemData("进度条"));
        dataString.add(new ItemListDemoBean().setItemData("通知"));
        dataString.add(new ItemListDemoBean().setItemData("对话框"));
        dataString.add(new ItemListDemoBean().setItemData("SnackBar的使用"));
        dataString.add(new ItemListDemoBean().setItemData("FlexBox的使用"));
        dataString.add(new ItemListDemoBean().setItemData("日历"));
        dataString.add(new ItemListDemoBean().setItemData("Play Video"));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);

        recyclerView.setLayoutManager(gridLayoutManager);
        //默认 gridView布局
        isGridView = true;
        AssemblyRecyclerAdapter adapter = new AssemblyRecyclerAdapter(dataString);
        adapter.addItemFactory(new ItemListDemoFactory());
        recyclerView.setAdapter(adapter);

        findViewById(R.id.recyclerButton).setOnClickListener(view ->
                {
                    if (!isGridView) {
                        recyclerView.setLayoutManager(gridLayoutManager);
                        isGridView = true;
                    } else {
                        recyclerView.setLayoutManager(linearLayoutManager);
                        isGridView = false;
                    }
                    adapter.notifyDataSetChanged();
                }
        );
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.left_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            IntentUtil.launch(this, SettingsActivity.class);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            //             Handle the camera action
            // TODO: 2017/6/27 获取权限 
//            showCamera();
            cameraTask();
        } else if (id == R.id.nav_gallery) {
            try {
                Intent intent = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivity(intent);
            } catch (Exception e) {
                Log.e(TAG, "相册未安装或已停止使用");
            }

        } else if (id == R.id.nav_slideshow) {
            Toast.makeText(this, "点击了视频", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_manage) {
            Toast.makeText(this, "点击了工具", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_share) {
            // 启动分享发送的属性
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain"); // 分享发送的数据类型
            //默认的分享内容
            //            String msg = "推荐给大家";
            //            intent.putExtra(Intent.EXTRA_TEXT, msg);
            // 目标应用选择对话框的标题
            startActivity(Intent.createChooser(intent, "选择分享"));
        } else if (id == R.id.nav_send) {
            // TODO: 2017/6/27 获取权限
//            showSendSms();
            locationAndContactsTask();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 发送信息
     */
    private void showSendSms() {
        try {
            //发送一个新的信息
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + ""));
            //发送一个目标信息
            //            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + "10086"));
            startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG, "未安装信息或已停用");
        }
    }

    @AfterPermissionGranted(RC_LOCATION_CONTACTS_PERM)
    public void locationAndContactsTask() {
        String[] perms = { Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_CONTACTS };
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Have permissions, do the thing!
            Toast.makeText(this, "TODO: Location and Contacts things", Toast.LENGTH_LONG).show();
            showSendSms();
        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(this, "This app needs access to your sms to read all your great messages.",
                    RC_LOCATION_CONTACTS_PERM, perms);
        }
    }

    /**
     * 显示相机
     */
    private void showCamera() {
        try {
            Intent intent = new Intent();
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG, "相机未安装或已停止使用");
        }
    }

    @AfterPermissionGranted(RC_CAMERA_PERM)
    public void cameraTask() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
            // Have permission, do the thing!
            Toast.makeText(this, "TODO: Camera things", Toast.LENGTH_LONG).show();
            showCamera();
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(this, "This app needs access to your camera so you can take pictures.",
                    RC_CAMERA_PERM, Manifest.permission.CAMERA);
        }
    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.d(TAG, "onPermissionsGranted:" + requestCode + ":" + perms.size());

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "onPermissionsDenied:" + requestCode + ":" + perms.size());

        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            // Do something after user returned from app settings screen, like showing a Toast.
            Toast.makeText(this, "Returned from app settings to MainActivity", Toast.LENGTH_SHORT)
                    .show();
        }
    }
}
