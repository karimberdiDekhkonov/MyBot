package uz.pdp.bots;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class evroBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "@UzSinfdosh_bot";
    }
    static boolean auto = true;
    @Override
    public String getBotToken() {
        return "5194465546:AAFW8zO8aYQQJUMaRTDYEUsv1PdZ6_rVNCA";
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

            ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
            sendMessage.setReplyMarkup(markup);
            markup.setResizeKeyboard(true);
            markup.setOneTimeKeyboard(true);
            markup.setSelective(true);
            List<KeyboardRow> keyboardRows = new ArrayList<>();
            KeyboardRow row1 = new KeyboardRow();
            KeyboardRow row2 = new KeyboardRow();
            row1.add("Telegram Bot");
            row1.add("Website");
            row2.add("Korxonani Tizimlashtirish");
            keyboardRows.add(row1);
            keyboardRows.add(row2);
            markup.setKeyboard(keyboardRows);
        } else if (text.equals("getUsers0921")) {
            sendMessage.setText("Umumiy startlar soni: " + users);
        } else if (text.equals("Telegram Bot")) {
            sendMessage.setText("Tushunarli \nBot bajarishi kerak bo'lgan vazifalarni qisqacha yoritib o'ting!");
        }else if (text.equals("Website")) {
            sendMessage.setText("Tushunarli \nWebsite bajarishi kerak bo'lgan vazifalarni qisqacha yoritib o'ting!");
        }else {
            if (auto) {
                sendMessage.setText("Hurmatli " + message.getChat().getFirstName() + " murojatingiz qabul qilindi yaqin daqiqqalar ichida siz bilan bog'lanamiz \uD83D\uDE0A\uD83D\uDE0A ");
                auto=false;
            }else {
                sendMessage.setText("");
            }
        }



        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
