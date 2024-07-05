package MoMod.Actions;

import MoMod.Enums.AbstractTagEnum;
import MoMod.cards.attack.*;
import MoMod.cards.power.constrcution.SovietBarracks;
import MoMod.power.*;
import MoMod.util.UnitToTrain;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SovietBarracksUnitReadyAction extends AbstractGameAction {
    public SovietBarracksUnitReadyAction() {

    }

    @Override
    public void update() {
        AbstractCard c = new UnitToTrain(new SovietBarracks(), AbstractDungeon.player).getUnit();
        int sovietBarracks = AbstractDungeon.player.getPower(SovietBarracksPower.POWER_ID).amount;
        if (AbstractDungeon.player.hasPower(GearChangePower.POWER_ID))
            sovietBarracks = 0;
        if (AbstractDungeon.player.hasPower(CapacityUpgradingPower.POWER_ID)) {
            sovietBarracks *= (AbstractDungeon.player.getPower(CapacityUpgradingPower.POWER_ID).amount + 1);
            System.out.println(sovietBarracks);
        }
        if (c instanceof Conscript)
            this.addToBot(new ExhaustUnitReadyAction(c, sovietBarracks));
        else
            this.addToBot(new UnitReadyAction(c, sovietBarracks));
        this.isDone = true;
    }
}
