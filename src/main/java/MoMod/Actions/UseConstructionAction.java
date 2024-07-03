package MoMod.Actions;

import MoMod.cards.power.constrcution.SovietBarracks;
import MoMod.cards.power.constrcution.SovietWarFactory;
import MoMod.cards.power.constrcution.Walls;
import MoMod.power.SovietBarracksPower;
import MoMod.power.SovietWarFactoryPower;
import MoMod.power.WallsPower;
import MoMod.util.ConstructionPileManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class UseConstructionAction extends AbstractGameAction {
    public UseConstructionAction() {

    }

    @Override
    public void update() {
        CardGroup gp = ConstructionPileManager.getConstructionPile();
        AbstractCreature p = AbstractDungeon.player;
        AbstractCard c;
        for (int i = 0; i < gp.size(); i++) {
            c = gp.group.get(i);
            if (c instanceof SovietBarracks)
                p.getPower(SovietBarracksPower.POWER_ID).flash();
            if (c instanceof SovietWarFactory)
                p.getPower(SovietWarFactoryPower.POWER_ID).flash();
            if (c instanceof Walls)
                p.getPower(WallsPower.POWER_ID).flash();
            this.addToTop(new NewQueueCardAction(gp.group.get(i), target, false, true));
        }
        this.isDone = true;
    }
}
