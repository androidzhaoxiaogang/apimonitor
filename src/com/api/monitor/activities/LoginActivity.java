package com.api.monitor.activities;

import java.util.HashMap;

import com.api.monitor.adapters.TextWatcherAdapter;
import com.api.monitor.pojo.UserInfo;
import com.api.monitor.utils.Constants;
import com.api.monitor.utils.Prefs;
import com.api.monitor.utils.Toaster;
import com.api.monitor.R;

import fast.rocket.Rocket;
import fast.rocket.config.JsonCallback;
import fast.rocket.error.RocketError;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
	private static final String TAG = "LoginActivity";
	private static final String EMPTY = "";
	private ProgressDialog progressDialog;
	private AutoCompleteTextView emailText;
	private EditText passwordText;
	private Button signinButton;

	private TextWatcher watcher = validationTextWatcher();
	private SharedPreferences sharedPref;
	
	private String email;
    private String password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);

		setupViews();
	}

	private void setupViews() {
		sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
		emailText = (AutoCompleteTextView) findViewById(R.id.et_email);
		passwordText = (EditText) findViewById(R.id.et_password);
		signinButton = (Button) findViewById(R.id.b_signin);

		final String userName = sharedPref.getString(Constants.USERNAME, EMPTY);
		final String userPass = sharedPref.getString(Constants.PASSWORD, EMPTY);
		// emailText.setAdapter(new ArrayAdapter<String>(this,
		// android.R.layout.simple_dropdown_item_1line, ));


		signinButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				handleLogin();
			}
		});

		emailText.addTextChangedListener(watcher);
		passwordText.addTextChangedListener(watcher);
		
		if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(userPass)) {
			emailText.setText(userName);
			passwordText.setText(userPass);
		}
	}

	private TextWatcher validationTextWatcher() {
		return new TextWatcherAdapter() {
			public void afterTextChanged(Editable gitDirEditText) {
				updateUIWithValidation();
			}
		};
	}

	private void updateUIWithValidation() {
		boolean populated = populated(emailText) && populated(passwordText);
		signinButton.setEnabled(populated);
	}

	private boolean populated(EditText editText) {
		return editText.length() > 0;
	}

	private void showProgressDialog() {
		final ProgressDialog dialog = new ProgressDialog(this);
		dialog.setMessage(getText(R.string.message_signing_in));
		dialog.setIndeterminate(true);
		dialog.setCancelable(true);
		dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
			public void onCancel(DialogInterface dialog) {
				Rocket.getDefault(LoginActivity.this).cancelAll(TAG);
			}
		});
		dialog.show();
		progressDialog = dialog;
	}

	private void dissmissProgressDialog() {
		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
		}
	}

	private void handleLogin() {
		showProgressDialog();
		
		email = emailText.getText().toString();
        password = passwordText.getText().toString();

		final HashMap<String, String> params = new HashMap<String, String>();
		params.put("method", "onLogin");
		params.put("account", email);
		params.put("passWord", password);

		Rocket.with(this).enableCookie(true)
				.requestParams(params)
				.requestTag(TAG)
				.targetType(UserInfo.class)
				.invoke(callback)
				.load(Constants.loginInfoUrl);
	}
	
	private JsonCallback<UserInfo> callback = new JsonCallback<UserInfo>() {

		@Override
		public void onCompleted(RocketError error, UserInfo result) {
			dissmissProgressDialog();
			if (error == null) {
				if (result != null && result.getResult()) {
					Prefs.setStrValue(sharedPref.edit(), Constants.USERNAME, email);
					Prefs.setStrValue(sharedPref.edit(), Constants.PASSWORD, password);
				}
				
				startActivity(new Intent(LoginActivity.this, MainActivity.class));
				finish();
			} else {
				Toaster.errroMessage(error, LoginActivity.this);
			}
		}
	};
}
