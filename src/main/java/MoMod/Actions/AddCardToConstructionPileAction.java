package MoMod.Actions;

import MoMod.modcore.MoMod;
import MoMod.power.SovietBarracksPower;
import MoMod.util.ConstructionPileManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
