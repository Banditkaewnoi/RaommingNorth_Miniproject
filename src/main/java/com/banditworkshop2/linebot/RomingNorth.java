package com.banditworkshop2.linebot;

import java.util.Arrays;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public  class RomingNorth extends LineBotController {
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
                String userId = event.getSource().getUserId();

                if (userId != null) {
                    lineMessagingClient.getProfile(userId)
                            .whenComplete((phayao, throwable) -> {
                                if (throwable != null) {
                                    this.replyText(replyToken, throwable.getMessage());
                                    return;
                                }
                                this.reply(replyToken, Arrays.asList(
                                        new TextMessage("จังหวัดพะเยาพะเยา"),
                                        new TextMessage(
                                                "https://www.google.com/maps/place/Phayao/@19.2672932,99.0371395,8z/data=!3m1!4b1!4m5!3m4!1s0x30d82120f437301b:0x195a4b21f6c96a8c!8m2!3d19.2154367!4d100.2023692")));

                            });
                }
                break;
            }

        }
    }

    
    
}
