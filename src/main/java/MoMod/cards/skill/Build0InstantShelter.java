package MoMod.cards.skill;

import MoMod.Enums.AbstractCardEnum;
import MoMod.cards.Abstract.AbstractBuildingConstructionCard;
import MoMod.cards.power.constrcution.InstantShelter;
import MoMod.util.MoModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Build0InstantShelter extends AbstractBuildingConstructionCard {
    public static final String ID = MoModHelper.makeID(Build0InstantShelter.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardColor COLOR = AbstractCardEnum.SOVIET;

    public Build0InstantShelter() {
        //todo:应急碉堡技能卡贴图
        super(ID, true, CARD_STRINGS, COST, TYPE, COLOR, RARITY, TARGET);
        this.setupMagicNumber(3);
        this.cardsToPreview = new InstantShelter(this.magicNumber);
    }


    @Override
    public void limitedUpgrade() {
        this.upgradeMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
    }
}
