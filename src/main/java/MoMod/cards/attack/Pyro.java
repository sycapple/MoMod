package MoMod.cards.attack;

import MoMod.Enums.AbstractCardEnum;
import MoMod.cards.Abstract.InfantryUnitCard;
import MoMod.power.FireUpPower;
import MoMod.relics.WhitePhosphorus;
import MoMod.util.MoModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Pyro extends InfantryUnitCard {
    public static final String ID = MoModHelper.makeID(Pyro.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardColor COLOR = AbstractCardEnum.SOVIET;


    public Pyro() {
        // 为了命名规范修改了变量名。这些参数具体的作用见下方
        super(ID, false, CARD_STRINGS, COST, TYPE, COLOR, RARITY, TARGET);
        this.setupDamage(3);
        this.setupMagicNumber(5);
    }

    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(2);
        this.upgradeMagicNumber(3);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(AbstractDungeon.player, this.damage), AbstractGameAction.AttackEffect.FIRE));
        if (AbstractDungeon.player.hasRelic(WhitePhosphorus.ID)) {
            AbstractDungeon.player.getRelic(WhitePhosphorus.ID).flash();
            this.addToBot(new ApplyPowerAction(m, p, new FireUpPower(m, p, this.magicNumber * 2), this.magicNumber * 2));
        } else
            this.addToBot(new ApplyPowerAction(m, p, new FireUpPower(m, p, this.magicNumber), this.magicNumber));
    }

}
