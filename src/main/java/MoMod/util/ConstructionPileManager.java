package MoMod.util;


import MoMod.patches.PlayerHasConstructionPilePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;


public class ConstructionPileManager {
    public ConstructionPileManager() {
    }


    public static void sayIsFull() {
        AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, CardCrawlGame.languagePack.getUIString(MoModHelper.makeID("AddCardToConstructionPileAction")).TEXT[0], true));
    }

    public static void addMaxConstruction(int a) {
        PlayerHasConstructionPilePatch.ConstructionPileField.maxConstruction.set(AbstractDungeon.player, Math.min(getMaxConstruction() + a, PlayerHasConstructionPilePatch.MaxConstructionAvailable));
    }

    public static void resetMaxConstruction() {
        PlayerHasConstructionPilePatch.ConstructionPileField.maxConstruction.set(AbstractDungeon.player, PlayerHasConstructionPilePatch.MaxConstructionInitial);
        ;
    }

    public static CardGroup getConstructionPile() {
        return (CardGroup) PlayerHasConstructionPilePatch.ConstructionPileField.ConstructionPile.get(AbstractDungeon.player);
    }


    public static Integer getMaxConstruction() {
        return (Integer) PlayerHasConstructionPilePatch.ConstructionPileField.maxConstruction.get(AbstractDungeon.player);
    }

    public static void buildingConstruction(AbstractCard c) {
        CardGroup mp = ConstructionPileManager.getConstructionPile();
        if (mp.size() >= ConstructionPileManager.getMaxConstruction()) {
            AbstractCard lc = mp.group.get(ConstructionPileManager.getMaxConstruction() - 1);
            mp.moveToExhaustPile(lc);
        }
        mp.group.add(0, c);
    }

    public static Integer getConstructionCnt() {
        return getConstructionPile().size();
    }
}
