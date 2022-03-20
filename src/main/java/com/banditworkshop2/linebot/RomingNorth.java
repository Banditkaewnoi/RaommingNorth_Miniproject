package com.banditworkshop2.linebot;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;

import org.checkerframework.checker.guieffect.qual.UI;
import org.hibernate.validator.constraints.URL;

import lombok.NonNull;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RomingNorth extends LineBotController {
    private void Phayao(String replyToken, TextMessageContent content) {
        String text = content.getText();

        log.info("Got text message from %s : %s", replyToken, text);

        switch (text) {

            case "พะเยา": {
                this.reply(replyToken, Arrays.asList(
                        new TextMessage("จังหวัดพะเยาพะเยา"),
                        new TextMessage(
                                "https://www.google.com/maps/place/Phayao/@19.2672932,99.0371395,8z/data=!3m1!4b1!4m5!3m4!1s0x30d82120f437301b:0x195a4b21f6c96a8c!8m2!3d19.2154367!4d100.2023692")));

            }
                ;
        }

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
    @Value
    public static class DownloadedContent {
        Path path;
        String uri;
    }

}
