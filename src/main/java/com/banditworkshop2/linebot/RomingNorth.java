package com.banditworkshop2.linebot;

import java.nio.file.Path;
import java.util.Arrays;

import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;

import lombok.Value;

public class RomingNorth extends LineBotController {
    protected void Lumpang(String replyToken, TextMessageContent content) {
        String text = content.getText();
        switch (text) {
            case "ลำปาง": {
                this.reply(replyToken, Arrays.asList(
                        new TextMessage("จังหวัดลำปาง"),
                        new TextMessage(
                                "https://www.google.com/maps/place/Phayao/@19.2672932,99.0371395,8z/data=!3m1!4b1!4m5!3m4!1s0x30d82120f437301b:0x195a4b21f6c96a8c!8m2!3d19.2154367!4d100.2023692")

                ));

            }
        }
    }
    @Value
    public static class DownloadedContent {
        Path path;
        String uri;
    }
}
