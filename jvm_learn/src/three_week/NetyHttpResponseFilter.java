package three_week;

import io.netty.handler.codec.http.FullHttpResponse;

public class NetyHttpResponseFilter implements NetyResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("xjava_key", "luyue");
    }
}
