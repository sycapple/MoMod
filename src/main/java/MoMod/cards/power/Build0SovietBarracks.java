package MoMod.cards.power;

import MoMod.Enums.AbstractCardEnum;
import MoMod.cards.Abstract.AbstractBuildingConstructionCard;
import MoMod.cards.Abstract.AbstractConstructionCard;
import MoMod.cards.attack.AttackDog;
import MoMod.util.MoModHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Build0SovietBarracks extends AbstractBuildingConstructionCard {
    public static final String ID = MoModHelper.makeID(Build0SovietBarracks.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 2;
    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final AbstractCard.CardRarity RARITY = CardRarity.BASIC;
    private static final AbstractCard.CardTarget TARGET = CardTarget.SELF;
    private static final AbstractCard.CardColor COLOR = AbstractCardEnum.SOVIET;

    public Build0SovietBarracks() {
        super(ID, false, CARD_STRINGS, COST, TYPE, COLOR, RARITY, TARGET);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        super.use(abstractPlayer, abstractMonster);
    }
}
