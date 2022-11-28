package com.shiropure.proxy;

import com.shiropure.config.ConfigManager;
import com.shiropure.exception.CanNotSendMessageException;
import com.shiropure.handler.MessageEventHandler;
import com.shiropure.handler.intercept.Interceptor;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.MessageChain;

import java.util.List;
import java.util.Map;

public class Context {
    private Map<String, Object> params;
    private final List<MessageEventHandler> handlerList;
    private final List<Interceptor> interceptorList;
    private String message;
    private int index;
    private boolean execute;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Context(List<MessageEventHandler> handlerList, List<Interceptor> interceptorList) {
        this.handlerList = handlerList;
        this.interceptorList = interceptorList;
    }

    /**
     * 设置参数，这将在 handler 之间传递
     * @param key
     * @param val
     * @return
     */
    public Context set(String key, Object val) {
        params.put(key, val);
        return this;
    }

    /**
     * 获取参数
     * @param key
     * @return
     */
    public Object get(String key) {
        return params.get(key);
    }

    /**
     * 在执行列表末尾添加一个 handler
     * @param handler
     * @return
     */
    public Context addHandler(MessageEventHandler handler) {
        handlerList.add(handler);
        return this;
    }

    /**
     * 将 handler 添加至当前 handler 的下一个位置
     * @param handler
     * @return
     */
    public Context addHandlerToNext(MessageEventHandler handler) {
        handlerList.add(index + 1, handler);
        return this;
    }

    public void continueExecute() {
        execute = true;
    }

    /**
     * 执行处理消息逻辑，一旦一个 handler 对此事件感兴趣（shouldHandle 返回 true），那么此事件就会交由该 handler 执行，execute 方法会立即将 hanlder 的回复信息发送出去，<strong>此事件不会再交由其他 handler 执行，但这不是绝对的， handler 可以在 {@link MessageEventHandler#handleMessageEvent} 方法中显式的调用 {@link #continueExecute()}} 方法以表明希望能够继续处理下一个 handler</strong>
     * @param event
     */
    public void execute(MessageEvent event) {
        execute = true;
        while (index < handlerList.size() && execute) {
            MessageEventHandler handler = handlerList.get(index);
            if (handler.shouldHandle(event, this)) {
                execute = false;
                List<MessageChain> res;
                try {
                    res = handler.handleMessageEvent(event, this);
                    for (Interceptor interceptor : interceptorList) {
                        res = interceptor.interceptAfter(event, res);
                        if (res == null) {
                            break;
                        }
                    }
                    reply(res, event);
                } catch (CanNotSendMessageException e) {
                    e.printStackTrace();
                    ConfigManager.recordFailLog(event, e.getMessage());
                }
            }
            index++;
        }
    }


    /**
     * 具体的回复动作
     * @throws CanNotSendMessageException
     */
    private void reply(List<MessageChain> replyMessages, MessageEvent event) throws CanNotSendMessageException {
        Contact contact = event.getSubject();
        if (replyMessages == null) {
            return;
        }
        try {
            for (MessageChain replyMessage : replyMessages) {
                if (replyMessage != null && !replyMessage.isEmpty()) {
                    contact.sendMessage(replyMessage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CanNotSendMessageException("Can not send message: \"" + replyMessages + "\", the contact is: " + contact + "\nCause by " + e.getCause().toString());
        }
    }
}
