package bot.managers;

public class CommandManager extends MessageHandler {
    public CommandManager(String USER_TEXT)
    {
        super(USER_TEXT);
    }


    public String getResult()
    {
        String out = commandHandler();
        return super.getResult(out);
    }


    private String commandHandler()
    {
        String out = "";

        boolean isStartCommand = USER_TEXT.equals("/start") || USER_TEXT.equals("/help") || USER_TEXT.equals("/home");
        boolean isThemeCommand = USER_TEXT.contains("/theme") || USER_TEXT.equals("/mech");


        if (isStartCommand)
        {
            out = "привет!\n" +
                    "я бот физик, ты можешь дать мне задачу, я её решу \uD83E\uDDAE\n" +
                    "выбери раздел \uD83D\uDC47\n" +
                    "/mech - механика\n" +
                    "/theme_static - статистическая физика\n" +
                    "/theme_electro_mag - электричество и магнетизм\n" +
                    "/theme_optics - оптика\n" +
                    "/theme_atomic - атомная физика\n" +
                    "/theme_nuclear - ядерная физика\n";
        }

        if (isThemeCommand)
        {

            if (USER_TEXT.equals("/mech"))
            {
                out = "основновные направления:\n" +
                        "/theme_kin - кинематика\n" +
                        "/theme_dyn - динамика\n" +
                        "/theme_stat - статика";
                return out;
            }

            out = "установи переменные\n" + getAllVales();



        }



        return out;
    }
}
