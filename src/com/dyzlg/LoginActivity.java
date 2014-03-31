package com.dyzlg;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dyzlg.base.BaseActivity;
import com.dyzlg.model.UserValidate;
import com.dyzlg.network.HttpResponseListener;
import com.dyzlg.network.manager.AuthorizationManager;
import com.dyzlg.tools.IOUtil;
import com.dyzlg.tools.JsonReader;

/**
 * 登陆页
 * larry create on 2014-3-17
 */
public class LoginActivity extends BaseActivity implements OnClickListener{
	private ImageView headerLeft;
	private ImageView headerRight;
	private TextView title, collect;
	private EditText username, password;
	private Button login, registr;
	private RelativeLayout collectLayout;
	private UserValidate userValidate;
	
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				userValidate = (UserValidate)msg.obj;
				if(userValidate != null && userValidate.getCode() == 1){					
					Intent intent = new Intent(LoginActivity.this, SettingActivity.class);
					startActivity(intent);
					finish();
				}
				break;
			case 4:
				Toast.makeText(LoginActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
		}};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		setlistener();
	}

	private void initView() {
		headerLeft = (ImageView)findViewById(R.id.header_left);
		headerRight = (ImageView)findViewById(R.id.header_right);
		title = (TextView)findViewById(R.id.header_title);
		username = (EditText)findViewById(R.id.login_username);
		password = (EditText)findViewById(R.id.login_password);
		login = (Button)findViewById(R.id.login_button_login);
		registr = (Button)findViewById(R.id.login_button_register);
		collect = (TextView)findViewById(R.id.login_collect);
		collectLayout = (RelativeLayout)findViewById(R.id.login_collect_layout);
		headerRight.setVisibility(View.INVISIBLE);
		collect.setText(getResources().getString(R.string.login_i_collect, 0));
	}

	private void setlistener() {
		headerLeft.setOnClickListener(this);
		login.setOnClickListener(this);
		registr.setOnClickListener(this);
		collectLayout.setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.header_left:
			finish();
			break;
		case R.id.login_button_login:
			if("".equals(username.getEditableText().toString()) || "".equals(password.getEditableText().toString())){
				Message msg = new Message();
				msg.what = 4;
				msg.obj = "账号或密码不能为空";
				handler.sendMessage(msg);
			} else {
				login(username.getEditableText().toString(), password.getEditableText().toString());
			}
			break;
		case R.id.login_button_register:
			break;
		case R.id.login_collect_layout:
			break;
		}
	}
	
	private void login(String username, String password){
		AuthorizationManager.getInstance().login(username, password, new HttpResponseListener() {
			@Override
			public void succeed(InputStream inStream) {
				String string = IOUtil.convertInputStreamToString(inStream);
				Message msg = new Message();
				msg.what = 0;
				try {
					msg.obj = JsonReader.parseUserValidate(URLDecoder.decode(string, "utf-8"));
					System.out.println("-----login-----"+ URLDecoder.decode(string, "utf-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				handler.sendMessage(msg);
			}
			
			@Override
			public void failed(String errMsg) {
//				Log.d("larry", errMsg);
				System.out.println("*******" + errMsg);
				if(errMsg == null){
					errMsg = "登录失败";
				}
				Message msg = new Message();
				msg.what = 4;
				msg.obj = errMsg;
				handler.sendMessage(msg);
			}
		});
	}

}
