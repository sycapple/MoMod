package MoMod.Actions;

import MoMod.util.ConstructionPileManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class AddCardToConstructionPileAction extends AbstractGameAction {
    private AbstractCard c;
    private AbstractPower po;

    public AddCardToConstructionPileAction(AbstractCard C) {
        this.c = C;
    }

    public void update() {
        ConstructionPileManager.buildingConstruction(this.c);
        this.isDone = true;
    }
}
