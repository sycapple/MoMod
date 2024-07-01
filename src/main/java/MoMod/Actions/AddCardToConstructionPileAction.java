package MoMod.Actions;

import MoMod.modcore.MoMod;
import MoMod.util.ConstructionPileManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddCardToConstructionPileAction extends AbstractGameAction {
    private AbstractCard c;

    public AddCardToConstructionPileAction(AbstractCard C) {
        this.c = C;
    }

    public void update() {
        ConstructionPileManager.addConstruction(this.c);
        this.isDone = true;
    }
}
