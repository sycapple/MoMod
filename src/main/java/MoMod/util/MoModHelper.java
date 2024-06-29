package MoMod.util;


public class MoModHelper {
    public static String MOD_ID = "MoMod";

    public static String makeID(String id) {
        return MOD_ID + ":" + id;
    }


    public static String assetPath(String path) {
        return MOD_ID + "Resources/" + path;
    }

}
