package com.banditworkshop2.linebot;
import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.flex.component.*;
import com.linecorp.bot.model.message.flex.component.Button.ButtonHeight;
import com.linecorp.bot.model.message.flex.component.Image.ImageAspectMode;
import com.linecorp.bot.model.message.flex.component.Image.ImageAspectRatio;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.linecorp.bot.model.message.flex.unit.FlexMarginSize;

import java.util.function.Supplier;

import static java.util.Arrays.asList;

public class ChiangRaiFlex implements Supplier<FlexMessage> {
    @Override
    public FlexMessage get() {
        final Image heroBlock = createHeroBlock();
        final Box bodyBlock = createBodyBlock();
        final Box footerBlock = createFooterBlock();

        final Bubble bubbleContainer = Bubble.builder()
                .hero(heroBlock)
                .body(bodyBlock)
                .footer(footerBlock)
                .build();
        return new FlexMessage("Chiang rai", bubbleContainer);
    }

    private Image createHeroBlock() {
        return Image.builder()
                .url("https://cms.dmpcdn.com/travel/2021/07/27/f4aa6420-eeb9-11eb-8793-61b2b4d8b27c_original.jpg")
                .size(Image.ImageSize.FULL_WIDTH)
                .aspectRatio(ImageAspectRatio.R20TO13)
                .aspectMode(ImageAspectMode.Cover)
                .action(new URIAction("label", "https://cms.dmpcdn.com/travel/2021/07/27/f4aa6420-eeb9-11eb-8793-61b2b4d8b27c_original.jpg"))
                .build();
    }

    private Box createBodyBlock() {
            
        final Text title = Text.builder()
                .text("ไร่ชาฉุยฟง")
                .color("#075673")
                .weight(Text.TextWeight.BOLD)
                .size(FlexFontSize.XL)
                .build();

        final Box info = createInfoBox();
                

        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .contents(asList(title, info))
                .build();
    }

    private Box createInfoBox() {
        final Box place = Box.builder()
                .layout(FlexLayout.BASELINE)
                .spacing(FlexMarginSize.SM)
                .contents(asList(
                        Text.builder()
                            .text("Place")
                            .color("#aaaaaa")
                            .size(FlexFontSize.SM)
                            .flex(1)
                            .build(),
                        Text.builder()
                            .text("อ.แม่จัน, จ.เชียงราย")
                            .wrap(true)
                            .color("#666666")
                            .flex(5)
                            .build()
                )).build();
        final Box time = Box.builder()
                .layout(FlexLayout.BASELINE)
                .spacing(FlexMarginSize.SM)
                .contents(asList(
                        Text.builder().text("Time")
                            .color("#aaaaaa")
                            .size(FlexFontSize.SM)
                            .flex(1)
                            .build(),
                        Text.builder()
                            .text("8.30AM - 5.30PM)")
                            .wrap(true)
                            .color("#666666")
                            .size(FlexFontSize.SM)
                            .flex(5)
                            .build()
                )).build();
        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .margin(FlexMarginSize.LG)
                .spacing(FlexMarginSize.SM)
                .contents(asList(place, time))
                .build();
    }

   
    private Box createFooterBlock() {
        final Spacer spacer = Spacer.builder().size(FlexMarginSize.SM).build();
        final Button websiteAction = Button.builder()
                .style(Button.ButtonStyle.LINK)
                .height(ButtonHeight.SMALL)
                .color("#000000")
                .action(new URIAction("ข้อมูล", "https://www.paiduaykan.com/76_province/north/chiangrai/chouifongtea.html"))
                .build();
        final Button ortherAction = Button.builder()
                .style(Button.ButtonStyle.LINK)
                .height(ButtonHeight.MEDIUM)
                .action(new URIAction("สถานที่ท่องเที่ยวเพิ่มเติม", "https://travel.trueid.net/detail/R0LpNq7vYQR"))
                .color("#000000")
                .build();
        final Button locAction = Button.builder()
                .style(Button.ButtonStyle.LINK)
                .height(ButtonHeight.SMALL)
                .action(new URIAction("พิกัด",
                                "https://goo.gl/maps/kNxE2wugCKkU4kPR8"))
                .color("#000000")
                .build();
        final Separator separator = Separator.builder().build();
                return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .spacing(FlexMarginSize.SM)
                .contents(asList(spacer, websiteAction,separator,locAction,separator, ortherAction))
                .build();

    }
}