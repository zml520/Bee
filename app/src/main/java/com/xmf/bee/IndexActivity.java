package com.xmf.bee;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

import com.mlcm.special.base.BaseActivity2016;
import com.mlcm.special.common.Constants;
import com.mlcm.special.ui.activity.vpurse.LoginActivity;
import com.mlcm.special.util.StringUtil;
import com.mlcm.special.util.Utils;
import com.mlcm.special.util.logUtil.LogUtil;
import com.umeng.analytics.MobclickAgent;


/**
 * 作者：wxw on 2016/7/15 15:58
 * 邮箱：1069289509@qq.com
 */
public class IndexActivity extends BaseActivity2016 {
    private Activity _context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        isCanSwipeBack = false;
        super.onCreate(savedInstanceState);
        isAuToSetBlack = false;
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
        _context = IndexActivity.this;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_index);
        TextView tv_version_code = (TextView) findViewById(R.id.tv_version_code);
        String versionName = Utils.getVersionName(_context);
        if (versionName != null) {
            tv_version_code.setText(versionName);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (StringUtil.isEmpty(Utils.getConfigValue(_context, "userToken", ""))) {
                    Intent intent = new Intent(_context, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(_context, MainActivity.class);
//                    Intent intent = new Intent(_context, BaseDemoActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 2000);
        //数据库初始化
//        new Thread() {
//            public void run() {
//                AddressDBUtils.copyDb(IndexActivity.this, "address.db");
//            }
//
//        }.start();
        // 友盟统计分析设置
        MobclickAgent.enableEncrypt(Constants.IS_DEBUG != true);
        MobclickAgent.setDebugMode(Constants.IS_DEBUG);
        //打印手机硬件信息
        LogUtil.i("IndexActivity", Utils.getHandSetInfo() + "");
    }


}




