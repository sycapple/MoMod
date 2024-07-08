package MoMod.cards.power.constrcution;

import MoMod.Actions.ExhaustUnitReadyAction;
import MoMod.Enums.AbstractCardEnum;
import MoMod.cards.Abstract.AbstractConstructionCard;
import MoMod.cards.attack.Conscript;
import MoMod.power.AbstractMoPower;
import MoMod.power.DebrisShelterPower;
import MoMod.util.MoModHelper;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class InstantShelter extends AbstractConstructionCard {
    public static final String ID = MoModHelper.makeID(InstantShelter.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardColor COLOR = AbstractCardEnum.SOVIET;

    public InstantShelter(int amount) {
        //为了命名规范修改了变量名。这些参数具体的作用见下方
        //todo:应急碉堡 建筑卡贴图
        super(ID, true, CARD_STRINGS, COLOR, RARITY);
        this.setupBlock(3);
        this.setupMagicNumber(amount);
        this.cardsToPreview = new Conscript();
    }


    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.gainBlock();
    }

    @Override
    public void triggerOnExhaust() {
        AbstractMoPower po = this.getPower(false);
        this.addToBot(new ExhaustUnitReadyAction(new Conscript(), this.magicNumber));
        this.addToBot(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, po.ID, 1));
        DebrisShelterPower pd = (DebrisShelterPower) AbstractDungeon.player.getPower(DebrisShelterPower.POWER_ID);
        if (pd != null)
            this.gainBlock(pd.BlockArg);
    }
}
