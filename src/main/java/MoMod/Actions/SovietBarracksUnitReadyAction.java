package MoMod.Actions;

import MoMod.Enums.AbstractTagEnum;
import MoMod.cards.attack.Arsonist;
import MoMod.cards.attack.Pyro;
import MoMod.cards.attack.RhinoHeavyTank;
import MoMod.cards.attack.ShockTrooper;
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
        int barracksAmount = p.hasPower(SovietBarracksPower.POWER_ID) ? p.getPower(SovietBarracksPower.POWER_ID).amount : 0;
        int technologyLevel = p.hasPower(TechnologyLevelPower.POWER_ID) ? p.getPower(TechnologyLevelPower.POWER_ID).amount : 0;
        if (technologyLevel == 0)
            for (int i = 0; i < barracksAmount; i++)
                this.addToBot(new ConscriptReadyAction());
        else {
            AbstractCard c = null;
            switch (technologyLevel) {
                case 1: {
                    c = new Pyro();
                    break;
                }
                case 2: {
                    c = new ShockTrooper();
                }
                case 3: {
                    c = new Arsonist();
                }
            }
            c.tags.add(AbstractTagEnum.TRAINED_UNIT);
            c.rawDescription = c.rawDescription + " NL 消耗 虚无 ";
            this.addToBot(new UnitReadyAction(c, barracksAmount));
        }
        this.isDone = true;
    }
}
