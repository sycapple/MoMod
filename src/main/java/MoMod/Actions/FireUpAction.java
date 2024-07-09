package MoMod.Actions;

import MoMod.power.FireUpPower;
import MoMod.relics.WhitePhosphorus;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FireUpAction extends AbstractGameAction {
    public int fireUpVigour = 0;
    public int fireUpAmount = 0;
    AbstractCreature m = null;
    AbstractCreature p = null;

    public FireUpAction(AbstractCreature m, AbstractCreature p) {
        this.m = m;
        this.p = p;
    }

    public FireUpAction(AbstractCreature m, AbstractCreature p, int fireUpAmount) {
        this(m, p);
        this.fireUpAmount = fireUpAmount;
    }

    public FireUpAction(AbstractCreature m, AbstractCreature p, int fireUpAmount, int fireUpVigour) {
        this(m, p);
        this.fireUpAmount = fireUpAmount;
        this.fireUpVigour = fireUpVigour;
    }

    @Override
    public void update() {
        if (AbstractDungeon.player.hasRelic(WhitePhosphorus.ID)) {
            AbstractDungeon.player.getRelic(WhitePhosphorus.ID).flash();
            this.addToBot(new ApplyPowerAction(m, p, new FireUpPower(m, p, this.fireUpAmount * 2, this.fireUpVigour), this.fireUpAmount * 2));
        } else
            this.addToBot(new ApplyPowerAction(m, p, new FireUpPower(m, p, this.fireUpAmount, this.fireUpVigour), this.fireUpAmount));
        this.isDone = true;
    }
}
