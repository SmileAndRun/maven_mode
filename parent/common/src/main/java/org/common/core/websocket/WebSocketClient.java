package org.common.core.websocket;

import java.io.IOException;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
/**
 * 服务器主动发送消息
 * @author Administrator
 *
 */
@ClientEndpoint
public class WebSocketClient {
	private Session session;
	/**客服端连接的时候触发*/
	@OnOpen
    public void onOpen(Session session) {
        this.session = session;
    }
	/**当客服端断开连接时触发*/
	@OnClose
	public void onClose(){
	}
	/**接受客服端发来的信息
	 * 	待定
	 * */
	@OnMessage
    public void onMessage(String message, Session session) {
 
    }
	@OnError
    public void onError(Session session, Throwable error) {
    }
	public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
}
