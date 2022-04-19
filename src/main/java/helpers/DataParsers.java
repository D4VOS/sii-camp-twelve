package helpers;

public class DataParsers {
    public static float parsePrice(String price) {
        return Float.parseFloat(price.replaceAll("[^0-9?!\\.]", ""));
    }
}
