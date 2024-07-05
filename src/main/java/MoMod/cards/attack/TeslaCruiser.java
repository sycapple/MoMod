package MoMod.cards.attack;

import MoMod.Enums.AbstractCardEnum;
import MoMod.cards.Abstract.TankUnitCard;
import MoMod.power.ElectromagneticParalysisPower;
import MoMod.power.OverChargePower;
import MoMod.util.MoModHelper;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

import java.util.Iterator;

public class TeslaCruiser extends TankUnitCard {
    public static final String ID = MoModHelper.makeID(TeslaCruiser.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 2;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardColor COLOR = AbstractCardEnum.SOVIET;


    public TeslaCruiser() {
        // 为了命名规范修改了变量名。这些参数具体的作用见下方
        super(ID, false, CARD_STRINGS, COST, TYPE, COLOR, RARITY, TARGET);
        this.setupDamage(15);
        this.setupMagicNumber(50);
        this.exhaust = true;
        this.isEthereal = true;
    }

    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(5);
        this.upgradeMagicNumber(75);
    }

    public void use(AbstractPlayer p, AbstractMonster mo) {
        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while (var3.hasNext()) {
            AbstractMonster m = (AbstractMonster) var3.next();
            if (m != null && !m.isDead) {
                AbstractGameAction ga = new DamageAction(m, new DamageInfo(AbstractDungeon.player, this.damage), AbstractGameAction.AttackEffect.LIGHTNING);
                this.addToBot(ga);
                this.addToTop(new SFXAction("ORB_LIGHTNING_EVOKE", 0.1F));
                this.addToTop(new VFXAction(new LightningEffect(ga.target.hb.cX, ga.target.hb.cY)));
                if (p.hasPower(OverChargePower.POWER_ID))
                    this.addToBot(new ApplyPowerAction(m, p, new ElectromagneticParalysisPower(m, p), this.magicNumber, true, AbstractGameAction.AttackEffect.LIGHTNING));
                if (MathUtils.random(0, 1) * 100 < this.magicNumber)
                    this.addToBot(new ApplyPowerAction(m, p, new ElectromagneticParalysisPower(m, p), this.magicNumber, true, AbstractGameAction.AttackEffect.LIGHTNING));
            }
        }
    }

}
