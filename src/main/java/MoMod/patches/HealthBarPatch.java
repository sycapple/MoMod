package MoMod.patches;

import MoMod.power.FireUpPower;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class HealthBarPatch {
    public HealthBarPatch() {

    }

    @SpirePatch(
            clz = AbstractCreature.class,
            method = "renderRedHealthBar",
            paramtypez = {
                    SpriteBatch.class,
                    float.class,
                    float.class
            }
    )
    public static class HealthBarRemainPatch {
        public HealthBarRemainPatch() {

        }

        @SpireInsertPatch(
                loc = 1100
        )
        public static void insert(AbstractCreature __instance, SpriteBatch sb, float x, float y, float ___targetHealthBarWidth, float ___HEALTH_BAR_OFFSET_Y, float ___HEALTH_BAR_HEIGHT) {
            int fireUpAmt = __instance.hasPower(FireUpPower.POWER_ID) ? __instance.getPower(FireUpPower.POWER_ID).amount : 0;
            if (__instance.currentHealth > fireUpAmt) {
                if (fireUpAmt > 0 && __instance.hasPower("Intangible")) {
                    fireUpAmt = 1;
                }

                if (__instance.currentHealth > fireUpAmt) {
                    float w = 1.0F - (float) (__instance.currentHealth - fireUpAmt) / (float) __instance.currentHealth;
                    w *= ___targetHealthBarWidth;
                    if (__instance.currentHealth > 0) {
                        sb.draw(ImageMaster.HEALTH_BAR_L, x - ___HEALTH_BAR_HEIGHT, y + ___HEALTH_BAR_OFFSET_Y, ___HEALTH_BAR_HEIGHT, ___HEALTH_BAR_HEIGHT);
                    }
                    sb.draw(ImageMaster.HEALTH_BAR_B, x, y + ___HEALTH_BAR_OFFSET_Y, ___targetHealthBarWidth - w, ___HEALTH_BAR_HEIGHT);
                    sb.draw(ImageMaster.HEALTH_BAR_R, x + ___targetHealthBarWidth - w, y + ___HEALTH_BAR_OFFSET_Y, ___HEALTH_BAR_HEIGHT, ___HEALTH_BAR_HEIGHT);
                }
            }
        }
    }

    @SpirePatch(
            clz = AbstractCreature.class,
            method = "renderHealth",
            paramtypez = {
                    SpriteBatch.class
            }
    )
    public static class RenderHealthBarPatch {
        public RenderHealthBarPatch() {

        }

        @SpireInsertPatch(
                loc = 1006
        )
        public static void insert(AbstractCreature __instance, SpriteBatch sb, float ___HEALTH_BAR_HEIGHT, float ___targetHealthBarWidth, float ___HEALTH_BAR_OFFSET_Y, float ___hbYOffset, Color ___greenHbBarColor) {
            float xx = __instance.hb.cX - __instance.hb.width / 2.0F;
            float yy = __instance.hb.cY - __instance.hb.height / 2.0F + ___hbYOffset;
            if (__instance.hasPower(FireUpPower.POWER_ID)) {
                System.out.println("开始染色");
                Color flameColor = new Color(1f, 0.49f, 25 / 255f, 0.09f);
                sb.setColor(flameColor);
                if (__instance.currentHealth > 0) {
                    sb.draw(ImageMaster.HEALTH_BAR_L, xx - ___HEALTH_BAR_HEIGHT, yy + ___HEALTH_BAR_OFFSET_Y, ___HEALTH_BAR_HEIGHT, ___HEALTH_BAR_HEIGHT);
                }

                sb.draw(ImageMaster.HEALTH_BAR_B, xx, yy + ___HEALTH_BAR_OFFSET_Y, ___targetHealthBarWidth, ___HEALTH_BAR_HEIGHT);
                sb.draw(ImageMaster.HEALTH_BAR_R, xx + ___targetHealthBarWidth, yy + ___HEALTH_BAR_OFFSET_Y, ___HEALTH_BAR_HEIGHT, ___HEALTH_BAR_HEIGHT);
            }
        }
    }

}
