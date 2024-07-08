package MoMod.cards.attack;

import MoMod.Actions.MigBombingAction;
import MoMod.Enums.AbstractCardEnum;
import MoMod.cards.Abstract.InfantryUnitCard;
import MoMod.util.MoModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class Boris extends InfantryUnitCard {
    public static final String ID = MoModHelper.makeID(Boris.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = -1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardColor COLOR = AbstractCardEnum.SOVIET;


    public Boris() {
        // 为了命名规范修改了变量名。这些参数具体的作用见下方
        super(ID, false, CARD_STRINGS, COST, TYPE, COLOR, RARITY, TARGET);
        this.setupDamage(7);
        this.isMultiDamage = true;
    }

    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(3);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MigBombingAction(p, this.multiDamage, this.damageTypeForTurn, this.freeToPlayOnce, this.energyOnUse));
    }

}
