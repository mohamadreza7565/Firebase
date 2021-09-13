package com.ryfa.firebase.api;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.ryfa.firebase.general.JsonReader;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.ContentType;
import cz.msebera.android.httpclient.message.BasicHeader;



public class QueryBuilder {
    Context context;
    public static final int FROM_ASSETS = 0, FROM_SERVER = 1;
    public static final String BASE_URL = "http://engineerit93.ir/Firebase/";
    private RequestParams params;
    private int loadingDuration = 1000;
    private String[][] header;
    public static final int POST = 1, GET = 2;


    public QueryBuilder() {

    }

    public RequestParams getParams() {
        return params;
    }

    public QueryBuilder setParams(RequestParams params) {
        this.params = params;
        return this;
    }

    public String[][] getHeader() {
        return header;
    }

    public QueryBuilder setHeader(String[][] header) {
        this.header = header;
        return this;
    }

    public Context getContext() {
        return context;
    }

    public QueryBuilder setContext(Context context) {
        this.context = context;
        return this;
    }

    /**
     * @param requestUrl
     * @param api
     * @param findListCallback
     * @return fromServer
     */

    public QueryBuilder findInBackground(String requestUrl, String api, final FindCallback findListCallback) {
        AsyncHttpClient client = new AsyncHttpClient();
        if (header != null) {
            for (int i = 0; i < getHeader().length; i++) {
                client.addHeader(getHeader()[i][0], getHeader()[i][1]);
            }
        }
        client.post(String.format("%s%s", requestUrl, api), getParams(), new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String result, Throwable throwable) {
                findListCallback.done(new ServerResult(), statusCode);
                findListCallback.done(result, statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String json) {
                ServerResult serverResult = new Gson().fromJson(json, ServerResult.class);
                findListCallback.done(serverResult, statusCode);
                findListCallback.done(json, statusCode);
            }
        });
        return this;

    }

    public QueryBuilder findInBackground(int requestType, String requestUrl, String api, final FindCallback findListCallback) {
        AsyncHttpClient client = new AsyncHttpClient();
        if (header != null) {
            for (int i = 0; i < getHeader().length; i++) {
                client.addHeader(getHeader()[i][0], getHeader()[i][1]);
            }
        }
        if (requestType == POST) {
            client.post(String.format("%s%s", requestUrl, api), getParams(), new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String result, Throwable throwable) {
                    findListCallback.done(new ServerResult(), statusCode);
                    findListCallback.done(result, statusCode);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String json) {
                    ServerResult serverResult = new Gson().fromJson(json, ServerResult.class);
                    findListCallback.done(serverResult, statusCode);
                    findListCallback.done(json, statusCode);
                }
            });

        } else {
            client.get(String.format("%s%s", requestUrl, api), getParams(), new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String result, Throwable throwable) {
                    findListCallback.done(new ServerResult(), statusCode);
                    findListCallback.done(result, statusCode);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String json) {
                    ServerResult serverResult = new Gson().fromJson(json, ServerResult.class);
                    findListCallback.done(serverResult, statusCode);
                    findListCallback.done(json, statusCode);
                }
            });

        }
        return this;

    }

    /**
     * @param api
     * @param findListCallback
     * @return fromServer
     */

    public QueryBuilder findInBackground(String api, final FindCallback findListCallback) {

        AsyncHttpClient client = new AsyncHttpClient();
        if (header != null) {
            for (int i = 0; i < getHeader().length; i++) {
                client.addHeader(getHeader()[i][0], getHeader()[i][1]);
            }
            //client.addHeader(getHeader()[1][0], getHeader()[1][1]);
        }
        //client.setEnableRedirects(true);
        client.post(BASE_URL + api, getParams(), new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String result, Throwable throwable) {
                Log.e("SERVER_RESULT: ", result + "");
                findListCallback.done(new ServerResult(false, "خطا در دریافت اطلاعات از سرور"), statusCode);
                findListCallback.done(result, statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String json) {
                try {
                    ServerResult serverResult = new Gson().fromJson(json, ServerResult.class);
                    findListCallback.done(serverResult, statusCode);
                    findListCallback.done(json, statusCode);
                    Log.e("SERVER_RESULT: ", json + "");
                } catch (Exception e) {
                    Log.e("SERVER_RESULT", json);
                    findListCallback.done(new ServerResult(false, "خطا در فرمت اطلاعات سرور"), statusCode);
                    findListCallback.done(json, statusCode);
                    Log.e("SERVER_RESULT: ", e.getMessage());
                }
            }
        });

        return this;

    }

    public QueryBuilder findInBackground(int requestType, String api, final FindCallback findListCallback) {

        AsyncHttpClient client = new AsyncHttpClient();
        if (header != null) {
//            for (int i = 0; i < getHeader().length; i++) {
//                client.addHeader(getHeader()[i][0], getHeader()[i][1]);
//            }
            client.addHeader(getHeader()[1][0], getHeader()[1][1]);
        }
        if (requestType == POST) {
            client.post(BASE_URL + api, getParams(), new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String result, Throwable throwable) {
                    Log.e("SERVER_RESULT: ", result + "");
                    findListCallback.done(new ServerResult(false, "خطا در دریافت اطلاعات از سرور"), statusCode);
                    findListCallback.done(result, statusCode);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String json) {
                    try {
                        ServerResult serverResult = new Gson().fromJson(json, ServerResult.class);
                        findListCallback.done(serverResult, statusCode);
                        findListCallback.done(json, statusCode);
                        Log.e("SERVER_RESULT: ", json + "");
                    } catch (Exception e) {
                        Log.e("SERVER_RESULT", json);
                        findListCallback.done(new ServerResult(false, "خطا در فرمت اطلاعات سرور"), statusCode);
                        findListCallback.done(json, statusCode);
                        Log.e("SERVER_RESULT: ", e.getMessage());
                    }
                }
            });

        }
        if (requestType == GET) {
            client.get(BASE_URL + api, getParams(), new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String result, Throwable throwable) {
                    Log.e("SERVER_RESULT: ", result + "");
                    findListCallback.done(new ServerResult(false, "خطا در دریافت اطلاعات از سرور"), statusCode);
                    findListCallback.done(result, statusCode);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String json) {
                    try {
                        ServerResult serverResult = new Gson().fromJson(json, ServerResult.class);
                        findListCallback.done(serverResult, statusCode);
                        findListCallback.done(json, statusCode);
                        Log.e("SERVER_RESULT: ", json + "");
                    } catch (Exception e) {
                        Log.e("SERVER_RESULT", json);
                        findListCallback.done(new ServerResult(false, "خطا در فرمت اطلاعات سرور"), statusCode);
                        findListCallback.done(json, statusCode);
                        Log.e("SERVER_RESULT: ", e.getMessage());
                    }
                }
            });

        }

        return this;

    }

    /**
     * @param context
     * @param fileName   is json file name from assets/json/ directory
     * @param hasLoading
     * @return
     */

    public QueryBuilder findInBackground(final Context context, final String folder, final String fileName, boolean hasLoading, final FindCallback findListCallback) {
        if (hasLoading) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    String json = JsonReader.fromAssets(context, folder + "/" + fileName);
                    ServerResult serverResult = new Gson().fromJson(json, ServerResult.class);
                    findListCallback.done(serverResult, 200);
                    findListCallback.done(json, 200);
                }
            }, getLoadingDuration());
        } else {
            String json = JsonReader.fromAssets(context, folder + "/" + fileName);
            ServerResult serverResult = new Gson().fromJson(json, ServerResult.class);
            findListCallback.done(serverResult, 200);
            findListCallback.done(json, 200);
        }
        return this;
    }

    public QueryBuilder findInBackground(final Context context, String folder, final String fileName, final FindCallback findListCallback) {
        String json = JsonReader.fromAssets(context, folder + "/" + fileName);
        findListCallback.done(json, 200);
        findListCallback.done(new ServerResult(), 200);
        return this;
    }

    public QueryBuilder findInBackground(Context context, HttpEntity entity, String api, final FindCallback findCallback) {

        AsyncHttpClient client = new AsyncHttpClient();

        if (header != null) {
            for (int i = 0; i < getHeader().length; i++) {
                client.addHeader(getHeader()[i][0], getHeader()[i][1]);
            }
        }

        client.post(context, String.format("%s%s", BASE_URL, api), entity, "application/json", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String result, Throwable throwable) {
                Log.e("SERVER_RESULT: ", result + "");
                findCallback.done(new ServerResult(false, "خطا در دریافت اطلاعات از سرور"), statusCode);
                findCallback.done(result, statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String json) {
                try {
                    ServerResult serverResult = new Gson().fromJson(json, ServerResult.class);
                    findCallback.done(serverResult, statusCode);
                    findCallback.done(json, statusCode);
                    Log.e("SERVER_RESULT: ", json + "");
                } catch (Exception e) {
                    Log.e("SERVER_RESULT", json);
                    findCallback.done(new ServerResult(false, "خطا در فرمت اطلاعات سرور"), statusCode);
                    findCallback.done(json, statusCode);
                    Log.e("SERVER_RESULT: ", e.getMessage());
                }
            }
        });
        return this;
    }

    public QueryBuilder setLoadingDuration(int loadingDuration) {
        this.loadingDuration = loadingDuration;
        return this;
    }

    public int getLoadingDuration() {
        return loadingDuration;
    }

    public interface FindCallback {
        void done(ServerResult serverResult, int statusCode);

        void done(String result, int statusCode);
    }

}
