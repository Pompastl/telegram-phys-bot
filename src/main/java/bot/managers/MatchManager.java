package bot.managers;

public class MatchManager extends MessageHandler
{
    public MatchManager(String USER_TEXT)
    {
        super(USER_TEXT);
    }





    public String getOutTask(UnknownValue value)
    {
        int result = 0;

        switch (value)
        {
            case SPEED :

                if (isExist(UnknownValue.DISTANCE, UnknownValue.TIME))
                {
                    result = getValue(UnknownValue.DISTANCE) / getValue(UnknownValue.TIME);
                }

                if (isExist(UnknownValue.SPEED) || isExist(UnknownValue.BOOST, UnknownValue.TIME))
                {
                    result = getValue(UnknownValue.SPEED) + getValue(UnknownValue.BOOST) * getValue(UnknownValue.TIME);
                }
                break;

            case DISTANCE:

                if (isExist(UnknownValue.SPEED, UnknownValue.TIME))
                {
                    result = getValue(UnknownValue.SPEED) * getValue(UnknownValue.TIME);
                }

                if (isExist(UnknownValue.SPEED) || isExist(UnknownValue.TIME, UnknownValue.BOOST))
                {
                    int time = (int) Math.pow(getValue(UnknownValue.TIME), 2);
                    result = getValue(UnknownValue.SPEED) * getValue(UnknownValue.TIME) + (time * getValue(UnknownValue.BOOST)) / 2;
                }
                break;

            case BOOST:

                if (isExist(UnknownValue.SPEED, UnknownValue.TIME));
                {
                    result = getValue(UnknownValue.SPEED) / getValue(UnknownValue.TIME);
                }

                if (isExist(UnknownValue.POWER, UnknownValue.MASS))
                {
                    result = getValue(UnknownValue.POWER) / getValue(UnknownValue.MASS);
                }

                break;

            case TIME:

                if (isExist(UnknownValue.SPEED, UnknownValue.DISTANCE))
                {
                    result = getValue(UnknownValue.DISTANCE) / getValue(UnknownValue.SPEED);
                }

                if (isExist(UnknownValue.SPEED, UnknownValue.BOOST))
                {
                    result = getValue(UnknownValue.SPEED) / getValue(UnknownValue.BOOST);
                }
                break;

            case POWER:

                if (isExist(UnknownValue.BOOST, UnknownValue.MASS))
                {
                    result = getValue(UnknownValue.MASS) * getValue(UnknownValue.BOOST);
                }
                if (isExist(UnknownValue.MASS))
                {
                    result = getValue(UnknownValue.MASS) * getValue(UnknownValue.ACCELERATION_GRAVITY);
                }
                if (isExist(UnknownValue.GRAVITY, UnknownValue.FRICTION_FORCE))
                {
                    result = getValue(UnknownValue.FRICTION_FORCE) * getValue(UnknownValue.GRAVITY);
                }
                if (isExist(UnknownValue.DISTANCE, UnknownValue.BODY_STIFFNESS))
                {
                    result = -1 * getValue(UnknownValue.BODY_STIFFNESS) * getValue(UnknownValue.DISTANCE);
                }
                break;


        }


        String out = result == 0 ? "ошибка :(" : value.name() + " = " + result;
        return super.getResult(out);
    }

    private int getValue(UnknownValue value)
    {
     return variableValue.get(value);
    }


    private boolean isExist(UnknownValue value)
    {
        return variableValue.containsKey(value) && variableValue.get(value) != 0;
    }
    private boolean isExist(UnknownValue value1, UnknownValue value2)
    {
        return (variableValue.containsKey(value1) && variableValue.get(value1) != 0) && isExist(value2);
    }



    public void setValue(UnknownValue value, int num)
    {
        variableValue.put(value, num);
    }

}
