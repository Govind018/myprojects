package com.technical.quiz.http;

/**
 * Created by M1032185 on 2/4/2016.
 */
public interface NetworkLayer {

    public void parseResponse(String response);

    public void showError(String error);
}
