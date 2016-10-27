package com.wxhl.weibo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.UsersAPI;
import com.wxhl.core.utils.L;
import com.wxhl.core.utils.T;
import com.wxhl.weibo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,WBAuthActivity.class));
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        Oauth2AccessToken mAccessToken = AccessTokenKeeper.readAccessToken(this);

        UsersAPI usersAPI = new UsersAPI(this,Constants.APP_KEY,mAccessToken);

        usersAPI.show("小七的欧尼酱", new RequestListener() {
            @Override
            public void onComplete(String s) {
                T.show(MainActivity.this,s, Toast.LENGTH_SHORT);
                L.e("++++++++++++++++"+s);
            }

            @Override
            public void onWeiboException(WeiboException e) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }
}