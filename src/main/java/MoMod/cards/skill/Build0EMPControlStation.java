package MoMod.cards.skill;

import MoMod.Actions.AddCardToConstructionPileAction;
import MoMod.Actions.TechnologyUpgradeAction;
import MoMod.Enums.AbstractCardEnum;
import MoMod.cards.Abstract.AbstractBuildingConstructionCard;
import MoMod.cards.power.constrcution.EMPControlStation;
import MoMod.cards.power.constrcution.TechOilDerrick;
import MoMod.power.EMPControlStationPower;
import MoMod.util.ConstructionPileManager;
import MoMod.util.MoModHelper;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

import static MoMod.util.ConstructionPileManager.getConstructionPile;

public class Build0EMPControlStation extends AbstractBuildingConstructionCard {
    public static final String ID = MoModHelper.makeID(Build0EMPControlStation.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 2;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardColor COLOR = AbstractCardEnum.SOVIET;

    public Build0EMPControlStation() {
        //todo:电磁脉冲控制站 技能贴图
        super(ID, true, CARD_STRINGS, COST, TYPE, COLOR, RARITY, TARGET);
        this.setupMagicNumber(3);
        this.cardsToPreview = new EMPControlStation();
        this.exhaust = true;
    }


    @Override
    public void limitedUpgrade() {
        this.upgradeBaseCost(1);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        for (AbstractCard card : getConstructionPile().group) {
            if (card instanceof EMPControlStation) {
                AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, CardCrawlGame.languagePack.getUIString(MoModHelper.makeID(Build0EMPControlStation.class.getSimpleName())).TEXT[0], true));
                return false;
            }
        }
        return true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToTop(new AddCardToConstructionPileAction(new EMPControlStation()));
        AbstractPower po = new EMPControlStationPower(p, 3, this.uuid);
        this.addToTop(new ApplyPowerAction(p, p, po));
    }
}
