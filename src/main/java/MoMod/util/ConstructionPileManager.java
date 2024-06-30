package MoMod.util;

import MoMod.patches.PlayerHasConstructionPilePatch;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class ConstructionPileManager {
    public ConstructionPileManager() {

    }

    public static CardGroup getConstructionPile() {
        return (CardGroup) PlayerHasConstructionPilePatch.ConstructionPileField.ConstructionPile.get(AbstractDungeon.player);
    }

    public static Integer getMaxConstruction() {
        return (Integer)PlayerHasConstructionPilePatch.ConstructionPileField.maxConstruction.get(AbstractDungeon.player);
    }
    public static Integer getConstructionCnt() {
        return getConstructionPile().size();
    }
}
