package MoMod.Actions;

import MoMod.Enums.AbstractTagEnum;
import MoMod.cards.attack.*;
import MoMod.power.EliteRseservesPower;
import MoMod.power.SovietBarracksPower;
import MoMod.power.SovietWarFactoryPower;
import MoMod.power.TechnologyLevelPower;
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
        AbstractCreature p = AbstractDungeon.player;
        AbstractCard c = null;
        int technologyLevel = p.hasPower(TechnologyLevelPower.POWER_ID) ? p.getPower(TechnologyLevelPower.POWER_ID).amount : 0;
        if (technologyLevel == 0) {
            if (p.hasPower(EliteRseservesPower.POWER_ID))
                this.addToBot(new ConscriptReadyAction(true));
            else
                this.addToBot(new ConscriptReadyAction());
        } else {
            switch (technologyLevel) {
                case 1: {
                    c = new FlakTrooper();
                    break;
                }
                case 2: {
                    c = new ShockTrooper();
                    break;
                }
                case 3: {
                    c = new CyborgVanguard();
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
