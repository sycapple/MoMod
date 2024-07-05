package MoMod.Actions;

import MoMod.cards.power.constrcution.SovietBarracks;
import MoMod.cards.power.constrcution.SovietWarFactory;
import MoMod.cards.skill.FuryDrone;
import MoMod.power.CapacityUpgradingPower;
import MoMod.power.GearChangePower;
import MoMod.power.SovietWarFactoryPower;
import MoMod.util.UnitToTrain;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SovietWarFactoryUnitReadyAction extends AbstractGameAction {
    public SovietWarFactoryUnitReadyAction() {

    }

    @Override
    public void update() {
        AbstractCard c = new UnitToTrain(new SovietWarFactory(), AbstractDungeon.player).getUnit();
        int sovietWarFactory = AbstractDungeon.player.getPower(SovietWarFactoryPower.POWER_ID).amount;
        if (AbstractDungeon.player.hasPower(GearChangePower.POWER_ID))
            sovietWarFactory = 0;
        if (AbstractDungeon.player.hasPower(CapacityUpgradingPower.POWER_ID))
            sovietWarFactory *= (AbstractDungeon.player.getPower(CapacityUpgradingPower.POWER_ID).amount + 1);
        if (c instanceof FuryDrone)
            this.addToBot(new ExhaustUnitReadyAction(c, sovietWarFactory));
        else
            this.addToBot(new UnitReadyAction(c, sovietWarFactory));
        this.isDone = true;
    }

}
