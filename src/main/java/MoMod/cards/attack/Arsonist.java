package MoMod.cards.attack;

import MoMod.Enums.AbstractCardEnum;
import MoMod.cards.Abstract.AbstractTrainedCard;
import MoMod.cards.Abstract.MoCard;
import MoMod.power.FireUpPower;
import MoMod.util.MoModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;

public class Arsonist extends AbstractTrainedCard {
    public static final String ID = MoModHelper.makeID(Arsonist.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardColor COLOR = AbstractCardEnum.SOVIET;

    public Arsonist(boolean isTrained) {
        // 为了命名规范修改了变量名。这些参数具体的作用见下方
        super(ID, false, CARD_STRINGS, COST, TYPE, COLOR, RARITY, TARGET, isTrained);
        this.setupMagicNumber(3);
    }

    public Arsonist() {
        // 为了命名规范修改了变量名。这些参数具体的作用见下方
        super(ID, false, CARD_STRINGS, COST, TYPE, COLOR, RARITY, TARGET);
        this.setupMagicNumber(3);
    }

    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeMagicNumber(3);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //todo:纵火狂use
        this.addToBot(new ApplyPowerAction(m, p, new FireUpPower(m, p, this.magicNumber), this.magicNumber));
    }
}
