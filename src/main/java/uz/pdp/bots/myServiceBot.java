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

public class myServiceBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "WebServiceUzBot";
    }

    @Override
    public String getBotToken() {
        return "5543864112:AAFEB_qj2IqLC8CSXI2eB5Cz_2ZcCBADQEM";
    }

    public static int users = 0;
    static boolean auto = true;

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        Message message = update.getMessage();
        String text = message.getText();


        switch (text) {
            case "/start":
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
                break;
            case "getUsers0921":
                sendMessage.setText("Umumiy startlar soni: " + users);
                break;
            case "Telegram Bot":
                sendMessage.setText("Tushunarli \nBot bajarishi kerak bo'lgan vazifalarni qisqacha yoritib o'ting!");
                auto = true;
                break;
            case "Website":
                sendMessage.setText("Tushunarli \nWebsite bajarishi kerak bo'lgan vazifalarni qisqacha yoritib o'ting!");
                auto = true;
                break;
            case "Korxonani Tizimlashtirish":
                sendMessage.setText("Tushunarli \nTizim bajarishi kerak bo'lgan vazifalarni qisqacha yoritib o'ting!");
                auto = true;
                break;
            default:
                if (auto) {
                    sendMessage.setText("Hurmatli " + message.getChat().getFirstName() + " murojatingiz qabul qilindi yaqin daqiqqalar ichida siz bilan bog'lanamiz \uD83D\uDE0A\uD83D\uDE0A ");
                    auto = false;
                } else {
                    sendMessage.setText(" ");
                    return;
                }
        }

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
