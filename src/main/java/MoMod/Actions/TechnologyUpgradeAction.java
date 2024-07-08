package MoMod.Actions;

import MoMod.power.LargeIterationPlanPower;
import MoMod.power.TechnologyLevelPower;
import MoMod.util.MoModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;

public class TechnologyUpgradeAction extends AbstractGameAction {
    public TechnologyUpgradeAction() {

    }

    @Override
    public void update() {
        AbstractCreature p = AbstractDungeon.player;
        AbstractPower technologyLevel = p.hasPower(TechnologyLevelPower.POWER_ID) ? p.getPower(TechnologyLevelPower.POWER_ID) : null;
        if (technologyLevel != null) {
            if (p.hasPower(LargeIterationPlanPower.POWER_ID)) {
                if (technologyLevel.amount == 3) {
                    AbstractDungeon.player.powers.remove(technologyLevel);
                    this.addToBot(new ApplyPowerAction(p, p, new TechnologyLevelPower(p)));
                    this.addToBot(new VFXAction(p, new InflameEffect(p), 1.0F));
                    this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 10), 10));
                    this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 10), 10));
                } else {
                    technologyLevel.flash();
                    technologyLevel.amount++;
                    this.addToTop(new CardToPreviewUpdateAction());
                    technologyLevel.updateDescription();
                }
            } else {
                if (technologyLevel.amount == 3) {
                    technologyLevel.flash();
                    AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, CardCrawlGame.languagePack.getUIString(MoModHelper.makeID(TechnologyUpgradeAction.class.getSimpleName())).TEXT[0], true));
                } else {
                    technologyLevel.flash();
                    technologyLevel.amount++;
                    this.addToTop(new CardToPreviewUpdateAction());
                    technologyLevel.updateDescription();
                }
            }
        } else {
            this.addToTop(new ApplyPowerAction(p, p, new TechnologyLevelPower(p, 1)));
            this.addToTop(new CardToPreviewUpdateAction());
        }
        this.isDone = true;
    }
}
