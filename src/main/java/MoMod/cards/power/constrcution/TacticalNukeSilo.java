package MoMod.cards.power.constrcution;

import MoMod.Actions.ConstructionDestroyedAction;
import MoMod.Enums.AbstractCardEnum;
import MoMod.cards.Abstract.AbstractConstructionCard;
import MoMod.cards.attack.TacticalNuke;
import MoMod.power.TacticalNukeSiloPower;
import MoMod.util.MoModHelper;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class TacticalNukeSilo extends AbstractConstructionCard {
    public static final String ID = MoModHelper.makeID(TacticalNukeSilo.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardColor COLOR = AbstractCardEnum.SOVIET;

    public TacticalNukeSilo(int truns) {
        //为了命名规范修改了变量名。这些参数具体的作用见下方
        super(ID, false, CARD_STRINGS, COLOR, RARITY);
        this.cardsToPreview = new TacticalNuke();
        this.cardsToPreview.modifyCostForCombat(-this.cardsToPreview.cost);
        this.setupMagicNumber(truns);
    }


    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }


    @Override
    public void triggerOnExhaust() {
        this.addToBot(new ConstructionDestroyedAction());
        AbstractCreature p = AbstractDungeon.player;
        AbstractPower po = AbstractDungeon.player.getPower(TacticalNukeSiloPower.POWER_ID);
        this.addToBot(new ReducePowerAction(p, p, po, po.amount));
        this.addToBot(new RemoveSpecificPowerAction(p, p, po.ID));
//        for (AbstractPower po : p.powers) {
//            if (po instanceof EMPControlStationPower && ((EMPControlStationPower) po).Cuuid == this.uuid) {
//                this.addToBot(new ReducePowerAction(p, p, po, po.amount));
//            }
//        }
    }
}
