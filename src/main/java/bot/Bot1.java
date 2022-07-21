package bot;

import bot.managers.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class Bot1 extends TelegramLongPollingBot {

    private static final String TOKEN = "5518812439:AAEzksKfHlWWK8eZZlzPa9Fd_UQbMeCZJak";
    private static final String BOT_NAME = "Phys_helper_match_bot";

    MatchManager matchManager;
    UnknownsValueSetManager setValue = new UnknownsValueSetManager();
    UnknowValue value;
    int messageId;
    boolean waitNum;

    @Override
    public void onUpdateReceived(Update update)
    {
        boolean isMessage = update.hasMessage() && update.getMessage().hasText();
        boolean isCallBack = update.hasCallbackQuery();

        long chatId = isMessage ? update.getMessage().getChatId() : update.getCallbackQuery().getFrom().getId();

        if (isMessage)
        {
            String userMessage = update.getMessage().getText();
            String outToUser;

            boolean isCommand = update.getMessage().isCommand();
            boolean isNum = waitNum && update.getMessage().getText().matches("[\\d]++");

            if (isCommand)
            {
                CommandManager cManager = new CommandManager(userMessage);
                outToUser = cManager.commandHandler();
                outMessage(outToUser, chatId, cManager.markup);
                matchManager = new MatchManager(userMessage);
            }

            if (isNum)
            {
                matchManager.setValue(value, Integer.parseInt(userMessage));
                outToUser = matchManager.getAllVales();

//                outMessage(outToUser, chatId, matchManager.markup);
//                editMessage(outToUser, chatId, messageId, matchManager.markup);

                deleteMessage(chatId, update.getMessage().getMessageId());
                editMessage(outToUser, chatId, messageId, matchManager.markup);


                waitNum = false;
            }

        }

        if (isCallBack)
        {

            String call = update.getCallbackQuery().getData();
            messageId = update.getCallbackQuery().getMessage().getMessageId();
            boolean isKnowValue = call.contains("_VALUE");
            boolean isSetUnknownValue = call.equals("X");
            boolean isUnknownValue = call.contains("_N");
            value = setValue.setUnknownValue(call);

            if (isKnowValue)
            {
                String outToUser = "установите значение для " + call.charAt(0);
                editMessage(outToUser, chatId, messageId, new InlineKeyboardMarkup());
                waitNum = true;
            }

            if (isSetUnknownValue)
            {
                String outToUser = "установите неизвестную переменную (ответ будет выведен сразу)";
                KeyBoardManager keyBoardManager = new KeyBoardManager();

                InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
                markup.setKeyboard(keyBoardManager.getAllValue(matchManager.valueAll, false));
                //outMessage(outToUser, chatId, markup);
                editMessage(outToUser, chatId, messageId, markup);
            }


            if (isUnknownValue)
            {
                String outToUser = matchManager.getOutTask(value);                                     ///TODO: НЕ ЗАБУДЬ
                editMessage(outToUser, chatId, messageId, new InlineKeyboardMarkup());
            }
        }
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //НЕ ТРОГАТЬ
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    private void deleteMessage(long chatID, int messageID)
    {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(chatID);
        deleteMessage.setMessageId(messageID);
        try {
            execute(deleteMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }



    }

    private void editMessage(String text, long chatID, int messageID, InlineKeyboardMarkup markup)
    {

        EditMessageText editMessage = new EditMessageText();
        editMessage.setText(text);
        editMessage.setChatId(chatID);
        editMessage.setMessageId(messageID);


        if (markup.getKeyboard() != null){
            editMessage.setReplyMarkup(markup);
        }


        try
        {
            execute(editMessage);
        } catch (TelegramApiException e)
        {
            throw new RuntimeException(e);
        }

    }


    private void outMessage(String text, long chatID, InlineKeyboardMarkup markup)
    {

        SendMessage message = new SendMessage();
        message.setText(text);
        message.setChatId(chatID);

        if (markup.getKeyboard() != null)
        {
            message.setReplyMarkup(markup);
        }


        try
        {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String getBotUsername()
    {
        return BOT_NAME;
    }

    @Override
    public String getBotToken()
    {
        return TOKEN;
    }
}
