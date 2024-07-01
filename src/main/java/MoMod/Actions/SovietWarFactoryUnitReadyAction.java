package MoMod.Actions;

import MoMod.cards.attack.RhinoHeavyTank;
import MoMod.power.SovietWarFactoryPower;
import MoMod.power.TechnologyLevelPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SovietWarFactoryUnitReadyAction extends AbstractGameAction {
    public SovietWarFactoryUnitReadyAction() {

    }

    @Override
    public void update() {
        AbstractCreature p = AbstractDungeon.player;
        int warFactoryAmount = p.hasPower(SovietWarFactoryPower.POWER_ID) ? p.getPower(SovietWarFactoryPower.POWER_ID).amount : 0;
        if (!p.hasPower(TechnologyLevelPower.POWER_ID) || p.getPower(TechnologyLevelPower.POWER_ID).amount == 0) {
            this.addToBot(new MakeTempCardInHandAction(new RhinoHeavyTank(), warFactoryAmount, false));
        }
        this.isDone = true;
    }
}
