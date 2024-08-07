package MoMod.cards.attack;


import MoMod.Actions.AutoRandomNoSourceDamageAction;
import MoMod.Enums.AbstractCardEnum;
import MoMod.cards.Abstract.InfantryUnitCard;
import MoMod.power.ConsconscriptPower;
import MoMod.util.MoModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Conscript extends InfantryUnitCard {
    public static final String ID = MoModHelper.makeID(Conscript.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 0;
    private static final AbstractCard.CardType TYPE = CardType.ATTACK;
    private static final AbstractCard.CardRarity RARITY = CardRarity.BASIC;
    private static final AbstractCard.CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final AbstractCard.CardColor COLOR = AbstractCardEnum.SOVIET;


    public Conscript() {
        // 为了命名规范修改了变量名。这些参数具体的作用见下方
        super(ID, false, CARD_STRINGS, COST, TYPE, COLOR, RARITY, TARGET);
        if (AbstractDungeon.player != null) {
            if (AbstractDungeon.player.hasPower(ConsconscriptPower.POWER_ID))
                this.setupDamage(3 + AbstractDungeon.player.getPower(ConsconscriptPower.POWER_ID).amount);
            else this.setupDamage(3);
        } else this.setupDamage(3);
        this.tags.add(CardTags.STRIKE);
        this.exhaust = true;
        this.isEthereal = true;
    }


    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(3);
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AutoRandomNoSourceDamageAction(this, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }
}
