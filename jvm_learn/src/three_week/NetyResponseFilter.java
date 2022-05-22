package three_week;

import io.netty.handler.codec.http.FullHttpResponse;

public interface NetyResponseFilter {

    void filter(FullHttpResponse response);
}
