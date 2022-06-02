package uz.pdp;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.List;

public class Main extends TelegramLongPollingBot {
    public static void main(String[] args) {
        try {
            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(new Main());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "WebServiceUzBot";
    }

    @Override
    public String getBotToken() {
        return "5543864112:AAFEB_qj2IqLC8CSXI2eB5Cz_2ZcCBADQEM";
    }
    public static int users = 0;
    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        Message message = update.getMessage();
        String text = message.getText();

        if (text.equals("/start")) {
            users++;
            sendMessage.setText("Sizga qanday yordam bera olamiz ?\nYani qanday platforma yaratmoqchisiz?");
        }else if (text.equals("getUsers0921")){
            sendMessage.setText("Umumiy startlar soni: "+String.valueOf(users));
        }else {
            sendMessage.setText("Hurmatli " + message.getChat().getFirstName() + " murojatingiz qabul qilindi yaqin daqiqqalar ichida siz bilan bog'lanamiz \uD83D\uDE0A\uD83D\uDE0A ");

        }
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(markup);
        markup.setResizeKeyboard(true);
        markup.setOneTimeKeyboard(true);
        markup.setSelective(true);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardButton bot = new KeyboardButton("Telegram Bot");
        KeyboardButton web = new KeyboardButton("Website");
        KeyboardButton tiz = new KeyboardButton("Korxonani Tizimlashtirish");
        row1.add(bot);
        row1.add(web);
        row2.add(tiz);

        keyboardRows.add(row1);
        keyboardRows.add(row2);
        markup.setKeyboard(keyboardRows);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
