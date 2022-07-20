package bot.managers;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class KeyBoardManager {
    public KeyBoardManager() {
    }

    public List<List<InlineKeyboardButton>> getAllValue(ArrayList<String> value, boolean isKnowValue)
    {
        List<List<InlineKeyboardButton>> totalRow = new ArrayList<>();
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        List<InlineKeyboardButton> row2 = new ArrayList<>();

        ArrayList<InlineKeyboardButton> buttons = new ArrayList<>();

        for (int i = 0; i != value.size(); i++)
        {
            buttons.add(i, new InlineKeyboardButton());
            buttons.get(i).setText(value.get(i));


            if (isKnowValue)
            {
                buttons.get(i).setCallbackData(value.get(i) + "_VALUE");
            } else buttons.get(i).setCallbackData(value.get(i) + "_N");

            if (i <= 2)
            {
                row1.add(buttons.get(i));
            } else row2.add(buttons.get(i));
        }

        if (isKnowValue)
        {
            InlineKeyboardButton button = new InlineKeyboardButton("X");
            button.setCallbackData("X");
            row2.add(button);
        }




        totalRow.add(row1);
        totalRow.add(row2);

        return totalRow;
    }
}
