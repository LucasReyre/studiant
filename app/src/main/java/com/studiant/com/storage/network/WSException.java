package com.studiant.com.storage.network;

import org.json.JSONException;
import org.json.JSONObject;


public class WSException extends Exception {
    private int httpCode;
    private String message;

    public WSException(String jsonBody){
        try {
            JSONObject jsonError = new JSONObject(jsonBody);
            jsonError = jsonError.getJSONObject("error");
            this.httpCode = Integer.parseInt(jsonError.getString("statusCode"));
            this.message = jsonError.getString("message");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
