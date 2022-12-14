package com.shiropure.utils;

import com.shiropure.config.RobotConfig;
import com.shiropure.exception.FileUploadException;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.*;
import net.mamoe.mirai.utils.ExternalResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RobotUtil {
    public static List<MessageChain> buildMessageChainAsList(MessageChain... m) {
        return OfUtil.ofList(m);
    }
    public static MessageChain buildMessageChain(String... m) {
        MessageChainBuilder builder = new MessageChainBuilder();
        for (String s : m) {
            builder.append(s);
        }
        return builder.build();
    }
    public static MessageChain buildMessageChain(Object... m) {
        MessageChainBuilder builder = new MessageChainBuilder();
        for (Object s : m) {
            if (s == null) {
                continue;
            }
            if (s instanceof String) {
                s = new PlainText((CharSequence) s);
            }
            if (s instanceof StringBuilder) {
                s = new PlainText((CharSequence) s.toString());
            }
            if (s instanceof SingleMessage) {
                builder.append((SingleMessage) s);
            }
        }
        return builder.build();
    }
    public static List<MessageChain> buildMessageChainAsSingletonList(Object... m) {
        return OfUtil.ofList(buildMessageChain(m));
    }
    public static String getContent(MessageEvent event) {
        if (event == null) {
            return null;
        }
        return getContent(event.getMessage());
    }
    public static String getContent(MessageChain chain) {
        if (chain == null) {
            return null;
        }
        return chain.serializeToMiraiCode();
    }
    public static String getOnlyPlainContent(MessageEvent event) {
        if (event == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (SingleMessage singleMessage : event.getMessage()) {
            if (singleMessage instanceof PlainText) {
                sb.append(singleMessage);
            }
        }
        return sb.toString().trim();
    }
    public static String getSenderId(MessageEvent event) {
        return String.valueOf(event.getSender().getId());
    }

    public static long getSenderId2(MessageEvent event) {
        return event.getSender().getId();
    }

    /**
     * ????????????????????????
     *
     * @param event
     * @param msg
     * @return
     */
    public static MessageChain quoteReply(MessageEvent event, MessageChain msg) {
        return buildMessageChain(getQuoteReply(event), msg);
    }
    /**
     * ????????????????????????????????????????????? null<br/>
     * ????????????????????????????????????????????????chainBuilder.append(getQuoteReply(e))<br/>???????????????????????????buildMessageChain(getQuoteReply(e), msg) ????????????????????????<br/>???????????? getQuoteReply ????????????????????????????????????
     *
     * @param event
     * @return MessageSource
     * @see #buildMessageChain(Object...)
     * @see #quoteReply(MessageEvent, MessageChain)
     */
    public static QuoteReply getQuoteReply(MessageEvent event) {
        return new QuoteReply(event.getMessage());
    }
    public static net.mamoe.mirai.message.data.Image uploadImage(MessageEvent event, URL url) throws FileUploadException {
        return uploadImage(event.getSubject(), url);
    }

    /**
     * ???????????????????????????????????????
     *
     * @param contact ??????????????????????????????????????????????????????
     * @param url     ???????????? URL
     * @return net.mamoe.mirai.message.data.Image
     */
    public static net.mamoe.mirai.message.data.Image uploadImage(Contact contact, URL url) throws FileUploadException {
        try (InputStream stream = IOUtil.sendAndGetResponseStream(
                url,
                "GET",
                null,
                null
        )) {
            return Contact.uploadImage(contact, stream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileUploadException("Can not upload the image from the url: " + url + ", cause by " + e.getCause().toString());
        }
    }
    public static String formateCommand(String cmd)
    {
        return RobotConfig.commandPrefix+cmd;
    }
    public String stringFormateCommand(String cmd)
    {
        return RobotConfig.stringCommandPrefix+cmd;
    }
    public List<MessageChain> sendImage(String path,MessageEvent event) throws IOException {
        List<MessageChain> ans = new ArrayList<>();
        ExternalResource res = ExternalResource.create(new File(path));
        Image image = event.getSubject().uploadImage(res);
        res.close();
        ans.add(new MessageChainBuilder().append(image).build());
        return ans;
    }
}
