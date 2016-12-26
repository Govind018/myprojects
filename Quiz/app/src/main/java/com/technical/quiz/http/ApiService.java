package com.technical.quiz.http;

import android.content.Context;

import com.technical.quiz.transactions.LoginTransaction;
import com.technical.quiz.transactions.SignUpTransaction;
import com.technical.quiz.transactions.SubscribeTransaction;
import com.technical.quiz.utils.Utils;

import org.json.JSONObject;

public class ApiService {

    public static ApiService apiService = new ApiService();

    private ApiService() {

    }

    public static ApiService getApiService() {
        return apiService;
    }


    public void doLogin(JSONObject jsonObject, Context context, String auth,String emailId) {

        LoginTransaction loginTransaction = new LoginTransaction(jsonObject, context, auth,emailId);
        TransactionProcessor transactionProcessor = new TransactionProcessor(context);
        transactionProcessor.execute(loginTransaction);

    }

    public void doSignUp(Context context, JSONObject jsonObject) {

        SignUpTransaction signUpTransaction = new SignUpTransaction(jsonObject, context);
        TransactionProcessor transactionProcessor = new TransactionProcessor(context);
        transactionProcessor.execute(signUpTransaction);
    }

    public void doSubscribeCategory(Context context, JSONObject jsonObject) {

        SubscribeTransaction subscribeTransaction = new SubscribeTransaction(jsonObject, context);
        TransactionProcessor transactionProcessor = new TransactionProcessor(context);
        transactionProcessor.execute(subscribeTransaction);
    }
}
