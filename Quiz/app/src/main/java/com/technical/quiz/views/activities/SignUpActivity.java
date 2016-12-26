package com.technical.quiz.views.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.technical.quiz.R;
import com.technical.quiz.http.ApiService;
import com.technical.quiz.http.NetworkLayer;
import com.technical.quiz.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity implements NetworkLayer {


    EditText editName;

    EditText editEmail;

    EditText editMob;

    EditText editPwd;

    EditText editCompany;

    EditText editExp;

    EditText editCollege;

    EditText editSubject;

    EditText editDomain;

    EditText editDepart;

    EditText editDesg;

    Spinner occuSpinner;

    TextView textComp;

    TextView textDesg;

    TextView textDept;

    TextView textDomain;

    TextView textExp;

    TextView textCollege;

    TextView textSubject;

    private boolean isCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.signup_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initUI();

//        if(Utils.isUserSignUp(getApplicationContext())){
//            Utils.changeActivity(getApplicationContext(),new Intent(getApplicationContext(),LoginActivity.class));
//            finish();
//        }else{
//
//        }
    }

    private void initUI() {

        editName = (EditText) findViewById(R.id.edit_name);
        editEmail = (EditText) findViewById(R.id.edit_email);
        editMob = (EditText) findViewById(R.id.edit_mob);
        editPwd = (EditText) findViewById(R.id.edit_password);
        editCompany = (EditText) findViewById(R.id.edit_company);
        editExp = (EditText) findViewById(R.id.edit_exp);
        editCollege = (EditText) findViewById(R.id.edit_college);
        editSubject = (EditText) findViewById(R.id.edit_sub);
        occuSpinner = (Spinner) findViewById(R.id.spinner_occ);
        occuSpinner.setOnItemSelectedListener(onItemSelectedListener);
        textComp = (TextView) findViewById(R.id.text_comp);
        textExp = (TextView) findViewById(R.id.text_exp);
        textCollege = (TextView) findViewById(R.id.text_college);
        textSubject = (TextView) findViewById(R.id.text_sub);
        textDesg = (TextView) findViewById(R.id.text_designation);
        textDept = (TextView) findViewById(R.id.text_department);
        textDomain = (TextView) findViewById(R.id.text_domain);
        editDomain = (EditText) findViewById(R.id.edit_domain);
        editDepart = (EditText) findViewById(R.id.edit_department);
        editDesg = (EditText) findViewById(R.id.edit_designation);

    }

    AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            if (position == 0) {
                editCompany.setVisibility(View.VISIBLE);
                editExp.setVisibility(View.VISIBLE);
                editDesg.setVisibility(View.VISIBLE);
                editDomain.setVisibility(View.VISIBLE);
                editDepart.setVisibility(View.VISIBLE);
                editCollege.setVisibility(View.GONE);
                editSubject.setVisibility(View.GONE);

                textComp.setVisibility(View.VISIBLE);
                textExp.setVisibility(View.VISIBLE);
                textDesg.setVisibility(View.VISIBLE);
                textDept.setVisibility(View.VISIBLE);
                textDomain.setVisibility(View.VISIBLE);
                textCollege.setVisibility(View.GONE);
                textSubject.setVisibility(View.GONE);

                isCompany = true;

            } else {

                editCompany.setVisibility(View.GONE);
                editExp.setVisibility(View.GONE);
                editDesg.setVisibility(View.GONE);
                editDomain.setVisibility(View.GONE);
                editDepart.setVisibility(View.GONE);
                editCollege.setVisibility(View.VISIBLE);
                editSubject.setVisibility(View.VISIBLE);

                textComp.setVisibility(View.GONE);
                textExp.setVisibility(View.GONE);
                textDesg.setVisibility(View.GONE);
                textDept.setVisibility(View.GONE);
                textDomain.setVisibility(View.GONE);
                textCollege.setVisibility(View.VISIBLE);
                textSubject.setVisibility(View.VISIBLE);

                isCompany = false;

            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


    public void doNext(View view) {
        Utils.changeActivity(SignUpActivity.this, new Intent(getApplicationContext(), CategoryActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }

        return super.onOptionsItemSelected(item);

    }

    public void doSignUp(View view) {

        String userName = editName.getText().toString();
        String userEmail = editEmail.getText().toString();
        String userMob = editMob.getText().toString();
        String userPwd = editPwd.getText().toString();
        String userDomain = editDomain.getText().toString();
        String userDept = editDepart.getText().toString();
        String userDesg = editDesg.getText().toString();
        String userComp = editCompany.getText().toString();
        String userExp = editExp.getText().toString();

//        startActivity(new Intent(getApplicationContext(), CategoryActivity.class));

        if (!userName.isEmpty() && !userEmail.isEmpty() && !userMob.isEmpty() && !userPwd.isEmpty()) {

            if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
                Toast.makeText(getApplicationContext(), "Please provide valid email adress.", Toast.LENGTH_LONG).show();
                return;
            }

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("name", userName);
                jsonObject.put("email", userEmail);
                jsonObject.put("mobile", userMob);
                jsonObject.put("password", userPwd);
                jsonObject.put("gcmId", Utils.getFromPrefrencesBoolean(getApplicationContext(), "gcmId"));


                JSONObject companyOrCollege = new JSONObject();
                String occupation;
                if (isCompany) {
                    companyOrCollege.put("name", userComp);
                    companyOrCollege.put("domain", userDomain);
                    companyOrCollege.put("department", userDept);
                    companyOrCollege.put("designation", userDesg);
                    companyOrCollege.put("experience", userExp);
                    occupation = "company";
                } else {
                    companyOrCollege.put("name", "test");
                    occupation = "college";
                }

                jsonObject.put(occupation, companyOrCollege);

                ApiService.getApiService().doSignUp(getApplicationContext(), jsonObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {

        }
    }

    @Override
    public void parseResponse(String response) {


    }

    @Override
    public void showError(String error) {

    }
}
