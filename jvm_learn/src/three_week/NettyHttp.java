package three_week;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Netty的服务端
 */
public class NettyHttp {
    private int port;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workGroup;

    public NettyHttp(int port) {
        this.port = port;
        this.bossGroup = new NioEventLoopGroup(2);
        this.workGroup = new NioEventLoopGroup(16);
    }

    public void start() throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.SO_REUSEADDR, true)
                .childOption(ChannelOption.SO_RCVBUF, 32 * 1024)
                .childOption(ChannelOption.SO_SNDBUF, 32 * 1024)
                .childOption(EpollChannelOption.SO_REUSEPORT, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        bootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new NettyInitializer());
        ChannelFuture sync = bootstrap.bind(port).sync();
        if (sync.isSuccess()){
            System.out.println("启动成功");
        }else{
            System.out.println("启动失败");
        }
        sync.channel().closeFuture().sync();
    }

    public static void main(String[] args) throws Exception {
        int port = 8808;
        new NettyHttp(port).start();// 创建一个Netty服务并使用8808端口，然后启动
    }
}
