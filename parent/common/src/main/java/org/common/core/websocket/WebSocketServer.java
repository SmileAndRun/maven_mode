package org.common.core.websocket;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

/**
 * 不加@Component注解会报路径错误
 * @author Administrator
 *
 */
@Component
@ServerEndpoint(value="/websocket")
public class WebSocketServer {
	private static ConcurrentHashMap<String, WebSocketServer> webSocketServers = new ConcurrentHashMap<String,WebSocketServer>();
	private Session session;
	/**客服端连接的时候触发*/
	@OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketServers.put(session.getId(), this);
        System.out.println("sessionID："+session.getId()+",Open触发");
    }
	/**当客服端断开连接时触发*/
	@OnClose
	public void onClose(){
		webSocketServers.remove(this);
		System.out.println("sessionID："+session.getId()+",Close触发");
	}
	/**接受客服端发来的信息
	 * 	待定
	 * */
	@OnMessage
    public void onMessage(String message, Session session) {
 
    }
	@OnError
    public void onError(Session session, Throwable error) {
		System.out.println("sessionID："+session.getId()+",error触发");
    }
	public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
	public static ConcurrentHashMap<String, WebSocketServer> getWebSocketServers() {
		return webSocketServers;
	}
	public static void setWebSocketServers(
			ConcurrentHashMap<String, WebSocketServer> webSocketServers) {
		WebSocketServer.webSocketServers = webSocketServers;
	}
}
