package two_week;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OKHttpWork {

    private static  OkHttpClient httpClient = new OkHttpClient();

    public static void main(String[] args) {
        String url = "http://localhost:8801";
        Request request = new Request.Builder().get().url(url).build();
        try {
            Response response = httpClient.newCall(request).execute();
            String resonData = response.body().string();
            System.out.println(resonData);
            httpClient = null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpClient = null;
        }
    }
}
