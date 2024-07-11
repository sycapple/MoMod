package MoMod.patches;


import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

public class CyborgVanguardDamagePatch {
    public static Integer CyborgVanguardDamage;
    public static Integer CyborgVanguardDamageInitial = 0;

    public CyborgVanguardDamagePatch() {
    }

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "<class>"
    )
    public static class CyborgVanguardDamageField {
        //ConstructionPile
        //maxConstruction
        //上面这两个东西是hashmap,键是AbstractDungeon.player,就是当前玩家
        //CardGroup
        //这个好像是用来管理一组卡组的
        //卡片都放在CardGroup.group里面,类型是ArrayList
        public static SpireField<Integer> CyborgVanguardDamageAddOn = new SpireField(() -> {
            return CyborgVanguardDamageInitial;
        });


        public CyborgVanguardDamageField() {
        }

    }

}
