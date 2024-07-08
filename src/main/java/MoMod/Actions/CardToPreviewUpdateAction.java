package MoMod.Actions;

import MoMod.cards.Abstract.AbstractConstructionCard;
import MoMod.util.ConstructionPileManager;
import MoMod.util.UnitToTrain;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class CardToPreviewUpdateAction extends AbstractGameAction {
    public CardToPreviewUpdateAction() {

    }

    @Override
    public void update() {
        CardGroup gp = ConstructionPileManager.getConstructionPile();
        for (AbstractCard card : gp.group) {
            card.cardsToPreview = new UnitToTrain((AbstractConstructionCard) card, AbstractDungeon.player).getUnit();}
        this.isDone = true;
    }
}
