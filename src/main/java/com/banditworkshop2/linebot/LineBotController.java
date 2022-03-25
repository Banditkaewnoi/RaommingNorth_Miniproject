package com.banditworkshop2.linebot;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.client.MessageContentResponse;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.LocationMessageContent;
import com.linecorp.bot.model.event.message.StickerMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.*;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import lombok.NonNull;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import java.util.concurrent.ExecutionException;

@Slf4j
@LineMessageHandler
public class LineBotController {
    @Autowired
    private LineMessagingClient lineMessagingClient;

    @EventMapping
    public void handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws IOException {
        log.info(event.toString());
        TextMessageContent message = event.getMessage();
        handleTextContent(event.getReplyToken(), event, event.getMessage());
    }

    private void handleTextContent(String token, Event event, TextMessageContent content) throws IOException {
        String text = content.getText();
        switch (text) {
            case "พะเยา": {
                this.reply(token, new PhayaoFlex().get());
                break;

            }
            case "ลำปาง": {
                this.reply(token, new LampangFlex().get());
                break;

            }
            case "เชียงราย": {
                this.reply(token, new ChiangRaiFlex().get());
                break;

            }
            case "เชียงใหม่": {
                this.reply(token, new ChiangMaiFlex().get());
                break;

            }
            case "น่าน": {
                this.reply(token, new NanFlex().get());
                break;

            }
            case "แพร่": {
                this.reply(token, new PhraeFlex().get());
                break;

            }
            case "ลำพูน": {
                this.reply(token, new LamphunFlex().get());
                break;

            }
            case "แม่ฮ่องสอน": {
                this.reply(token, new MaehongsonFlex().get());
                break;

            }
            case "อุตรดิตถ์": {
                this.reply(token, new UttaraditFlex().get());
                break;

            }
            case "จังหวัด": {
                this.reply(token, Arrays.asList(
                        new TextMessage(
                                "พิมพ์ ลำปาง, พะเยา, เชียงราย, เชียงใหม่, แพร่, น่าน, ลำพูน, แพร่, แม่ฮ่องสอน, อุตรดิตถ์ เพื่อดูสถานที่ท่องเที่ยวที่แนะนำ :D")));
            }

            default:
                log.info("Return echo message %s : %s", token, text);
                this.replyText(token,
                        "กรุณาพิมพ์ชื่อจังหวัดให้ถูกต้องเพื่อดูรายชื่อจังหวัดในภาคเหนือ ");

        }
    }

    private void handleStickerContent(String replyToken, StickerMessageContent content) {
        reply(replyToken, new StickerMessage(
                content.getPackageId(), content.getStickerId()));
    }

    protected void replyText(@NonNull String replyToken, @NonNull String message) {
        if (replyToken.isEmpty()) {
            throw new IllegalArgumentException("replyToken is not empty");
        }

        if (message.length() > 1000) {
            message = message.substring(0, 1000 - 2) + "...";
        }
        this.reply(replyToken, new TextMessage(message));
    }

    protected void reply(@NonNull String replyToken, @NonNull Message message) {
        reply(replyToken, Collections.singletonList(message));
    }

    protected void reply(@NonNull String replyToken, @NonNull List<Message> messages) {
        try {
            BotApiResponse response = lineMessagingClient.replyMessage(
                    new ReplyMessage(replyToken, messages)).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
