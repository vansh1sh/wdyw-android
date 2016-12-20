package app.com.example.vansh.wdyw.rest;

import java.io.IOException;

import app.com.example.vansh.wdyw.utility.UtilityMethods;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        // Request customization: add request headers
        Request.Builder requestBuilder = original.newBuilder()
                .header("Cache-Control", (UtilityMethods.isNetworkAvailable()) ?
                        "public, max-age=60" :  "public, only-if-cached, max-stale=604800"); // <-- this is the important line

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}