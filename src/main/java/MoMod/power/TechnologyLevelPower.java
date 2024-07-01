//package MoMod.power;
//
//import MoMod.util.MoModHelper;
//import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
//import com.megacrit.cardcrawl.cards.tempCards.Shiv;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.localization.PowerStrings;
//
//public class TechnologyLevelPower extends AbstractMoPower {
//    public static final String POWER_ID = MoModHelper.assetPath(SovietBarracksPower.class.getSimpleName());
//    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
//    private static final String NAME = powerStrings.NAME;
//    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
//
//    public TechnologyLevelPower.java(int amount) {
//        this.name = NAME;
//        this.ID = POWER_ID;
//        this.type = PowerType.BUFF;
//        this.amount = amount;
//    }
//
//    public void atStartOfTurn() {
//        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
//            this.flash();
//            this.addToBot(new MakeTempCardInHandAction(new Shiv(), this.amount, false));
//        }
//
//    }
//}
