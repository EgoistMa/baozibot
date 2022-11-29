package com.shiropure.handler.impl;

import com.shiropure.api.SplatoonApi;
import com.shiropure.handler.handler;
import com.shiropure.proxy.Context;
import com.shiropure.utils.DateUtil;
import com.shiropure.utils.OfUtil;
import com.shiropure.utils.SplatoonUtil;
import com.shiropure.utils.StringUtil;
import kotlinx.serialization.json.Json;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.MessageChain;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@handler
@SuppressWarnings("unchecked")
public class SplatoonMessageEventHandler extends GroupMessageEventHandler {
    public final String command1 = "喷喷";
    public final String command2 = "喷2";
    @Override
    public List<MessageChain> handleMessageEvent(MessageEvent event, Context ctx) {
        try {
            //todo 优化这里
            String content = getPlantContent(event);
            StringBuilder sb = new StringBuilder();
            if (content.startsWith(command1)) {
                Map<String, Object> map = SplatoonApi.SplatoonSchedules("bankaraSchedules");
                if (map == null) {
                    return buildMessageChainAsSingletonList("未查询到相关数据", getQuoteReply(event));
                }


                //todo time;
                long currentTime = new Date().getTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                DateFormat TimeFormat = new SimpleDateFormat("HH:mm:ss:SS");
                String date = dateFormat.format(currentTime);
                String time = TimeFormat.format(currentTime);
                String dateTime = date+"T"+time+"Z";
                logger.info(dateTime);
                String DateRegion = DateUtil.getDateRegion(dateTime);
                String DateRegionEnd = DateUtil.getNextDateRegion(DateRegion);
                /*
                map.forEach((key, value) -> {
                    sb.append(key + " + " + value + "\n");
                });
                 */
                for (int i = 1; i < 5; i++)
                {
                    logger.info("UTC: "+DateRegion+" -> "+DateRegionEnd+"matchNumber:"+i);
                    String eng = map.get("UTC: "+DateRegion+" -> "+DateRegionEnd+"matchNumber:"+i).toString();
                    sb.append(SplatoonUtil.translateStage(eng) +"\n");

                }
                return buildMessageChainAsSingletonList(sb.toString());
            }
            if (content.startsWith(command2)) {
                return buildMessageChainAsSingletonList("命令 喷2 执行");
            }
            return buildMessageChainAsSingletonList(getQuoteReply(event), "不可能发生" + content);
        } catch (Exception e) {
            logError(event, e);
            return buildMessageChainAsSingletonList("发生了意料之外、情理之中的错误：" + e.getMessage());
        }
    }


    @Override
    public boolean shouldHandle(MessageEvent event, Context ctx) {
        return startWithKeywords(event, OfUtil.ofSet(command1,command2));
    }
}
