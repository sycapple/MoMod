package MoMod.patches;

import MoMod.cards.Abstract.AbstractConstructionCard;
import MoMod.util.ConstructionPileManager;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

public class PlayerHasConstructionPilePatch {
    public static CardGroup.CardGroupType CONSTRUCTION_PILE;
    public static Integer MaxConstructionAvailable = 8;
    public static Integer MaxConstructionInitial = 4;

    public PlayerHasConstructionPilePatch() {
    }

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "<class>"
    )
    public static class ConstructionPileField {
        //ConstructionPile
        //maxConstruction
        //上面这两个东西是hashmap,键是AbstractDungeon.player,就是当前玩家
        // Todo：猜想SpireField这个按照角色生成键值对
        //CardGroup
        //这个好像是用来管理一组卡组的
        //卡片都放在CardGroup.group里面,类型是ArrayList
        public static SpireField<CardGroup> ConstructionPile = new SpireField(() -> {
            return new CardGroup(PlayerHasConstructionPilePatch.CONSTRUCTION_PILE);
        });
        public static SpireField<Integer> maxConstruction = new SpireField(() -> {
            return PlayerHasConstructionPilePatch.MaxConstructionInitial;
        });

        public ConstructionPileField() {
        }

    }

}
