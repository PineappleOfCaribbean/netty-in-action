package com.nio.netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    private int counter;

    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println("开始");
        ByteBuf buf = (ByteBuf) msg;

        System.out.println("原始1");
        String strorg = buf.toString(CharsetUtil.UTF_8);
        System.out.println(strorg);
        System.out.println("原始2");
        String afterStr = strorg.substring(0,strorg.lastIndexOf(System.getProperty("line.separator")));
        System.out.println(afterStr);

        byte[] req = afterStr.getBytes();
        System.out.println("原始3");

//        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);

//        String body = new String(req, "UTF-8").substring(0, req.length - System.getProperty("line.separator").length());

        System.out.println("The time server receive order : " + new String(req)  + " ; the counter is : " + ++counter);

//        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new java.util.Date(System.currentTimeMillis()).toString() : "BAD ORDER";
//        currentTime = currentTime + System.getProperty("line.separator");
//        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
//        ctx.writeAndFlush(resp);
        System.out.println("结束");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}