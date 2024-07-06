package MoMod.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ExhaustUnitReadyAction extends AbstractGameAction {

    private AbstractCard c;
    private int amount = 0;

    public ExhaustUnitReadyAction(AbstractCard c, int amount) {
        this.c = c;
        this.amount = amount;
    }


    @Override
    public void update() {
        for (int i = 0; i < amount; i++) {
            AbstractCard card = c.makeCopy();
            if (c.upgraded)
                card.upgrade();
            card.exhaustOnUseOnce = true;
            AbstractDungeon.player.limbo.group.add(card);
            card.current_y = -200.0F * Settings.scale;
            card.target_x = (float) Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
            card.target_y = (float) Settings.HEIGHT / 2.0F;
            card.targetAngle = 0.0F;
            card.lighten(false);
            card.drawScale = 0.12F;
            card.targetDrawScale = 0.75F;
            card.applyPowers();
            target = AbstractDungeon.getRandomMonster();
            this.addToTop(new NewQueueCardAction(card, target, false, true));
            if (!Settings.FAST_MODE) {
                this.addToBot(new WaitAction(Settings.ACTION_DUR_MED));
            } else {
                this.addToBot(new WaitAction(Settings.ACTION_DUR_FASTER));
            }
        }
        this.isDone = true;
    }
}
