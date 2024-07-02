package MoMod.cards.attack;

import MoMod.Enums.AbstractCardEnum;
import MoMod.Enums.AbstractTagEnum;
import MoMod.cards.Abstract.TankUnitCard;
import MoMod.util.MoModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Buratino extends TankUnitCard {
    public static final String ID = MoModHelper.makeID(Buratino.class.getSimpleName());
    private static CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 2;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardColor COLOR = AbstractCardEnum.SOVIET;


    public Buratino() {
        // 为了命名规范修改了变量名。这些参数具体的作用见下方
        super(ID, false, CARD_STRINGS, COST, TYPE, COLOR, RARITY, TARGET);
        this.setupDamage(10);
    }

    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(5);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.damageToAllEnemies(AbstractGameAction.AttackEffect.FIRE);
    }

}
