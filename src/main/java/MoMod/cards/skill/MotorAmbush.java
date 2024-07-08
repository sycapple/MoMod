package MoMod.cards.skill;

import MoMod.Actions.ExhaustUnitReadyAction;
import MoMod.Enums.AbstractCardEnum;
import MoMod.cards.Abstract.MoCard;
import MoMod.cards.attack.MortarQuad;
import MoMod.util.MoModHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MotorAmbush extends MoCard {
    public static final String ID = MoModHelper.makeID(MotorAmbush.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 2;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardColor COLOR = AbstractCardEnum.SOVIET;

    public MotorAmbush() {
        // 为了命名规范修改了变量名。这些参数具体的作用见下方
        super(ID, false, CARD_STRINGS, COST, TYPE, COLOR, RARITY, TARGET);
        this.cardsToPreview = new MortarQuad();
        if (this.upgraded)
            this.cardsToPreview.upgrade();
        this.setupMagicNumber(2);
    }


    @Override
    public void limitedUpgrade() {
        this.upgradeDescription(CARD_STRINGS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard c = new MortarQuad();
        if (this.upgraded)
            c.upgrade();
        this.addToBot(new ExhaustUnitReadyAction(c, this.magicNumber));
    }
}