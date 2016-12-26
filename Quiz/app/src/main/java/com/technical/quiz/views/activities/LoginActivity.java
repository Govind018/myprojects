package com.technical.quiz.views.activities;

import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.technical.quiz.R;
import com.technical.quiz.gcm.GCMClientManager;
import com.technical.quiz.gcm.RegistrationCompletedHandler;
import com.technical.quiz.http.ApiService;
import com.technical.quiz.http.NetworkLayer;
import com.technical.quiz.utils.LoadingDialog;
import com.technical.quiz.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class LoginActivity extends AppCompatActivity implements NetworkLayer {

    public static final String PROJECT_ID = "495291212746";

    public static String ACTION = "com.technical.quiz.notification";

    EditText editEmail;

    EditText editPwd;

    ScrollView scrollView;

    Button btnLogin;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        scrollView = (ScrollView) findViewById(R.id.login_scroll);
        editEmail = (EditText) findViewById(R.id.edit_email);
        btnLogin = (Button) findViewById(R.id.btn_login);
        editPwd = (EditText) findViewById(R.id.edit_password);

        editEmail.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                ObjectAnimator anims = ObjectAnimator.ofInt(scrollView,
                        "scrollY", btnLogin.getBottom());
                anims.setDuration(800);
                anims.start();
                return false;
            }
        });

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                String message = intent.getStringExtra("message");

                Utils.sendNotify(getApplicationContext(), message);

            }
        }, new IntentFilter(LoginActivity.ACTION));
    }

    @Override
    protected void onStart() {
        super.onStart();

//        GCMClientManager gcmClientManager = new GCMClientManager(this, PROJECT_ID);
//        gcmClientManager.registerIfNeeded(new RegistrationCompletedHandler() {
//            @Override
//            public void onSuccess(String registrationId, boolean isNewRegistration) {
//
//            }
//        });
    }

    public void doLogin(View vIew) {

        String userEmail = editEmail.getText().toString();
        String userPwd = editPwd.getText().toString();
        String auth = "";
        if (!userEmail.isEmpty() && !userPwd.isEmpty()) {

            JSONObject jsonObject = new JSONObject();

            String basicAuth = userEmail + ":" + userPwd;
            byte[] data = new byte[0];
            try {
                data = basicAuth.getBytes("UTF-8");
                auth = Base64.encodeToString(data, Base64.DEFAULT);
                Log.d("QUIZ", "BasicAuthentication" + auth);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

//            try {
//                jsonObject.put("BasicAuthentication",userPwd+":"+userPwd);
//                jsonObject.put("password", userPwd);
//                jsonObject.put("username", userEmail);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setMessage("Please Wait..");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            ApiService.getApiService().doLogin(jsonObject, LoginActivity.this, auth, userEmail);
        } else {
            Toast.makeText(getApplicationContext(), "Please enter credentials.", Toast.LENGTH_LONG).show();
        }

//        LoadingDialog loadingDialog = new LoadingDialog(LoginActivity.this);
//        loadingDialog.showDialog();

        scrollView.scrollTo(0, -200);

//        GCMClientManager gcmClientManager = new GCMClientManager(this, PROJECT_ID);
//        gcmClientManager.registerIfNeeded(new RegistrationCompletedHandler() {
//            @Override
//            public void onSuccess(String registrationId, boolean isNewRegistration) {
//
//                Log.d("RegID",registrationId);
//
//            }
//
//            @Override
//            public void onFailure(String ex) {
//                super.onFailure(ex);
//
//            }
//        });
    }

    public void doRegister(View view) {

        Utils.changeActivity(LoginActivity.this, new Intent(getApplicationContext(), SignUpActivity.class));

    }

    @Override
    public void parseResponse(String response) {
        progressDialog.cancel();
        startActivity(new Intent(getApplicationContext(), HomeContainerActivity.class));
    }

    @Override
    public void showError(String error) {

        progressDialog.cancel();
        Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_LONG).show();
    }

    class DoneOnEditorActionListener implements TextView.OnEditorActionListener {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return true;
            }
            return false;
        }
    }
}
