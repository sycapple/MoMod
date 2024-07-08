package MoMod.cards.skill;


import MoMod.Actions.ConstructionRedoAction;
import MoMod.Enums.AbstractCardEnum;
import MoMod.cards.Abstract.MoCard;
import MoMod.util.MoModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ConstructionRedo extends MoCard {
    public static final String ID = MoModHelper.makeID(ConstructionRedo.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardColor COLOR = AbstractCardEnum.SOVIET;

    public ConstructionRedo() {
        // 为了命名规范修改了变量名。这些参数具体的作用见下方
        super(ID, false, CARD_STRINGS, COST, TYPE, COLOR, RARITY, TARGET);
    }


    @Override
    public void limitedUpgrade() {
        this.upgradeBaseCost(0);
    }

//    @Override
//    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
//        if (getConstructionCnt() != 0) {
//            AbstractCard lc = getConstructionPile().group.get(getConstructionCnt() - 1);
//            if (lc instanceof TacticalNukeSilo || lc instanceof EMPControlStation || lc instanceof IronCurtainDevice) {
//                AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, CardCrawlGame.languagePack.getUIString(MoModHelper.makeID(ConstructionRedo.class.getSimpleName())).TEXT[0], true));
//                return false;
//            }
//        }
//        return true;
//    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ConstructionRedoAction());
    }

}
