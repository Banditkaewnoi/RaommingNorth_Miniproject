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
 protected String Lampang, Lampang1;

 protected void Lampang(){
    this.Lampang = "สถานที่ท่องเที่ยวที่แนะนำ : พิพิธภัณฑ์เซรามิกธนบดี";
 }
 protected void setLampang(){
     this.Lampang = Lampang;
     
 }
 protected void setLampang1(){
    this.Lampang1 = Lampang1;

    this.Lampang1 = "https://g.page/MuseumDhanabadee?share";
    
}
 protected String getLampang(){
     return this.Lampang;
 }
}
    

        
            
                
        
    



