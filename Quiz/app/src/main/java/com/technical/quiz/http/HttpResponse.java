package com.technical.quiz.http;

import java.net.URI;

/**
 * Created by M1032185 on 2/3/2016.
 */
public class HttpResponse {

    private String method;

    private URI uri;

    private String responseBody;

    private int statusCode;

    private String statusLine;

    private String postRequestBody;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusLine() {
        return statusLine;
    }

    public void setStatusLine(String statusLine) {
        this.statusLine = statusLine;
    }

    public String getPostRequestBody() {
        return postRequestBody;
    }

    public void setPostRequestBody(String postRequestBody) {
        this.postRequestBody = postRequestBody;
    }

}
