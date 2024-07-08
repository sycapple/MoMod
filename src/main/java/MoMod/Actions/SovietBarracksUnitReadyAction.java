package MoMod.Actions;

import MoMod.cards.attack.Conscript;
import MoMod.cards.power.constrcution.SovietBarracks;
import MoMod.power.CapacityUpgradingPower;
import MoMod.power.GearChangePower;
import MoMod.power.SovietBarracksPower;
import MoMod.util.UnitToTrain;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
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
        }
        if (c instanceof Conscript)
            this.addToBot(new ExhaustUnitReadyAction(c, sovietBarracks));
        else
            this.addToBot(new UnitReadyAction(c, sovietBarracks));
        this.isDone = true;
    }
}
