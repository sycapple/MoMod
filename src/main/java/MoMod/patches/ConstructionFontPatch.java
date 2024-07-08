package MoMod.patches;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.StaticSpireField;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;

import java.util.HashMap;

public class ConstructionFontPatch {
    public ConstructionFontPatch() {
    }

    @SpirePatch(
            clz = FontHelper.class,
            method = "initialize",
            paramtypez = {}
    )
    public static class FontPatchTxwz {
        public FontPatchTxwz() {
        }

        @SpireInsertPatch(
                rloc = 280
        )
        public static void Insert(FileHandle ___fontFile, FreeTypeFontGenerator.FreeTypeFontParameter ___param, float ___fontScale, HashMap<String, FreeTypeFontGenerator> ___generators) {
            ___param.shadowOffsetX = 2;
            ___param.shadowOffsetY = 2;
            ___param.shadowColor = new Color(0.0F, 0.0F, 0.0F, 0.33F);
            ___param.gamma = 2.0F;
            ___param.borderGamma = 2.0F;
            ___param.borderStraight = true;
            ___param.borderColor = new Color(0.32156864F, 0.31764707F, 0.32156864F, 0.8F);
            ___param.borderWidth = 3.0F * Settings.scale;
            ___param.shadowOffsetX = 1;
            ___param.shadowOffsetY = 1;
            BitmapFont a = FontHelper.prepFont(39.0F, false);
            ConstructionFontPatch.ConstructionFontField.FightHardFont.set(a);
        }
    }

    @SpirePatch(
            clz = FontHelper.class,
            method = "<class>"
    )
    public static class ConstructionFontField {
        public static StaticSpireField<BitmapFont> FightHardFont = new StaticSpireField(() -> {
            return null;
        });

        public ConstructionFontField() {
        }
    }
}
