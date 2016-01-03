package chenanze.com.buanews.network;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by duian on 15/12/21.
 */

public class RestClient {
    private static String TAG = "GetContentList";
    //    private static final String BASE_URL = Config.SERVER_URL+":"+Config.PORT+"/api";
    private static final String BASE_URL = "http://en.wikipedia.org/";

    private static AsyncHttpClient client = new AsyncHttpClient();
    static {
        client.setTimeout(5000);
    }
    public static void get(String url, RequestParams params, JsonHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void getBinaryFile(String url, RequestParams params, BinaryHttpResponseHandler responseHandler) {
        client.get(url, params, responseHandler);
    }

    public static void post(String url, RequestParams params, JsonHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
