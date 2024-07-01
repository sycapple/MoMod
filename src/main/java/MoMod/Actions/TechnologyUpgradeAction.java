package MoMod.Actions;

import MoMod.power.AbstractMoPower;
import MoMod.power.TechnologyLevelPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class TechnologyUpgradeAction extends AbstractGameAction {
    public TechnologyUpgradeAction() {

    }

    @Override
    public void update() {
        AbstractPower technologyLevel = AbstractDungeon.player.hasPower(TechnologyLevelPower.POWER_ID) ? AbstractDungeon.player.getPower(TechnologyLevelPower.POWER_ID) : null;
        if (technologyLevel != null) {
            if (technologyLevel.amount == 3) {
                AbstractDungeon.player.powers.remove(technologyLevel);
                this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new TechnologyLevelPower(AbstractDungeon.player)));
            } else {
                technologyLevel.amount++;
                technologyLevel.updateDescription();
            }
        } else {
            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new TechnologyLevelPower(AbstractDungeon.player, 1)));
        }
        this.isDone = true;
    }
}
