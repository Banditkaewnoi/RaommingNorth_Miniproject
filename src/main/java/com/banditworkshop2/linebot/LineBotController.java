package com.banditworkshop2.linebot;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.LocationMessageContent;
import com.linecorp.bot.model.event.message.StickerMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.LocationMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.StickerMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@LineMessageHandler
public class LineBotController extends RomingNorth {
    @Autowired
    protected LineMessagingClient lineMessagingClient;

    @EventMapping
    public void handleTextMessage(MessageEvent<TextMessageContent> event) {
        log.info(event.toString());
        TextMessageContent message = event.getMessage();
        handleTextContent(event.getReplyToken(), event, message);
    }

    @EventMapping
    public void handleStickerMessage(MessageEvent<StickerMessageContent> event) {
        log.info(event.toString());
        StickerMessageContent message = event.getMessage();
        reply(event.getReplyToken(), new StickerMessage(
                message.getPackageId(), message.getStickerId()));
    }

    @EventMapping
    public void handleLocationMessage(MessageEvent<LocationMessageContent> event) {
        log.info(event.toString());
        LocationMessageContent message = event.getMessage();
        reply(event.getReplyToken(), new LocationMessage(
                (message.getTitle() == null) ? "Location replied" : message.getTitle(),
                message.getAddress(),
                message.getLatitude(),
                message.getLongitude()));
    }


    private void handleTextContent(String replyToken, Event event, TextMessageContent content) {
        String text = content.getText();

        log.info("Got text message from %s : %s", replyToken, text);

        switch (text) {
            case "Profile": {
                String userId = event.getSource().getUserId();
                if (userId != null) {
                    lineMessagingClient.getProfile(userId)
                            .whenComplete((profile, throwable) -> {
                                if (throwable != null) {
                                    this.replyText(replyToken, throwable.getMessage());
                                    return;
                                }
                                this.reply(replyToken, Arrays.asList(
                                        new TextMessage("Display name: " + profile.getDisplayName()),
                                        new TextMessage("Status message: " + profile.getStatusMessage()),
                                        new TextMessage("User ID: " + profile.getUserId())));
                            });
                }
                break;
            }
            default:
                log.info("Return echo message %s : %s", replyToken, text);
                this.replyText(replyToken,
                        "กรุณาพิมพ์ชื่อจังหวัดที่อยู่ในภาคเหนือด้วยนะครับพิมพ์ให้ถูกด้วยเน้อ :D " + text);

            case "พะเยา": {
                

                this.reply(replyToken, Arrays.asList(
                        new TextMessage("สถานที่ท่องเที่ยวที่แนะนำ : กว๊านพะเยา"),
                        new TextMessage(
                                "https://goo.gl/maps/xEiU7E45y8PcgMSr7"),
                        new TextMessage("https://travel.trueid.net/detail/X9zwqekRaBjZ")));

            }
            case "ลำปาง": {
                this.replyText(replyToken, this.Lampang);
            }
            }
                ;
        }
    
    

    private void replyText(String replyToken, List<TextMessage> lampang) {
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
