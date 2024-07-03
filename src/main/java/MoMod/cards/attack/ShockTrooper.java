package MoMod.cards.attack;

import MoMod.Enums.AbstractCardEnum;
import MoMod.cards.Abstract.InfantryUnitCard;
import MoMod.cards.Abstract.MoCard;
import MoMod.power.ElectromagneticParalysisPower;
import MoMod.util.MoModHelper;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;


public class ShockTrooper extends InfantryUnitCard {
    public static final String ID = MoModHelper.makeID(ShockTrooper.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 0;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardColor COLOR = AbstractCardEnum.SOVIET;


    public ShockTrooper() {
        //为了命名规范修改了变量名。这些参数具体的作用见下方
        super(ID, false, CARD_STRINGS, COST, TYPE, COLOR, RARITY, TARGET);
        this.setupDamage(10);
        this.setupMagicNumber(25);
        this.exhaust = true;
        this.isEthereal = true;
    }

    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(5);
        this.upgradeMagicNumber(25);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractGameAction ga = new DamageAction(m, new DamageInfo(AbstractDungeon.player, this.damage), AbstractGameAction.AttackEffect.LIGHTNING);
        this.addToBot(ga);
        this.addToTop(new SFXAction("ORB_LIGHTNING_EVOKE", 0.1F));
        this.addToTop(new VFXAction(new LightningEffect(ga.target.hb.cX, ga.target.hb.cY)));
        if (MathUtils.random(0, 1) * 100 < this.magicNumber)
            this.addToBot(new ApplyPowerAction(m, p, new ElectromagneticParalysisPower(m, p), this.magicNumber, true, AbstractGameAction.AttackEffect.LIGHTNING));
    }

}
