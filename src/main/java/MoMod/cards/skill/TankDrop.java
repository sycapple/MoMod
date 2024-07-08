package MoMod.cards.skill;

import MoMod.Actions.ExhaustUnitReadyAction;
import MoMod.Enums.AbstractCardEnum;
import MoMod.cards.Abstract.MoCard;
import MoMod.cards.attack.HydraCannon;
import MoMod.cards.attack.TankKiller;
import MoMod.util.MoModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TankDrop extends MoCard {
    public static final String ID = MoModHelper.makeID(TankDrop.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 3;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardColor COLOR = AbstractCardEnum.SOVIET;

    public TankDrop() {
        // 为了命名规范修改了变量名。这些参数具体的作用见下方
        super(ID, false, CARD_STRINGS, COST, TYPE, COLOR, RARITY, TARGET);
        this.cardsToPreview = new HydraCannon();
        this.setupMagicNumber(2);
        this.exhaust = true;
    }


    @Override
    public void limitedUpgrade() {
        this.upgradeBaseCost(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ExhaustUnitReadyAction(new HydraCannon(), this.magicNumber));
        this.addToBot(new ExhaustUnitReadyAction(new TankKiller(), this.magicNumber));
    }
}