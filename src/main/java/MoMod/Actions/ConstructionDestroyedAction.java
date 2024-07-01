package MoMod.Actions;

import MoMod.cards.Abstract.AbstractConstructionCard;
import MoMod.cards.attack.Conscript;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
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
        for (int i = 0; i < 2; i++)
            this.addToTop(new ConscriptReadyAction());
        this.addToTop(new GainEnergyAction(1));
        this.isDone = true;
    }

}

