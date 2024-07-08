package MoMod.cards.skill;


import MoMod.Enums.AbstractCardEnum;
import MoMod.cards.Abstract.MoCard;
import MoMod.power.OverChargePower;
import MoMod.util.MoModHelper;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

public class OverCharge extends MoCard {
    public static final String ID = MoModHelper.makeID(OverCharge.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardColor COLOR = AbstractCardEnum.SOVIET;

    public OverCharge() {
        // 为了命名规范修改了变量名。这些参数具体的作用见下方
        super(ID, false, CARD_STRINGS, COST, TYPE, COLOR, RARITY, TARGET);
        this.setupMagicNumber(2);
    }


    @Override
    public void limitedUpgrade() {
        this.upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToTop(new SFXAction("ORB_LIGHTNING_EVOKE", 0.1F));
        this.addToTop(new VFXAction(new LightningEffect(p.hb.cX, p.hb.cY)));
        this.addToBot(new ApplyPowerAction(p, p, new OverChargePower(p, this.magicNumber), this.magicNumber));
    }

}
