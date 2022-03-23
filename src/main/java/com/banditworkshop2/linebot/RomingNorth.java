package com.banditworkshop2.linebot;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.message.StickerMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.StickerMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;

import afu.org.checkerframework.checker.nullness.qual.NonNull;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class RomingNorth{
 protected List<TextMessage> Lampang;

 private void setLampange(){
     this.Lampang = Lampang;
     this.Lampang = Arrays.asList(new TextMessage ("ลำปาง"));
 }
 private List<TextMessage> getLampang(){
     return this.Lampang;
 }
}
    

        
            
                
        
    



