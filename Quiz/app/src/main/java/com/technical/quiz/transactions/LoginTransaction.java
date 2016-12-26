package com.technical.quiz.transactions;

import android.content.Context;

import com.technical.quiz.BuildConfig;
import com.technical.quiz.http.GetTransaction;
import com.technical.quiz.utils.AppConstants;

import org.json.JSONObject;

import java.net.URI;

/**
 * Created by M1032185 on 8/12/2016.
 */
public class LoginTransaction extends GetTransaction {

    String basicAuth;

    String emailId;
    public LoginTransaction(JSONObject jsonObject, Context context, String auth,String emailId) {
        super(jsonObject, context);

        basicAuth = auth;
        this.emailId = emailId;
    }

    @Override
    protected JSONObject setupRequestBody() {
        return null;
    }

    @Override
    protected String getUri() {
        return BuildConfig.BASE_URL + AppConstants.USER_LOGIN + "/" + emailId;
    }

    @Override
    protected URI getRequestUri() {
        return null;
    }

    @Override
    protected String getHeader() {
        return basicAuth;
    }
}
