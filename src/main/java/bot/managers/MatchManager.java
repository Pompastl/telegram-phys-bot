package bot.managers;

public class MatchManager extends MessageHandler{
    public MatchManager(String USER_TEXT) {
        super(USER_TEXT);
    }

//    public MatchManager()
//    {
//        super("s");
//    }


    @Override
    public String getResult(String call)
    {
        String out = call + " = ";
        int result = 0;

        switch (call)
        {
            case "S":
                boolean speedIsExists = variableValue.containsKey("V") && variableValue.get("V") != 0;
                boolean timeIsExists = variableValue.containsKey("t") && variableValue.get("t") != 0;
                boolean boostIsExists = variableValue.containsKey("a") && variableValue.get("a") != 0;

                boolean isCorrectM1 = speedIsExists && timeIsExists;
                boolean isCorrectM2 = isCorrectM1 && boostIsExists;

                if (isCorrectM1)
                {
                    int speed = variableValue.get("V");
                    int time = variableValue.get("t");
                    result = speed * time;

                    if (isCorrectM2)
                    {
                        int timePow2 = (int) Math.pow(time, 2);
                        int boost = variableValue.get("a");
                        result = (speed * time) + (boost * timePow2) / 2;
                    }

                }
                break;


            case "V":
                timeIsExists = variableValue.containsKey("t") && variableValue.get("t") != 0;
                speedIsExists = variableValue.containsKey("V") && (variableValue.get("V") != 0 || variableValue.get("V") == 0 );

                boolean distanceExists = variableValue.containsKey("S") && variableValue.get("S") != 0;

                isCorrectM1 = distanceExists && timeIsExists;
                isCorrectM2 = timeIsExists && speedIsExists;

                if (isCorrectM2)
                {
                    int speed = variableValue.get("V") == null ? 0: variableValue.get("V");
                    int boost = variableValue.get("a");
                    int time = variableValue.get("t");

                    result = speed + boost * time;

                }



        }

        variableValue.put(call, result);

        out += variableValue.get(call) + "\n" + "введи /home чтобы вернутсья на главный экран";
        return super.getResult(out);
    }

    public void setValue(String value, int num)
    {
        variableValue.put(value.charAt(0) + "", num);
    }

}
