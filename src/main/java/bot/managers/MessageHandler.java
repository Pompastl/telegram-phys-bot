package bot.managers;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class MessageHandler {

    public final String USER_TEXT;
    public ArrayList<String> valueAll;
    public Map<UnknowValue, Integer> variableValue = new HashMap<>();
    public InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

    public boolean waitingNum;
    KeyBoardManager keyBoardManager = new KeyBoardManager();




    public MessageHandler(String USER_TEXT)
    {
        this.USER_TEXT = USER_TEXT;
    }

//    public void cleanAllValue(){
//        variableValue.put("S", 0);
//        variableValue.put("V", 0);
//        variableValue.put("t", 0);
//        variableValue.put("a", 0);
//    }

    public String getAllVales()
    {
        String out = "";
        valueAll = new ArrayList<>();
        markup = new InlineKeyboardMarkup();

        switch (USER_TEXT)
        {
            case "/theme_kin":

                out += "S = " + variableValue.get(UnknowValue.DISTANCE) + "\n";
                out += "V = " + variableValue.get(UnknowValue.SPEED) + "\n";
                out += "t = " + variableValue.get(UnknowValue.TIME) + "\n";
                out += "a = " + variableValue.get(UnknowValue.BOOST);

                valueAll.add("S");
                valueAll.add("V");
                valueAll.add("t");
                valueAll.add("a");
                break;
        }
        markup.setKeyboard(keyBoardManager.getAllValue(valueAll, true));
        waitingNum = true;

        return out;
    }


    public String getResult(String text)
    {
        waitingNum = false;
        return text;
    }


}
