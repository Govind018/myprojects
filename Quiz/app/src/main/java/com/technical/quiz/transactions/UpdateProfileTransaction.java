package com.technical.quiz.transactions;

import android.content.Context;

import com.technical.quiz.http.PutTransaction;

import org.json.JSONObject;

import java.net.URI;

/**
 * Created by Govind on 10/2/2016.
 */

public class UpdateProfileTransaction extends PutTransaction {

    private String authKey;

    public UpdateProfileTransaction(JSONObject jsonObject, Context context,String authKey) {
        super(jsonObject, context);

        this.authKey = authKey;
    }

    @Override
    protected JSONObject setupRequestBody() {
        return null;
    }

    @Override
    protected String getUri() {
        return null;
    }

    @Override
    protected URI getRequestUri() {
        return null;
    }

    @Override
    protected String getHeader() {
        return null;
    }
}
