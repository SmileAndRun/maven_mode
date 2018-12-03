package org.common.core.websocket;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

/**
 * 不加@Component注解会报路径错误
 * @author Administrator
 *
 */
@Component
@ServerEndpoint(value="/websocket/{id}")
public class WebSocketServer {
	private static int onlineNum;
	private static ConcurrentHashMap<String, WebSocketServer> webSocketServers = new ConcurrentHashMap<String,WebSocketServer>();
	private static ConcurrentHashMap<String, Set<String>> userSign = new ConcurrentHashMap<String,Set<String>>();
	private Session session;
	private String id;
	/**客服端连接的时候触发*/
	@OnOpen
    public void onOpen(@PathParam(value = "id")String id,Session session) {
        this.session = session;
        this.id = id;
        webSocketServers.put(session.getId(), this);
        Set<String> set = null;
        if(userSign.containsKey(id)){
        	set = userSign.get(id);
        }else{
        	addOnlineNum(); //在线数加1
        	set = new HashSet<String>();
        }
        set.add(session.getId());
        userSign.put(id, set);
        System.out.println("sessionID："+session.getId()+",Open触发,当前在线人数："+onlineNum);
    }
	/**当客服端断开连接时触发*/
	@OnClose
	public void onClose(){
		webSocketServers.remove(this);
		Set<String> set = userSign.get(id);
		set.remove(this);
		if(set.size()==0)subOnlineNum();
		System.out.println("sessionID："+session.getId()+",Close触发,当前在线人数："+onlineNum);
	}
	/**接受客服端发来的信息
	 * 	待定
	 * */
	@OnMessage
    public void onMessage(String message, Session session) {
 
    }
	@OnError
    public void onError(Session session, Throwable error) {
		System.out.println("sessionID："+session.getId()+",error触发,当前在线人数："+onlineNum);
    }
	public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
	public static synchronized int getOnlineNum() {
		return onlineNum;
	}
	public static synchronized void addOnlineNum() {
		onlineNum++;
	}
	public static synchronized void subOnlineNum() {
		onlineNum--;
	}
	
}
