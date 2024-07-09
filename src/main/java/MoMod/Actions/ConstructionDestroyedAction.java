package MoMod.Actions;

import MoMod.cards.Abstract.AbstractConstructionCard;
import MoMod.cards.attack.Conscript;
import MoMod.power.EliteRseservesPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ConstructionDestroyedAction extends AbstractGameAction {
    public static AbstractConstructionCard c = null;

    public ConstructionDestroyedAction() {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.WAIT;
        this.source = AbstractDungeon.player;
        this.target = AbstractDungeon.getRandomMonster();
        this.c = c;
    }

    public void update() {
        AbstractCard c = new Conscript();
        if (AbstractDungeon.player.hasPower(EliteRseservesPower.POWER_ID))
            c.upgrade();
        this.addToBot(new ExhaustUnitReadyAction(new Conscript(), 2));
        this.addToBot(new GainEnergyAction(1));
        this.isDone = true;
    }

}

