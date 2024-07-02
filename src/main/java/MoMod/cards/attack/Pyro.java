package MoMod.cards.attack;

import MoMod.Enums.AbstractCardEnum;
import MoMod.cards.Abstract.AbstractTrainedCard;
import MoMod.cards.Abstract.MoCard;
import MoMod.util.MoModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Pyro extends AbstractTrainedCard {
    public static final String ID = MoModHelper.makeID(Pyro.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardColor COLOR = AbstractCardEnum.SOVIET;

    public Pyro(boolean isTrained) {
        // 为了命名规范修改了变量名。这些参数具体的作用见下方
        super(ID, false, CARD_STRINGS, COST, TYPE, COLOR, RARITY, TARGET, isTrained);
        this.setupDamage(5);
    }

    public Pyro() {
        // 为了命名规范修改了变量名。这些参数具体的作用见下方
        super(ID, false, CARD_STRINGS, COST, TYPE, COLOR, RARITY, TARGET);
        this.setupDamage(5);
    }

    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(3);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.damageToAllEnemies(AbstractGameAction.AttackEffect.FIRE);
    }

}
