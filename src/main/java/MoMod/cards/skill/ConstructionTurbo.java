package MoMod.cards.skill;


import MoMod.Actions.UseConstructionAction;
import MoMod.Enums.AbstractCardEnum;
import MoMod.cards.Abstract.MoCard;
import MoMod.util.MoModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ConstructionTurbo extends MoCard {
    public static final String ID = MoModHelper.makeID(ConstructionTurbo.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 2;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardColor COLOR = AbstractCardEnum.SOVIET;

    public ConstructionTurbo() {
        // 为了命名规范修改了变量名。这些参数具体的作用见下方
        super(ID, false, CARD_STRINGS, COST, TYPE, COLOR, RARITY, TARGET);
    }


    @Override
    public void limitedUpgrade() {
        this.upgradeBaseCost(1);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new UseConstructionAction());
    }

}
