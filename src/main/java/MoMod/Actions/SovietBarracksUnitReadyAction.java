package MoMod.Actions;

import MoMod.cards.attack.Arsonist;
import MoMod.cards.attack.Pyro;
import MoMod.cards.attack.RhinoHeavyTank;
import MoMod.cards.attack.ShockTrooper;
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
        int technologyLevel = p.hasPower(TechnologyLevelPower.POWER_ID) ? p.getPower(TechnologyLevelPower.POWER_ID).amount : 0;
        switch (technologyLevel) {
            case 0: {
                for (int i = 0; i < barracksAmount; i++)
                    this.addToBot(new ConscriptReadyAction());
                break;
            }
            case 1: {
                this.addToBot(new MakeTempCardInHandAction(new Pyro(true), barracksAmount, false));
                break;
            }
            case 2: {
                this.addToBot(new MakeTempCardInHandAction(new ShockTrooper(true), barracksAmount, false));
                break;
            }
            case 3: {
                this.addToBot(new MakeTempCardInHandAction(new Arsonist(true), barracksAmount, false));
                break;
            }
        }
        this.isDone = true;
    }
}
