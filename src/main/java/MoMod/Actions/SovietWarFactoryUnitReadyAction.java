package MoMod.Actions;

import MoMod.Enums.AbstractTagEnum;
import MoMod.cards.attack.*;
import MoMod.power.SovietWarFactoryPower;
import MoMod.power.TechnologyLevelPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
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
        if (technologyLevel == 0)
            for (int i = 0; i < warFactoryAmount; i++)
                this.addToBot(new RhinoHeavyTankReadyAction());
        else {
            AbstractCard c = null;
            switch (technologyLevel) {
                case 1: {
                    c = new Buratino();
                    break;
                }
                case 2: {
                    c = new TeslaCruiser();
                }
                case 3: {
                    c = new IronDragon();
                }
            }
            c.tags.add(AbstractTagEnum.TRAINED_UNIT);
            c.rawDescription = c.rawDescription + " NL 消耗 虚无 ";
            c.cost = c.cost - 1;
            this.addToBot(new UnitReadyAction(c, warFactoryAmount));
        }
        this.isDone = true;
    }
}
