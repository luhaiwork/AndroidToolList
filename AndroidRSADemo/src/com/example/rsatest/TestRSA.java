package com.example.rsatest;
import java.security.PrivateKey;
import java.security.PublicKey;

import com.example.rsatest.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class TestRSA extends Activity implements OnClickListener
{
	private Button btn1, btn2;// 加密，解密
	private EditText et1, et2, et3;// 需加密的内容，加密后的内容，解密后的内容

	/* 密钥内容 base64 code */
	private static String PUCLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDSWQZr2XL8YRmxpL8VV9PfAUXcs"
			+ "XZe5Ux5ORDAXOtMWJ5MaIwbWUwhTVybdOXpkItziQwfOToE2PdiJSmiq+dUShM/E3PzWJReMu556DwjzKmlLpnRKgSBSk"
			+ "yHjyhrMDveoUPFSb1KZ4fSrHS8/JOVV0/uESUdc5cktoMR5B1ndQIDAQAB";
	private static String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANJZBmvZcvxhGbGkvx"
			+ "VX098BRdyxdl7lTHk5EMBc60xYnkxojBtZTCFNXJt05emQi3OJDB85OgTY92IlKaKr51RKEz8Tc/NYlF4y7nnoPCPMqaUu"
			+ "mdEqBIFKTIePKGswO96hQ8VJvUpnh9KsdLz8k5VXT+4RJR1zlyS2gxHkHWd1AgMBAAECgYBOO+xGepboxVigGK01S/gyTi"
			+ "/kuGBVRSVfRNk1IpolSCSAOLY7icfl7vcNnrnQWAF11Dgc+NTabrP73eSRayxbIUmSMi4v2ipAvsjvrAPjfmY8desrwi3y"
			+ "P5X43pOR2qG+7QmvmpmgzYT2l8eH3Ly1qKUDGCjWR9b9I1liEYCogQJBAPcGkz55XAQOu33qPpTMuWDR+/HwoaY+3m/Eyl"
			+ "XTZwknNGurt1Ldmoakhp1sjZ9pWXwqga2xAkZKqriH28QvZWECQQDZ/VUYCKH53485dQulENWzsXxY7VnDS2x/TXoF1Vpg"
			+ "aG1u68jlgAaDN8yvsusgPn01s+dnOYSrCFi8yVHiWyaVAkBB8XBnMhB9SM2GXHzeThSCoyult33/mjLZFWiKJsgPqnaU85"
			+ "8ZsOyqWstQxQS9dGsd+V7q1iDsUMxoPGRPZ/OhAkBycQMJeQ7ARdPFUGNqpynqCpXDgegMFT7CnoQfJ+Eol+pLv1Fa2xmQ"
			+ "Le0xmHbEGMpXNTmZAaNavykHA5IPidb5AkEAkp6WoUOiAGJKUap7LgPEjgicJ7DSH9BaNbM8Pl/Zk95/HQyW0fwWVELKjR"
			+ "nqYjlAZa476EF7xL5sfAxTlVRtfQ==";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
	}

	private void initView()
	{
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);

		et1 = (EditText) findViewById(R.id.et1);
		et2 = (EditText) findViewById(R.id.et2);
		et3 = (EditText) findViewById(R.id.et3);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		// 加密
		case R.id.btn1:
			String source = et1.getText().toString().trim();
			try
			{
				// 从字符串中得到公钥
				 PublicKey publicKey = RSAUtils.loadPublicKey(PUCLIC_KEY);
				// 从文件中得到公钥
//				InputStream inPublic = getResources().getAssets().open("rsa_public_key.pem");
//				PublicKey publicKey = RSAUtils.loadPublicKey(inPublic);
				// 加密
				byte[] encryptByte = RSAUtils.encryptData(source.getBytes(), publicKey);
				// 为了方便观察吧加密后的数据用base64加密转一下，要不然看起来是乱码,所以解密是也是要用Base64先转换
				String afterencrypt = Base64Utils.encode(encryptByte);
				et2.setText(afterencrypt);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			break;
		// 解密
		case R.id.btn2:
			String encryptContent = et2.getText().toString().trim();
			try
			{
				// 从字符串中得到私钥
				 PrivateKey privateKey = RSAUtils.loadPrivateKey(PRIVATE_KEY);
				// 从文件中得到私钥
//				InputStream inPrivate = getResources().getAssets().open("pkcs8_rsa_private_key.pem");
//				PrivateKey privateKey = RSAUtils.loadPrivateKey(inPrivate);
				// 因为RSA加密后的内容经Base64再加密转换了一下，所以先Base64解密回来再给RSA解密
				byte[] decryptByte = RSAUtils.decryptData(Base64Utils.decode(encryptContent), privateKey);
				String decryptStr = new String(decryptByte);
				et3.setText(decryptStr);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

}

