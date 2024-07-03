package MoMod.cards.power;

import MoMod.Actions.TechnologyUpgradeAction;
import MoMod.cards.Abstract.MoCard;
import MoMod.power.LargeIterationPlanPower;
import MoMod.power.TechnologyLevelPower;
import MoMod.util.MoModHelper;
import MoMod.Enums.AbstractCardEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

public class LevelUpgrade extends MoCard {
    public static final String ID = MoModHelper.makeID(LevelUpgrade.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 2;
    private static final AbstractCard.CardType TYPE = CardType.POWER;
    private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = CardTarget.SELF;
    private static final AbstractCard.CardColor COLOR = AbstractCardEnum.SOVIET;

    public LevelUpgrade() {
        // 为了命名规范修改了变量名。这些参数具体的作用见下方
        super(ID, false, CARD_STRINGS, COST, TYPE, COLOR, RARITY, TARGET);
        this.setupMagicNumber(1);
    }


    @Override
    public void limitedUpgrade() {
        this.upgradeBaseCost(1);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!AbstractDungeon.player.hasPower(LargeIterationPlanPower.POWER_ID))
            if (AbstractDungeon.player.hasPower(TechnologyLevelPower.POWER_ID) && AbstractDungeon.player.getPower(TechnologyLevelPower.POWER_ID).amount == 3) {
                AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, CardCrawlGame.languagePack.getUIString(MoModHelper.makeID(TechnologyUpgradeAction.class.getSimpleName())).TEXT[0], true));
                return false;
            }
        return true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new TechnologyUpgradeAction());
    }

}
