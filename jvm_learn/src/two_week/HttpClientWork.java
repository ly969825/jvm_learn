package two_week;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 使用HttpClint访问http：/localhost:8081
 */
public class HttpClientWork {

    public static void main(String[] args) {
        String url = "http://localhost:8801";
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        String toStringResult = null;
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                "Chrome/96.0.4664.45 Safari/537.36");
        CloseableHttpResponse response = null;
        try {
            response = closeableHttpClient.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            if (HttpStatus.SC_OK == statusLine.getStatusCode()) {
                //获取响应实体
                HttpEntity entity = response.getEntity();
                //对HttpEntity操作的工具类,获取返回的数据
                toStringResult = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                System.out.println(toStringResult);
                //确保流关闭
                EntityUtils.consume(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } if (closeableHttpClient != null) {
            try {
                closeableHttpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (response != null) {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
