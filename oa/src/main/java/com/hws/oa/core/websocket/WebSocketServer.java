package com.hws.oa.core.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

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
@ServerEndpoint(value="/websocket/{type}")
public class WebSocketServer {
	private static Logger logger = Logger.getLogger(WebSocketServer.class);
	public static CopyOnWriteArraySet<WebSocketServer> webSocketServersForUpdateJob = new CopyOnWriteArraySet<WebSocketServer>();
	public static CopyOnWriteArraySet<WebSocketServer> webSocketServersForChat = new CopyOnWriteArraySet<WebSocketServer>();
	private Session session;
	/**客服端连接的时候触发
	 * @throws IOException */
	@OnOpen
    public void onOpen(Session session,@PathParam("type")String type) throws IOException{
        this.session = session;
        if(type.equals("0")){
        	webSocketServersForUpdateJob.add(this);
        }else if(type.equals("1")){
        	webSocketServersForChat.add(this);
        }
        String message = "{webSocketSessionId:'"+session.getId()+"'}";
        sendMessage(message);
        Map<String, String> map = new HashMap<String,String>();
		map.put("type", "1");
		map.put("content", session.getId());
		sendMessageToAll(map);
        logger.info("sessionID："+session.getId()+",Open触发");
    }
	/**当客服端断开连接时触发
	 * @throws IOException */
	@OnClose
	public void onClose(@PathParam("type")String type) throws IOException{
		if(type.equals("0")){
			webSocketServersForUpdateJob.remove(this);
        }else if(type.equals("1")){
        	webSocketServersForChat.remove(this);
        }
		Map<String, String> map = new HashMap<String,String>();
		map.put("type", "2");
		map.put("content", this.session.getId());
		sendMessageToAll(map);
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
	public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
	public void sendMessageToAll(Session session) throws IOException{
		for(WebSocketServer wServer:webSocketServersForChat){
			wServer.session.getBasicRemote().sendText("{content:'"+session.getId()+"'}");
		}
	}
	public void sendMessageToAll(String message) throws IOException{
		for(WebSocketServer wServer:webSocketServersForChat){
			wServer.session.getBasicRemote().sendText(message);
		}
	}
	public void sendMessageToAll(Map<String, String>map) throws IOException{
		String message = "";
		if(null != map&& map.size() != 0){
			for(String key:map.keySet()){
				message += key + ":" +"'"+map.get(key)+"',";
			}
		}
		message = message.substring(0,message.length()-1);
		for(WebSocketServer wServer:webSocketServersForChat){
			wServer.session.getBasicRemote().sendText("{"+message+"}");
		}
	}
}
