//บัณฑิต แก้วน้อย 64363644 สร้าง class และ method และจัดการในส่วน interface ที่เกี่ยวกับ Flex  และตรวจสอบข้อมูล
//พชร ขันอาษา 64364139 เขียนในส่วน ของข้อมูลที่จะแสดงใน Flex 
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
                .url("https://cms.dmpcdn.com/travel/2020/09/23/19b141b0-fd8b-11ea-b266-63e567a949c5_original.JPG")
                .size(Image.ImageSize.FULL_WIDTH)
                .aspectRatio(ImageAspectRatio.R20TO13)
                .aspectMode(ImageAspectMode.Cover)
                .action(new URIAction("label", "https://cms.dmpcdn.com/travel/2020/09/23/19b141b0-fd8b-11ea-b266-63e567a949c5_original.JPG"))
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
                            .color("#8dafba")
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
                            .color("#8dafba")
                            .size(FlexFontSize.SM)
                            .flex(1)
                            .build(),
                        Text.builder()
                            .text("8.30 น. - 17.30 น.")
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