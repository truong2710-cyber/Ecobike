package ecobike.subsystems.barcode_subsystem;

import okhttp3.*;
import java.util.concurrent.TimeUnit;

/**
 * Create connection to barcode converter API
 */
public class HttpConnector {
    private final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10000, TimeUnit.MILLISECONDS)
            .readTimeout(10000,TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(true)
            .build() ;

    /**
     * Create request
     * @param url : API url
     * @param body : request body
     * @return response
     */
    public String post(String url , String body) {
        try {
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), body);
            Request request = new Request.Builder().url(url)
                    .post(requestBody).build();
            try (Response response = client.newCall(request).execute()) {
                assert response.body() != null;
                return response.body().string();
            }
        } catch (Exception e) {
            System.out.println("Lỗi rồi sếp ơi! (method: post - barcode)");
        }
        return null;
    }
}