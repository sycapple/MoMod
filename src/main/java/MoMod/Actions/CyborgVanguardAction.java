//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package MoMod.Actions;

import MoMod.cards.attack.CyborgVanguard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Claw;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

public class CyborgVanguardAction extends AbstractGameAction {
    private AbstractCard card;

    public CyborgVanguardAction(AbstractCard card, int amount) {
        this.card = card;
        this.amount = amount;
    }

    public void update() {
        AbstractCard var10000 = this.card;
        var10000.baseDamage += this.amount;
        this.card.applyPowers();
        Iterator var1 = AbstractDungeon.player.discardPile.group.iterator();

        AbstractCard c;
        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (c instanceof Claw) {
                c.baseDamage += this.amount;
                c.applyPowers();
            }
        }

        var1 = AbstractDungeon.player.drawPile.group.iterator();

        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (c instanceof CyborgVanguard) {
                c.baseDamage += this.amount;
                c.applyPowers();
            }
        }

        var1 = AbstractDungeon.player.hand.group.iterator();

        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (c instanceof CyborgVanguard) {
                c.baseDamage += this.amount;
                c.applyPowers();
            }
        }

        this.isDone = true;
    }
}
