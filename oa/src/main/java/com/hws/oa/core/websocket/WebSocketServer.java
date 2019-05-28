package com.hws.oa.core.websocket;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 不加@Component注解会报路径错误
 * @author Administrator
 *
 */
@Component
@ServerEndpoint(value="/websocket/{jsessionId}")
public class WebSocketServer {
	private static Logger logger = Logger.getLogger(WebSocketServer.class);
	private static ConcurrentHashMap<String, Session> mapCache = new ConcurrentHashMap<>();
	
	private Session session;
	/**客服端连接的时候触发
	 * @throws IOException */
	@OnOpen
    public void onOpen(Session session,@PathParam("jsessionId") String jsessionId) throws IOException{
        this.session = session;
        //绑定httpsession和websocket Session
        mapCache.put(jsessionId, session);
        logger.info("sessionID："+session.getId()+",Open触发");
    }
	/**当客服端断开连接时触发
	 * @throws IOException */
	@OnClose
	public void onClose(@PathParam("type")String type,Session session) throws IOException{
		logger.info("sessionID："+this.session.getId()+",Close触发");
		
	}
	/**接受客服端发来的信息
	 * 	待定
	 * */
	@OnMessage
    public void onMessage(String message, Session session) {
 
    }
	@OnError
    public void onError(Session session, Throwable error) {
		logger.info("sessionID："+session.getId()+",error触发");
    }
	public void sendMessage(String message,Session session) throws IOException {
		session.getBasicRemote().sendText(message);
    }
	public static ConcurrentHashMap<String, Session> getMapCache() {
		return mapCache;
	}
}
