package ecobike.subsystems.interbank_subsystem;

import okhttp3.*;

import java.util.concurrent.TimeUnit;

//@Slf4j
public class HttpConnector {
    private final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10000, TimeUnit.MILLISECONDS)
            .readTimeout(10000,TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(true)
            .build() ;

    public String sendPatch(String url , String body) {
        try {
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), body);
            Request request = new Request.Builder().url(url)
                    .patch(requestBody).build();
            try (Response response = client.newCall(request).execute()) {
                assert response.body() != null;
                return response.body().string();
            }
        } catch (Exception e) {
            System.out.println("Can not send patch!");
        }
        return null;
    }
}
