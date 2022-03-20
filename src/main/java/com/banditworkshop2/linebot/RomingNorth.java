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



