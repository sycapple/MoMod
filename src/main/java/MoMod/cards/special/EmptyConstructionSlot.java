package MoMod.cards.Special;

import MoMod.Enums.AbstractCardEnum;
import MoMod.cards.Abstract.MoCard;
import MoMod.util.MoModHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class EmptyConstructionSlot extends MoCard {
    public static final String ID = MoModHelper.makeID(EmptyConstructionSlot.class.getSimpleName());
    private static final CardStrings CARD_STRINGS;
    private static final int COST = -2;
    private static final AbstractCard.CardType TYPE;
    private static final AbstractCard.CardRarity RARITY;
    private static final AbstractCard.CardTarget TARGET;
    private static final AbstractCard.CardColor COLOR;

    static {
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.SPECIAL;
        TARGET = CardTarget.NONE;
        COLOR = AbstractCardEnum.SOVIET;
    }

    public EmptyConstructionSlot() {
        super(ID, false, CARD_STRINGS, COST, TYPE, COLOR, RARITY, TARGET);
    }

    public void limitedUpgrade() {
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public AbstractCard makeCopy() {
        AbstractCard c = new EmptyConstructionSlot();
        return c;
    }
}