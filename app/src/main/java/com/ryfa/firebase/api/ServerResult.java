package com.ryfa.firebase.api;

import com.google.gson.JsonElement;

public class ServerResult {

    private int status;
    private boolean result;
    private String message;

    public ServerResult() {
    }

    public ServerResult(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public ServerResult setStatus(int status) {
        this.status = status;
        return this;
    }

    public boolean isResult() {
        return result;
    }

    public ServerResult setResult(boolean result) {
        this.result = result;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ServerResult setMessage(String message) {
        this.message = message;
        return this;
    }

}
