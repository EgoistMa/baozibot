package com.shiropure.command;

import com.shiropure.exception.CanNotParseCommandException;
import com.shiropure.exception.InsufficientPermissionsException;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.MessageChain;

import java.util.List;

public interface CommandHandler {
    /**
     * 解析命令
     * @param event 命令事件
     * @return 解析完成后返回的消息
     * @throws CanNotParseCommandException 无法解析时请抛出此异常
     * @throws InsufficientPermissionsException 没有足够权限时请抛出此异常
     */
    List<MessageChain> parseCommand(MessageEvent event) throws CanNotParseCommandException, InsufficientPermissionsException;
}
