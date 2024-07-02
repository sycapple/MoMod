package MoMod.Actions;

import MoMod.cards.attack.*;
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
        int technologyLevel = p.hasPower(TechnologyLevelPower.POWER_ID) ? p.getPower(TechnologyLevelPower.POWER_ID).amount : 0;
        switch (technologyLevel) {
            case 0: {
                for (int i = 0; i < warFactoryAmount; i++)
                    this.addToBot(new RhinoHeavyTankReadyAction());
                break;
            }
            case 1: {
                this.addToBot(new MakeTempCardInHandAction(new Buratino(true), warFactoryAmount, false));
                break;
            }
            case 2: {
                this.addToBot(new MakeTempCardInHandAction(new TeslaCruiser(true), warFactoryAmount, false));
                break;
            }
            case 3: {
                this.addToBot(new MakeTempCardInHandAction(new IronDragon(true), warFactoryAmount, false));
                break;
            }
        }
        this.isDone = true;
    }
}
