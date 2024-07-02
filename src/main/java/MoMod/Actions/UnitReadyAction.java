//package MoMod.Actions;
//
//import com.badlogic.gdx.math.MathUtils;
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
//import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
//import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
//import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
//import com.megacrit.cardcrawl.actions.utility.WaitAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.core.Settings;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.unlock.UnlockTracker;
//import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
//import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
//
//public class UnitReadyAction extends AbstractGameAction {
//    private static AbstractCard unit;
//    private static final float PADDING = 0;
//    private boolean isOtherCardInCenter;
//    private boolean sameUUID;
//
//    public UnitReadyAction(AbstractCard unit, int amount) {
//        this.unit = unit;
//        this.isOtherCardInCenter = true;
//        this.sameUUID = false;
//        UnlockTracker.markCardAsSeen(unit.cardID);
//        this.amount = amount;
//        this.actionType = ActionType.CARD_MANIPULATION;
//        if (this.unit.type != AbstractCard.CardType.CURSE && this.unit.type != AbstractCard.CardType.STATUS && AbstractDungeon.player.hasPower("MasterRealityPower")) {
//            this.unit.upgrade();
//        }
//        this.duration = Settings.ACTION_DUR_FAST;
//        this.actionType = ActionType.WAIT;
//        this.source = AbstractDungeon.player;
//        this.isOtherCardInCenter = true;
//        this.sameUUID = false;
//    }
//
//    @Override
//    public void update() {
//        AbstractCard card = unit;
//        card.exhaustOnUseOnce = true;
//        card.isEthereal = true;
//        card.rawDescription = card.rawDescription + " 虚无  NL  消耗 ";
//        int discardAmount = 0;
//        int handAmount = this.amount;
//        if (this.amount + AbstractDungeon.player.hand.size() > 10) {
//            AbstractDungeon.player.createHandIsFullDialog();
//            discardAmount = this.amount + AbstractDungeon.player.hand.size() - 10;
//            handAmount -= discardAmount;
//        }
//        this.addToHand(handAmount);
//        this.addToDiscard(discardAmount);
//        if (this.amount > 0) {
//            this.addToTop(new WaitAction(0.8F));
//        }
//        this.addToHand(amount);
//        this.isDone = true;
//    }
//
//    private void addToHand(int handAmt) {
//        int i;
//        switch (this.amount) {
//            case 0:
//                break;
//            case 1:
//                if (handAmt == 1) {
//                    if (this.isOtherCardInCenter) {
//                        AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(this.makeNewCard(), (float) Settings.WIDTH / 2.0F - (PADDING + AbstractCard.IMG_WIDTH), (float) Settings.HEIGHT / 2.0F));
//                    } else {
//                        AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(this.makeNewCard()));
//                    }
//                }
//                break;
//            case 2:
//                if (handAmt == 1) {
//                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(this.makeNewCard(), (float) Settings.WIDTH / 2.0F - (PADDING + AbstractCard.IMG_WIDTH * 0.5F), (float) Settings.HEIGHT / 2.0F));
//                } else if (handAmt == 2) {
//                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(this.makeNewCard(), (float) Settings.WIDTH / 2.0F + PADDING + AbstractCard.IMG_WIDTH, (float) Settings.HEIGHT / 2.0F));
//                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(this.makeNewCard(), (float) Settings.WIDTH / 2.0F - (PADDING + AbstractCard.IMG_WIDTH), (float) Settings.HEIGHT / 2.0F));
//                }
//                break;
//            case 3:
//                if (handAmt == 1) {
//                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(this.makeNewCard(), (float) Settings.WIDTH / 2.0F - (PADDING + AbstractCard.IMG_WIDTH), (float) Settings.HEIGHT / 2.0F));
//                } else if (handAmt == 2) {
//                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(this.makeNewCard(), (float) Settings.WIDTH / 2.0F + PADDING + AbstractCard.IMG_WIDTH, (float) Settings.HEIGHT / 2.0F));
//                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(this.makeNewCard(), (float) Settings.WIDTH / 2.0F - (PADDING + AbstractCard.IMG_WIDTH), (float) Settings.HEIGHT / 2.0F));
//                } else if (handAmt == 3) {
//                    for (i = 0; i < this.amount; ++i) {
//                        AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(this.makeNewCard()));
//                    }
//                }
//                break;
//            default:
//                for (i = 0; i < handAmt; ++i) {
//                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(this.makeNewCard(), MathUtils.random((float) Settings.WIDTH * 0.2F, (float) Settings.WIDTH * 0.8F), MathUtils.random((float) Settings.HEIGHT * 0.3F, (float) Settings.HEIGHT * 0.7F)));
//                }
//        }
//
//    }
//
//    private AbstractCard makeNewCard() {
//        return this.sameUUID ? unit.makeSameInstanceOf() : unit.makeStatEquivalentCopy();
//    }
//
//    private void addToDiscard(int discardAmt) {
//        switch (this.amount) {
//            case 0:
//                break;
//            case 1:
//                if (discardAmt == 1) {
//                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(this.makeNewCard(), (float) Settings.WIDTH / 2.0F + PADDING + AbstractCard.IMG_WIDTH, (float) Settings.HEIGHT / 2.0F));
//                }
//                break;
//            case 2:
//                if (discardAmt == 1) {
//                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(this.makeNewCard(), (float) Settings.WIDTH * 0.5F - (PADDING + AbstractCard.IMG_WIDTH * 0.5F), (float) Settings.HEIGHT * 0.5F));
//                } else if (discardAmt == 2) {
//                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(this.makeNewCard(), (float) Settings.WIDTH * 0.5F - (PADDING + AbstractCard.IMG_WIDTH * 0.5F), (float) Settings.HEIGHT * 0.5F));
//                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(this.makeNewCard(), (float) Settings.WIDTH * 0.5F + PADDING + AbstractCard.IMG_WIDTH * 0.5F, (float) Settings.HEIGHT * 0.5F));
//                }
//                break;
//            case 3:
//                if (discardAmt == 1) {
//                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(this.makeNewCard(), (float) Settings.WIDTH * 0.5F + PADDING + AbstractCard.IMG_WIDTH, (float) Settings.HEIGHT * 0.5F));
//                } else if (discardAmt == 2) {
//                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(this.makeNewCard(), (float) Settings.WIDTH * 0.5F, (float) Settings.HEIGHT * 0.5F));
//                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(this.makeNewCard(), (float) Settings.WIDTH * 0.5F + PADDING + AbstractCard.IMG_WIDTH, (float) Settings.HEIGHT * 0.5F));
//                } else if (discardAmt == 3) {
//                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(this.makeNewCard(), (float) Settings.WIDTH * 0.5F, (float) Settings.HEIGHT * 0.5F));
//                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(this.makeNewCard(), (float) Settings.WIDTH * 0.5F - (PADDING + AbstractCard.IMG_WIDTH), (float) Settings.HEIGHT * 0.5F));
//                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(this.makeNewCard(), (float) Settings.WIDTH * 0.5F + PADDING + AbstractCard.IMG_WIDTH, (float) Settings.HEIGHT * 0.5F));
//                }
//                break;
//            default:
//                for (int i = 0; i < discardAmt; ++i) {
//                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(this.makeNewCard(), MathUtils.random((float) Settings.WIDTH * 0.2F, (float) Settings.WIDTH * 0.8F), MathUtils.random((float) Settings.HEIGHT * 0.3F, (float) Settings.HEIGHT * 0.7F)));
//                }
//        }
//
//    }
//
//}
//
