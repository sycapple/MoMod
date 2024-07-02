package MoMod.util;


public class MoModHelper {

    public static String makeID(String id) {
        return "momod" + ":" + id;
    }


    public static String assetPath(String path) {
        return "MoMod" + "Resources/" + path;
    }

}
