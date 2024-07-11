package MoMod.util;


import MoMod.patches.CyborgVanguardDamagePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;



public class CyborgVanguardDamageAddonManager {
    public CyborgVanguardDamageAddonManager() {
    }


    public static void addDamage(int a) {
        CyborgVanguardDamagePatch.CyborgVanguardDamageField.CyborgVanguardDamageAddOn.set(AbstractDungeon.player, getDamage() + a);
    }

    public static void resetDamage() {
        CyborgVanguardDamagePatch.CyborgVanguardDamageField.CyborgVanguardDamageAddOn.set(AbstractDungeon.player, 0);
    }

    public static Integer getDamage() {
        if (AbstractDungeon.player != null)
            return CyborgVanguardDamagePatch.CyborgVanguardDamageField.CyborgVanguardDamageAddOn.get(AbstractDungeon.player);
        return 0;
    }
}
