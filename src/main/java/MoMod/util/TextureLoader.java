package MoMod.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;

import java.util.HashMap;
import java.util.Map;

public class TextureLoader {
    private static HashMap<String, Texture> textures = new HashMap<>();

    public static Texture getTexture(String textureString) {
        if (textures.get(textureString) == null)
            try {
                loadTexture(textureString, true);
            } catch (GdxRuntimeException e) {
                return getTexture("downfallResources/images/ui/missing_texture.png");
            }
        return textures.get(textureString);
    }

    private static void loadTexture(String textureString) throws GdxRuntimeException {
        loadTexture(textureString, false);
    }

    private static void loadTexture(String textureString, boolean linearFilter) throws GdxRuntimeException {
        Texture texture = new Texture(textureString);
        if (linearFilter) {
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        } else {
            texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        }
        textures.put(textureString, texture);
    }

    public static boolean testTexture(String filePath) {
        return Gdx.files.internal(filePath).exists();
    }

    public static TextureAtlas.AtlasRegion getTextureAsAtlasRegion(String textureString) {
        Texture texture = getTexture(textureString);
        return new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
    }

    @SpirePatch(clz = Texture.class, method = "dispose")
    public static class DisposeListener {
        @SpirePrefixPatch
        public static void DisposeListenerPatch(Texture __instance) {
            TextureLoader.textures.entrySet().removeIf(entry -> {
                if (((Texture) entry.getValue()).equals(__instance))
                    System.out.println("TextureLoader | Removing Texture: " + (String) entry.getKey());
                return ((Texture) entry.getValue()).equals(__instance);
            });
        }
    }
}

