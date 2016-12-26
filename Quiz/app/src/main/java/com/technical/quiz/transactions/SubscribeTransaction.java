package com.technical.quiz.transactions;

import android.content.Context;

import com.technical.quiz.BuildConfig;
import com.technical.quiz.http.PostTransaction;
import com.technical.quiz.utils.AppConstants;

import org.json.JSONObject;

import java.net.URI;

/**
 * Created by M1032185 on 8/15/2016.
 */
public class SubscribeTransaction extends PostTransaction {

    JSONObject jsonObject;

    public SubscribeTransaction(JSONObject jsonObject, Context context) {
        super(jsonObject, context);
        this.jsonObject = jsonObject;
    }

    @Override
    protected JSONObject setupRequestBody() {
        return jsonObject;
    }

    @Override
    protected String getUri() {
        return BuildConfig.BASE_URL;
    }

    @Override
    protected URI getRequestUri() {
        return null;
    }

    @Override
    protected String getHeader() {
        return null;
    }

    @Override
    protected String getUrlPrefix() {
        return AppConstants.SUBSCRIBE_CATEGORY;
    }
}

