package MoMod.cards.skill;

import MoMod.Actions.AddCardToConstructionPileAction;
import MoMod.Enums.AbstractCardEnum;
import MoMod.cards.Abstract.AbstractBuildingConstructionCard;
import MoMod.cards.power.constrcution.IronCurtainDevice;
import MoMod.power.IronCurtainDevicePower;
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

public class Build0IronCurtainDevice extends AbstractBuildingConstructionCard {
    public static final String ID = MoModHelper.makeID(Build0IronCurtainDevice.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 3;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardColor COLOR = AbstractCardEnum.SOVIET;

    public Build0IronCurtainDevice() {
        super(ID, false, CARD_STRINGS, COST, TYPE, COLOR, RARITY, TARGET);
        this.setupMagicNumber(3);
        this.cardsToPreview = new IronCurtain();
        this.cardsToPreview.modifyCostForCombat(-this.cardsToPreview.cost);
        this.exhaust = true;
    }


    @Override
    public void limitedUpgrade() {
        this.upgradeMagicNumber(-1);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        for (AbstractCard card : getConstructionPile().group) {
            if (card instanceof IronCurtainDevice) {
                AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, CardCrawlGame.languagePack.getUIString(MoModHelper.makeID(Build0IronCurtainDevice.class.getSimpleName())).TEXT[0], true));
                return false;
            }
        }
        return true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToTop(new AddCardToConstructionPileAction(new IronCurtainDevice(this.magicNumber)));
        AbstractPower po = new IronCurtainDevicePower(p, this.magicNumber, this.uuid);
        this.addToTop(new ApplyPowerAction(p, p, po));
    }
}
