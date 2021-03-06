package helpers.data;

public class DataUtils {
    public static float parsePrice(String price) {
        return Float.parseFloat(price.replaceAll("[^0-9?!\\.]", ""));
    }

    public static float round(float number, int scale) {
        int pow = 10;
        for (int i = 1; i < scale; i++)
            pow *= 10;
        float tmp = number * pow;
        return ((float) ((int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp))) / pow;
    }
}
