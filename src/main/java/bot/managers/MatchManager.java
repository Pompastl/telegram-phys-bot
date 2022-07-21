package bot.managers;

public class MatchManager extends MessageHandler{
    public MatchManager(String USER_TEXT) {
        super(USER_TEXT);
    }

//    public MatchManager()
//    {
//        super("s");
//    }


//    @Override
//    public String getResult(UnknowValue value)
//    {
//        String out = "ответ = ";
//        int result = 0;
//
////        switch (call)
////        {
////            case "S":
////                boolean speedIsExists = variableValue.containsKey("V") && variableValue.get("V") != 0;
////                boolean timeIsExists = variableValue.containsKey("t") && variableValue.get("t") != 0;
////                boolean boostIsExists = variableValue.containsKey("a") && variableValue.get("a") != 0;
////
////                boolean isCorrectM1 = speedIsExists && timeIsExists;
////                boolean isCorrectM2 = isCorrectM1 && boostIsExists;
////
////                if (isCorrectM1)
////                {
////                    int speed = variableValue.get("V");
////                    int time = variableValue.get("t");
////                    result = speed * time;
////
////                    if (isCorrectM2)
////                    {
////                        int timePow2 = (int) Math.pow(time, 2);
////                        int boost = variableValue.get("a");
////                        result = (speed * time) + (boost * timePow2) / 2;
////                    }
////
////                }
////                break;
////
////
////            case "V":
////                timeIsExists = variableValue.containsKey("t") && variableValue.get("t") != 0;
////                speedIsExists = variableValue.containsKey("V") && (variableValue.get("V") != 0 || variableValue.get("V") == 0 );
////
////                boolean distanceExists = variableValue.containsKey("S") && variableValue.get("S") != 0;
////
////                isCorrectM1 = distanceExists && timeIsExists;
////                isCorrectM2 = timeIsExists && speedIsExists;
////
////                if (isCorrectM1)
////                {
////                    int distance = variableValue.get("S");
////                    int time = variableValue.get("t");
////
////                    result = distance / time;
////                }
////
////                if (isCorrectM2)
////                {
////                    int speed = variableValue.get("V") == null ? 0: variableValue.get("V");
////                    int boost = variableValue.get("a");
////                    int time = variableValue.get("t");
////
////                    result = speed + boost * time;
////                }
////                break;
////
////
////            case "t":
////                distanceExists =
////
////
////
////
////        }
//
//
//
//        variableValue.put(value, result);
//
//        out += variableValue.get(value) + "\n" + "введи /home чтобы вернутсья на главный экран";
//        return super.getResult(out);
//    }


    public String getOutTask(UnknowValue value)
    {
        boolean method1;
        boolean method2;
        String out = "";
        int result = 0;

        switch (value)
        {
            case SPEED :
                method1 = isExist(UnknowValue.DISTANCE, UnknowValue.TIME);


                if (method1)
                {
                    result = getValue(UnknowValue.DISTANCE) / getValue(UnknowValue.TIME);
                }



        }

        out = value.name() + " = " + result;
        return super.getResult(out);
    }

    private int getValue(UnknowValue value)
    {
     return variableValue.get(value);
    }

    private boolean isExist(UnknowValue value1, UnknowValue value2)
    {
        boolean out = (variableValue.containsKey(value1) && variableValue.get(value1) != 0) &&
                (variableValue.containsKey(value2) && variableValue.get(value2) != 0);
        return out;
    }



    public void setValue(UnknowValue value, int num)
    {
        variableValue.put(value, num);
    }

}
