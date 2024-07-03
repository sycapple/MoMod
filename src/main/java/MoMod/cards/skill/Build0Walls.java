package MoMod.cards.skill;

import MoMod.cards.Abstract.AbstractBuildingConstructionCard;
import MoMod.cards.Abstract.MoCard;
import MoMod.util.MoModHelper;
import MoMod.Enums.AbstractCardEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Build0Walls extends AbstractBuildingConstructionCard {
    public static final String ID = MoModHelper.makeID(Build0Walls.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 1;
    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final AbstractCard.CardRarity RARITY = CardRarity.BASIC;
    private static final AbstractCard.CardTarget TARGET = CardTarget.SELF;
    private static final AbstractCard.CardColor COLOR = AbstractCardEnum.SOVIET;

    public Build0Walls() {
        super(ID, true, CARD_STRINGS, COST, TYPE, COLOR, RARITY, TARGET);
    }


    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
    }
}
