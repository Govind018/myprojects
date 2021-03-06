package com.technical.quiz.http;

import android.content.Context;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;

/**
 * Created by Govind on 10/2/2016.
 */

public abstract class PutTransaction extends Transaction {

    protected JSONObject mRequestBody;

    URI mUri;

    Context mContext;

    public PutTransaction(JSONObject jsonObject, Context context) {
        super(context);
        mRequestBody = new JSONObject();
        mContext = context;
    }

    @Override
    public void initializeExecution() throws Exception {
        super.initializeExecution();

        mRequestBody = setupRequestBody();
    }

    protected abstract JSONObject setupRequestBody();

    @Override
    protected HttpResponse sendRequest() throws IOException {
        return mRestMethod.sendPostRequest(mUri, getRequestBody());
    }

    @Override
    protected String getRequestBody() {
        if (mRequestBody != null) {
            return mRequestBody.toString();
        }

        return null;
    }

    @Override
    protected void setupRequestUri() {
        // Construct base URL
        mUri = URI.create(getUri() + getUrlPrefix());
    }
}
