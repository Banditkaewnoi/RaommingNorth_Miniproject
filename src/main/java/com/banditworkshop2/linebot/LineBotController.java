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
                        "กรุณาพิมพ์ชื่อจังหวัดที่อยู่ในภาคเหนือด้วยนะครับพิมพ์ให้ถูกด้วยเน้อ :D ");
            case "ลำปาง": {
                this.reply(replyToken, Arrays.asList(
                        new TextMessage(" สถานที่ท่องเที่ยวแนะนำ : เหมืองแม่เมาะ "),
                        new TextMessage(" ข้อมูลสถานที่ : https://www.facebook.com/MaemohEGAT "),
                        new TextMessage(" พิกัด : https://goo.gl/maps/jyDpR6Pji5tnY4116"),
                        new TextMessage(" สถานที่ท่องเที่ยวเพิ่มเติม : https://travel.trueid.net/detail/jXqlkRaLX5p")));
            }
            case "พะเยา": {
                this.reply(replyToken, Arrays.asList(
                        new TextMessage(" สถานที่ท่องเที่ยวแนะนำ : กว๊านพะเยา "),
                        new TextMessage(" ข้อมูลสถานที่ : https://www.facebook.com/MaemohEGAT "),
                        new TextMessage(
                                " พิกัด : https://travel.trueid.net/detail/3KJNLYNxdRA?utm_source=web-trueid&utm_medium=ctw&utm_term=clicklink&utm_campaign=travel_X9zwqekRaBjZ_relatecontent_travel_3KJNLYNxdRA_25/01/2022 "),
                        new TextMessage(
                                " สถานที่ท่องเที่ยวเพิ่มเติม : https://travel.trueid.net/detail/X9zwqekRaBjZ")));
            }
            case "เชียงราย": {
                this.reply(replyToken, Arrays.asList(
                        new TextMessage(" สถานที่ท่องเที่ยวแนะนำ : ไร่ชาฉุยฟง "),
                        new TextMessage(" ข้อมูลสถานที่ : https://www.facebook.com/ChouiFongTea "),
                        new TextMessage(" พิกัด : https://goo.gl/maps/S7JvTSCBPQ5ZPkyc6"),
                        new TextMessage(" สถานที่ท่องเที่ยวเพิ่มเติม : https://travel.trueid.net/detail/R0LpNq7vYQR")));
            }
            case "เชียงใหม่": {
                this.reply(replyToken, Arrays.asList(
                        new TextMessage(" สถานที่ท่องเที่ยวแนะนำ : บ้านแม่กำปอง "),
                        new TextMessage(" ข้อมูลสถานที่ : https://travel.trueid.net/detail/4rklQrVdMBE?utm_source=web-trueid&utm_medium=ctw&utm_term=clicklink&utm_campaign=travel_K2qVnyVo7MO_relatecontent_travel_4rklQrVdMBE_25/11/2020 "),
                        new TextMessage(" พิกัด : https://g.page/maekhumpong?share"),
                        new TextMessage(" สถานที่ท่องเที่ยวเพิ่มเติม : https://travel.trueid.net/detail/K2qVnyVo7MO")));
            }
            case "แพร่": {
                this.reply(replyToken, Arrays.asList(
                        new TextMessage(" สถานที่ท่องเที่ยวแนะนำ : อุทยานแห่งชาติดอยผากลอง "),
                        new TextMessage(" ข้อมูลสถานที่ : https://www.facebook.com/ดอยผากลอง "),
                        new TextMessage(" พิกัด : https://goo.gl/maps/PxtBnfJvPTbkGzRC8"),
                        new TextMessage(" สถานที่ท่องเที่ยวเพิ่มเติม : https://travel.trueid.net/detail/ejRD7QwmNq3L")));
            }
            case "น่าน": {
                this.reply(replyToken, Arrays.asList(
                        new TextMessage(" สถานที่ท่องเที่ยวแนะนำ : หอศิลป์ริมน่าน "),
                        new TextMessage(" ข้อมูลสถานที่ : https://www.facebook.com/holsil.rimnan "),
                        new TextMessage(" พิกัด : https://goo.gl/maps/P9E7emKpL3PS73pD7"),
                        new TextMessage(" สถานที่ท่องเที่ยวเพิ่มเติม : https://travel.trueid.net/detail/aqP4e528vNd")));
            }
            case "ลำพูน": {
                this.reply(replyToken, Arrays.asList(
                        new TextMessage(" สถานที่ท่องเที่ยวแนะนำ : น้ำตกก้อหลวง "),
                        new TextMessage(" ข้อมูลสถานที่ : https://www.facebook.com/อุทยานแห่งชาติแม่ปิง/ "),
                        new TextMessage(" พิกัด : https://goo.gl/maps/YQRvLpWWevsz3wbN6"),
                        new TextMessage(" สถานที่ท่องเที่ยวเพิ่มเติม : https://travel.trueid.net/detail/O9Qp80zndyjY")));
            }
            case "แม่ฮ่องสอน": {
                this.reply(replyToken, Arrays.asList(
                        new TextMessage(" สถานที่ท่องเที่ยวแนะนำ : ทุ่งดอกบัวตอง ดอยแม่อูคอ "),
                        new TextMessage(" ข้อมูลสถานที่ : https://travel.trueid.net/detail/W8kr74dLAeX?utm_source=web-trueid&utm_medium=ctw&utm_term=clicklink&utm_campaign=travel_n2lpboApKWZ_relatecontent_travel_W8kr74dLAeX_05/10/2020 "),
                        new TextMessage(" พิกัด : https://goo.gl/maps/dmypk7oiSVyJgyxP6"),
                        new TextMessage(" สถานที่ท่องเที่ยวเพิ่มเติม : https://travel.trueid.net/detail/n2lpboApKWZ")));
            }
            case "อุตรดิตถ์": {
                this.reply(replyToken, Arrays.asList(
                        new TextMessage(" สถานที่ท่องเที่ยวแนะนำ : อุทยานแห่งชาติภูสอยดาว "),
                        new TextMessage(" ข้อมูลสถานที่ : https://www.facebook.com/phusoidao07 "),
                        new TextMessage(" พิกัด : https://goo.gl/maps/yAunvCs7dBxy8eZD8"),
                        new TextMessage(" สถานที่ท่องเที่ยวเพิ่มเติม : https://travel.trueid.net/detail/BgXXl1awwNWg")));
            }
            

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
