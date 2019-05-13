package com.example.chatroom.controller;

import com.example.chatroom.service.ChatRecordService;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 *
 * @author liq
 * @date 2019/5/7
 */

@Controller
@ServerEndpoint("/websocket/{param}")
@Component
public class WebSocketServer {



    public static ChatRecordService recordService;

    /**
     * onlineCount
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static int onlineCount = 0;

    /**
     * webSocketSet
     * concurrent包的线程安全Set，用来存放每个客户端对应的ProductWebSocket对象。
     */
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

    /**
     * nickName
     * 用户姓名
     */
    private String nickName;

    /**
     * session
     * 与客户端的连接回话，用于通信
     */
    private Session session;

    /**
     * log
     * 日志记录
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(WebSocketServer.class);



    public static CopyOnWriteArraySet<WebSocketServer> getWebSocketSet() {
        return webSocketSet;
    }

    /**
     *  连接成功调用的方法
     * @param session 用户的Session
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("param") String param) {
        nickName = param;
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        LOGGER.info("【==> 用户:{}加入聊天室,Online:{}" ,param,getOnlineCount());
        String message = String.format("[%s,%s]",nickName,"加入聊天室");

        // 广播发送消息
        broadcast(message);

    }

    /**
     *  连接关闭的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        String message = String.format("[%s,%s]",nickName,"离开聊天室");
        subOnlineCount();
        LOGGER.info("【==>用户：{} 退出聊天室,Online:{}】" , nickName,getOnlineCount());

        // 广播发送消息
        broadcast(message);
    }


    /**
     * 收到客户端消息后的调用方法
     * @param message   客户端发送的消息
     * @param session   客户端的标识
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        LOGGER.info("【==> nickname:{} 发送消息，message:{}】" , nickName,message);

        // 广播消息
        broadcast(String.format("%s:%s",nickName, message));

        // 保存聊天消息到数据中  可以设计异步调用
        recordService.saveChatting(nickName,message);
    }

    /**
     * 发生错误调用的方法
     * @param session 客户标识
     * @param error 错误信息
     */
    @OnError
    public void onError(Session session, Throwable error) {
        LOGGER.error("【==> websocket出现错误!!!,error:{},cause:{}",error.getMessage(),error.getCause());

    }


    /**
     * 群发广播消息
     * @param message 消息
     *
     */
    private static void broadcast(String message)  {
        webSocketSet.forEach(wb ->{
            try {
                wb.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                LOGGER.error("[===> 发生异常,发送消息失败 error:{}]",e.getStackTrace());
                // 移除此用户
                webSocketSet.remove(wb);
                //关闭此连接
                try {
                    wb.session.close();
                } catch (IOException e1) {
                    LOGGER.error("[===> 发生异常,用户断开连接 error:{}]",e1.getStackTrace());
                }
            }

        });
    }


    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }
    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }


}
