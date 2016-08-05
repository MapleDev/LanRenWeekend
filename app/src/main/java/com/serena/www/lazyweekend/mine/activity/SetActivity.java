package com.serena.www.lazyweekend.mine.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.serena.www.lazyweekend.R;
import com.serena.www.lazyweekend.base.BaseActivity;
import com.serena.www.lazyweekend.util.ActivityUtil;
import com.serena.www.lazyweekend.util.ToastUtils;

import java.io.File;
import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * @author MxBox
 * @time 2016/7/30  16:01
 * @task 设置页面
 */
public class SetActivity extends BaseActivity {

    protected boolean loginState = false;
    private String mCacheSize;//缓存大小
    private Dialog progressDialog;//加载框

    private static int CLEAR_ACTION = 1;//清除缓存行为
    private static int UPDATE_ACTION = 2;//检查更新行为

    @BindView(R.id.set_share)
    RelativeLayout mSetShare;
    @BindView(R.id.set_clear)
    RelativeLayout mSetClear;
    @BindView(R.id.set_evaluate)
    RelativeLayout mSetEvaluate;
    @BindView(R.id.set_feedback)
    RelativeLayout mSetFeedback;
    @BindView(R.id.set_update)
    RelativeLayout mSetUpdate;
    @BindView(R.id.set_contact)
    RelativeLayout mSetContact;
    @BindView(R.id.action_left)
    TextView mActionLeft;
    @BindView(R.id.center_title)
    TextView mCenterTitle;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_set;

    }

    @Override
    protected void initView() {
        ShareSDK.initSDK(SetActivity.this);
        mActionLeft.setBackgroundResource(R.drawable.ic_nav_left);
        mCenterTitle.setText("设置");
    }


    @OnClick({R.id.action_left, R.id.set_share, R.id.set_clear, R.id.set_evaluate, R.id.set_feedback, R.id.set_update, R.id.set_contact})
    public void onClick(View view) {
        switch (view.getId()) {
            //回退
            case R.id.action_left:
                this.finish();
                break;
            //分享
            case R.id.set_share:
                showShare();
                break;
            //清除缓存
            case R.id.set_clear:
                showProgressbar(CLEAR_ACTION);
                break;
            //评价
            case R.id.set_evaluate:
                if (loginState) {

                } else {
                    ActivityUtil.startActivity(this, LoginActivity.class, false, 0);
                }
                break;
            //反馈
            case R.id.set_feedback:
                ActivityUtil.startActivity(this, FeedBackActivity.class, false, 0);
                break;
            //更新
            case R.id.set_update:
                showProgressbar(UPDATE_ACTION);
                break;
            //联系我们
            case R.id.set_contact:
//                ToastUtils.show(this, "瞎鸡巴点什么");
                String phone = ((TextView)findViewById(R.id.phone)).getText().toString().trim();
                callDial(this, phone);
                break;
        }
    }

    // 需添加权限<uses-permission android:name="android.permission.CALL_PHONE"/>
    // /** * 拨打电话 */
    public static void callDial(Context context, String phoneNumber) {
        context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber)));
    }

    //分享
    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("测试");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("消息来自懒人周末");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("消息来自懒人周末");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(this);
    }


    //progressbar
    private void showProgressbar(int who) {

        //显示加载框
        progressDialog = new Dialog(this, R.style.progress_dialog);
        //设置加载框样式
        progressDialog.setContentView(R.layout.dialog);
        //设置可撤销
        progressDialog.setCancelable(true);
        //背景透明
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        //文字
        TextView msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
        if (who == CLEAR_ACTION) {
            msg.setText("三倍加速清除缓存中");
            progressDialog.show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //获取临时缓存数据目录
                        File file = getApplicationContext().getExternalCacheDir();
                        Log.v("mxbox", file + "");
                        mCacheSize = getFormatSize(getFolderSize(file));
                        Log.v("mxbox", mCacheSize);
                        //清除应用缓存
                        deleteFilesByDirectory(getApplication().getCacheDir());
                        Thread.sleep(2000);
                        //返回主线程修改UI
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //消除加载框
                                progressDialog.dismiss();
                                if (mCacheSize.equals("0.0Byte")) {
                                    ToastUtils.show(SetActivity.this, "暂无缓存可清理!");
                                } else {
                                    ToastUtils.show(SetActivity.this, "本次共清理了" + mCacheSize + "缓存!");
                                }
                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                        ToastUtils.show(SetActivity.this, "服务器异常,请重试!");
                    }

                }
            }).start();
        } else {
            msg.setText("三倍加速检查更新中");
            progressDialog.show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //消除加载框
                                progressDialog.dismiss();
                                //不更新版本...写死了
                                ToastUtils.show(SetActivity.this, "已经是最新版本了哦!");
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        ToastUtils.show(SetActivity.this, "服务器异常,请重试!");
                    }

                }
            }).start();
        }

    }

    //清除缓存的方法
    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }


    // 获取缓存大小
    //Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
    //Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    //格式化缓存大小
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

}
