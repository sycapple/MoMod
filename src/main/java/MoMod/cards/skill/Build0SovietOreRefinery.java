package MoMod.cards.skill;

import MoMod.Actions.ExhaustUnitReadyAction;
import MoMod.Enums.AbstractCardEnum;
import MoMod.cards.Abstract.AbstractBuildingConstructionCard;
import MoMod.cards.attack.WarMiner;
import MoMod.cards.power.constrcution.SovietWarFactory;
import MoMod.util.MoModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Build0SovietOreRefinery extends AbstractBuildingConstructionCard {
    public static final String ID = MoModHelper.makeID(Build0SovietOreRefinery.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 2;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardColor COLOR = AbstractCardEnum.SOVIET;

    public Build0SovietOreRefinery() {
        super(ID, false, CARD_STRINGS, COST, TYPE, COLOR, RARITY, TARGET);
        this.cardsToPreview = new WarMiner();
        this.setupMagicNumber(1);
    }

    public void limitedUpgrade() {
        this.upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        super.use(abstractPlayer, abstractMonster);
        this.addToBot(new ExhaustUnitReadyAction(new WarMiner(), this.magicNumber));
    }
}