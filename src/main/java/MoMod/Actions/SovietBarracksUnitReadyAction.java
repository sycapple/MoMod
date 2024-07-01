package MoMod.Actions;

import MoMod.cards.attack.RhinoHeavyTank;
import MoMod.power.SovietBarracksPower;
import MoMod.power.SovietWarFactoryPower;
import MoMod.power.TechnologyLevelPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SovietBarracksUnitReadyAction extends AbstractGameAction {
    public SovietBarracksUnitReadyAction() {

    }

    @Override
    public void update() {
        AbstractCreature p = AbstractDungeon.player;
        int barracksAmount = p.hasPower(SovietBarracksPower.POWER_ID) ? p.getPower(SovietBarracksPower.POWER_ID).amount : 0;
        if (!p.hasPower(TechnologyLevelPower.POWER_ID) || p.getPower(TechnologyLevelPower.POWER_ID).amount == 0) {
            for (int i = 0; i < barracksAmount; i++)
                this.addToTop(new ConscriptReadyAction());
        }
        this.isDone = true;
    }
}
