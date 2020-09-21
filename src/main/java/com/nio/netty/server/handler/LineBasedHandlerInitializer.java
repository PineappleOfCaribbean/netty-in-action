package com.nio.netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.util.CharsetUtil;

import java.util.concurrent.atomic.AtomicInteger;

public class LineBasedHandlerInitializer extends ChannelInitializer<Channel> {

    private static volatile AtomicInteger atomicInteger = new AtomicInteger();
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new LineBasedFrameDecoder(64 * 1024));
        pipeline.addLast(new FrameHandler());
    }

    public static final class FrameHandler
            extends SimpleChannelInboundHandler<ByteBuf> {
        @Override
        public void channelRead0(ChannelHandlerContext ctx,
                                 ByteBuf msg) throws Exception {
            System.out.println("atomicInteger：" + atomicInteger.addAndGet(1));
            ByteBuf in = (ByteBuf) msg;
            String str = in.toString(CharsetUtil.UTF_8);
            if (str.startsWith("50")){
                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAA：");
            }
            System.out.println("收到："+in.toString(CharsetUtil.UTF_8) );
        }
    }
}