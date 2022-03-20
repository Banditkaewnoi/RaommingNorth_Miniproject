package com.banditworkshop2.linebot;

import java.util.Arrays;

import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;

public class RomingNorth extends LineBotController{
    private void handleTextContent(String replyToken,TextMessageContent content){
        String text = content.getText();
        switch (text) {
            
            case "พะเยาc": {
                

                this.reply(replyToken, Arrays.asList(
                        new TextMessage("สถานที่ท่องเที่ยวที่แนะนำ : กว๊านพะเยา"),
                        new TextMessage(
                                "https://goo.gl/maps/xEiU7E45y8PcgMSr7"),
                        new TextMessage("https://travel.trueid.net/detail/X9zwqekRaBjZ")));

            }
                ;
        }
    }
    }




