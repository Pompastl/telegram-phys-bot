package bot.managers;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class MessageHandler {

    public final String USER_TEXT;
    public ArrayList<String> valueAll;
    public Map<UnknownValue, Integer> variableValue = new HashMap<>();
    public InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

    public boolean waitingNum;
    KeyBoardManager keyBoardManager = new KeyBoardManager();




    public MessageHandler(String USER_TEXT)
    {
        this.USER_TEXT = USER_TEXT;
        cleanAllValue();
    }


    public UnknownValue setUnknownValue(String value)
    {
        UnknownValue unknownValue = UnknownValue.ERROR;
        value = value.charAt(0) + "";
        switch (value)
        {
            case "S":
                unknownValue = UnknownValue.DISTANCE;
                break;
            case "V":
                unknownValue = UnknownValue.SPEED;
                break;
            case "t":
                unknownValue = UnknownValue.TIME;
                break;
            case "a":
                unknownValue = UnknownValue.BOOST;
                break;

            case "P":
                unknownValue = UnknownValue.DENSITY;
                break;

            case "F":
                unknownValue = UnknownValue.POWER;
                break;

            case "M":
                unknownValue = UnknownValue.MASS;
                break;

            case "N":
                unknownValue = UnknownValue.GRAVITY;
                break;

            case "µ":
                unknownValue = UnknownValue.FRICTION_FORCE;
                break;

            case "k":
                unknownValue = UnknownValue.BODY_STIFFNESS;
                break;

            case "g":
                unknownValue = UnknownValue.ACCELERATION_GRAVITY;
                break;
        }

        return unknownValue;
    }


    private void cleanAllValue(){
        variableValue.put(UnknownValue.DISTANCE, 0);
        variableValue.put(UnknownValue.SPEED, 0);
        variableValue.put(UnknownValue.TIME, 0);
        variableValue.put(UnknownValue.BOOST, 0);

        variableValue.put(UnknownValue.DENSITY, 0);
        variableValue.put(UnknownValue.POWER, 0);
        variableValue.put(UnknownValue.MASS, 0);
        variableValue.put(UnknownValue.GRAVITY, 0);
        variableValue.put(UnknownValue.FRICTION_FORCE, 0);
        variableValue.put(UnknownValue.BODY_STIFFNESS, 0);
        variableValue.put(UnknownValue.ACCELERATION_GRAVITY, 10);
    }

    public String getAllVales()
    {
        String out = "";
        valueAll = new ArrayList<>();
        markup = new InlineKeyboardMarkup();

        switch (USER_TEXT)
        {
            case "/theme_kin":

                out += "S = " + variableValue.get(UnknownValue.DISTANCE) + "\n";
                out += "V = " + variableValue.get(UnknownValue.SPEED) + "\n";
                out += "t = " + variableValue.get(UnknownValue.TIME) + "\n";
                out += "a = " + variableValue.get(UnknownValue.BOOST);

                valueAll.add("S");
                valueAll.add("V");
                valueAll.add("t");
                valueAll.add("a");
                break;

            case "/theme_dyn":

                out += "P = " + variableValue.get(UnknownValue.DENSITY) + "\n";
                out += "F = " + variableValue.get(UnknownValue.POWER) + "\n";
                out += "M = " + variableValue.get(UnknownValue.MASS) + "\n";
                out += "a = " + variableValue.get(UnknownValue.BOOST) + "\n";
                out += "N = " + variableValue.get(UnknownValue.GRAVITY) + "\n";
                out += "µ = " + variableValue.get(UnknownValue.FRICTION_FORCE) + "\n";
                out += "k = " + variableValue.get(UnknownValue.BODY_STIFFNESS) + "\n";
                out += "g = " + variableValue.get(UnknownValue.ACCELERATION_GRAVITY);


                valueAll.add("P");
                valueAll.add("F");
                valueAll.add("M");
                valueAll.add("a");
                valueAll.add("N");
                valueAll.add("µ");
                valueAll.add("k");
                valueAll.add("g");
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
