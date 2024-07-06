package MoMod.cards.skill;

import MoMod.Actions.AddCardToConstructionPileAction;
import MoMod.Enums.AbstractCardEnum;
import MoMod.cards.Abstract.AbstractBuildingConstructionCard;
import MoMod.cards.power.constrcution.EMPControlStation;
import MoMod.cards.power.constrcution.TacticalNukeSilo;
import MoMod.power.EMPControlStationPower;
import MoMod.power.TacticalNukeSiloPower;
import MoMod.util.MoModHelper;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

import static MoMod.util.ConstructionPileManager.getConstructionPile;

public class Build0TacticalNukeSilo extends AbstractBuildingConstructionCard {
    public static final String ID = MoModHelper.makeID(Build0TacticalNukeSilo.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 3;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardColor COLOR = AbstractCardEnum.SOVIET;

    public Build0TacticalNukeSilo() {
        super(ID, false, CARD_STRINGS, COST, TYPE, COLOR, RARITY, TARGET);
        this.setupMagicNumber(5);
        this.cardsToPreview = new TacticalNukeSilo(this.magicNumber);
        this.exhaust = true;
    }


    @Override
    public void limitedUpgrade() {
        this.upgradeMagicNumber(-1);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        for (AbstractCard card : getConstructionPile().group) {
            if (card instanceof TacticalNukeSilo) {
                AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, CardCrawlGame.languagePack.getUIString(MoModHelper.makeID(Build0TacticalNukeSilo.class.getSimpleName())).TEXT[0], true));
                return false;
            }
        }
        return true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToTop(new AddCardToConstructionPileAction(new TacticalNukeSilo(this.magicNumber)));
        AbstractPower po = new TacticalNukeSiloPower(p, this.magicNumber, this.uuid);
        this.addToTop(new ApplyPowerAction(p, p, po));
    }
}
