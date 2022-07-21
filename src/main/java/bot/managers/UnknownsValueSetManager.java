package bot.managers;

public class UnknownsValueSetManager
{
    public UnknownsValueSetManager() {}

    public UnknowValue setUnknownValue(String value)
    {
        UnknowValue unknowValue = UnknowValue.ERROR;
        value = value.charAt(0) + "";
        switch (value)
        {
            case "S":
                unknowValue = UnknowValue.DISTANCE;
                break;
            case "V":
                unknowValue = UnknowValue.SPEED;
                break;
            case "t":
                unknowValue = UnknowValue.TIME;
                break;
            case "a":
                unknowValue = UnknowValue.BOOST;
                break;
        }

        return unknowValue;
    }
}
