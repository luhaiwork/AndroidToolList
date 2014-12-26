package com.example.rsatest;

import java.io.ByteArrayOutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class TestRSA extends Activity implements OnClickListener {
	private Button btn1, btn2;// 加密，解密
	private EditText et1, et2, et3;// 需加密的内容，加密后的内容，解密后的内容

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
	}

	private void initView() {
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);

		et1 = (EditText) findViewById(R.id.et1);
		et2 = (EditText) findViewById(R.id.et2);
		et3 = (EditText) findViewById(R.id.et3);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 加密
		case R.id.btn1:
			String source = et1.getText().toString().trim();
			source="{'aa':'bb','test':'是历代封建开市大吉福克斯的','ssss':'222'}";
			try {
				// 从字符串中得到公钥
				PublicKey publicKey = RSAUtils.loadPublicKey(Const.PUCLIC_KEY);
				// 从文件中得到公钥
				// InputStream inPublic =
				// getResources().getAssets().open("rsa_public_key.pem");
				// PublicKey publicKey = RSAUtils.loadPublicKey(inPublic);
				// 加密
				byte[] encryptByte = RSAUtils.encryptData(source.getBytes(),
						publicKey);
				// 为了方便观察吧加密后的数据用base64加密转一下，要不然看起来是乱码,所以解密是也是要用Base64先转换
				String afterencrypt = Base64Utils.encode(encryptByte);
				et2.setText(afterencrypt);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		// 解密
		case R.id.btn2:
			String encryptContent = et2.getText().toString().trim();
			encryptContent="SivcVCVg00SLzpfCNkA0YvyOqdRxqt0OyEUehoOpL4kY/Hk0zCt1oRwjylflhpq9MmZQwph9EvDU7eyM6tvqIkLUdgyxeOQq4v1z2y4THJegE5TqklkIJa2+3oU6TixwL5KClunT85Wadyb2N+MZlAdqA6rGmJ8Ylv8AGI9q1qKVMh2QSmK+EIhzTkqi09Beo2mR3w893g44py/WF8WZPY0cTSroyaQOV5WauttW2HAdG+HuuNO6cMSofeyOdxmE6Oqa0tTKA1EtuAqiyvXb1yOS9vLN7z7OleOrCWUETOJbAZ62eJflXrsyVc7y5Y7iSFt+KSSztd+pZU2JhrNJCg==";
			try {
				// 从字符串中得到私钥
				PrivateKey privateKey = RSAUtils
						.loadPrivateKey(Const.PRIVATE_KEY);
				// 从文件中得到私钥
				// InputStream inPrivate =
				// getResources().getAssets().open("pkcs8_rsa_private_key.pem");
				// PrivateKey privateKey = RSAUtils.loadPrivateKey(inPrivate);
				// 因为RSA加密后的内容经Base64再加密转换了一下，所以先Base64解密回来再给RSA解密
//				byte[] decryptByte = RSAUtils.decryptData(
//						Base64Utils.decode(encryptContent), privateKey);
				byte[] decryptByte=RSAUtils.decryptData128(Base64Utils.decode(encryptContent),privateKey);
				String decryptStr = new String(decryptByte);
				et3.setText(decryptStr);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}


}
