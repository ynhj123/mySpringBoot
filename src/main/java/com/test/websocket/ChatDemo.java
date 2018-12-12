package com.test.websocket;


import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * @className:
 * @Description:
 * @auther:ynhj
 * @date:17:22 2018-11-05
 * @version: ver 1.0
 */
@ServerEndpoint("/chat/{uid}")
public class ChatDemo {
    @OnOpen
    public void openSession(@PathVariable Integer uid, Session session){
        RemoteEndpoint.Basic basicRemote = session.getBasicRemote();
        try {
            basicRemote.sendText("welcome"+uid);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @OnMessage
    public void onMessage(@PathVariable Integer uid,Session session){
        RemoteEndpoint.Basic basicRemote = session.getBasicRemote();
        try {
            basicRemote.sendText("message"+uid);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
