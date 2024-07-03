package MoMod.Actions;

import MoMod.Enums.AbstractTagEnum;
import MoMod.cards.attack.*;
import MoMod.power.EliteRseservesPower;
import MoMod.power.SovietWarFactoryPower;
import MoMod.power.TechnologyLevelPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SovietWarFactoryUnitReadyAction extends AbstractGameAction {
    public SovietWarFactoryUnitReadyAction() {

    }

    @Override
    public void update() {
        AbstractCreature p = AbstractDungeon.player;
        AbstractCard c = null;
        int technologyLevel = p.hasPower(TechnologyLevelPower.POWER_ID) ? p.getPower(TechnologyLevelPower.POWER_ID).amount : 0;
        if (technologyLevel == 0) {
            if (p.hasPower(EliteRseservesPower.POWER_ID))
                this.addToBot(new FuryDroneReadyAction(true));
            else
                this.addToBot(new FuryDroneReadyAction());
        } else {
            switch (technologyLevel) {
                case 1: {
                    c = new RhinoHeavyTank();
                    break;
                }
                case 2: {
                    c = new TeslaCruiser();
                    break;
                }
                case 3: {
                    c = new ApocalypseTank();
                }
            }
            c.tags.add(AbstractTagEnum.TRAINED_UNIT);
            if (p.hasPower(EliteRseservesPower.POWER_ID))
                c.upgrade();
            this.addToBot(new UnitReadyAction(c, 1));
        }
        this.isDone = true;
    }
}
