package MoMod.Actions;

import MoMod.util.ConstructionPileManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.CardGroup;

public class ExhaustAllConstructionAction extends AbstractGameAction {
    public ExhaustAllConstructionAction() {

    }

    @Override
    public void update() {
        CardGroup gp = ConstructionPileManager.getConstructionPile();
        for (int i = gp.size() - 1; i >= 0; i--) {
            gp.moveToExhaustPile(gp.group.get(i));
        }
        this.isDone = true;
    }
}
