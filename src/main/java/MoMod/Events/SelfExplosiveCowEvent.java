//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package MoMod.Events;

import MoMod.cards.attack.SelfExplosiveCow;
import MoMod.util.MoModHelper;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.cards.colorless.Bite;
import com.megacrit.cardcrawl.cards.curses.Regret;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.ArrayList;
import java.util.List;


public class SelfExplosiveCowEvent extends AbstractImageEvent {
    public static final String ID = MoModHelper.makeID(SelfExplosiveCowEvent.class.getSimpleName());

    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    public static final String NAME = eventStrings.NAME;
    public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    public static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String INTRO = DESCRIPTIONS[0];
    private static final String COW = DESCRIPTIONS[1];
    private static final String RUN = DESCRIPTIONS[2];
    private int screenNum = 0;
    private int hpLoss = 0;
    private List<String> cow;

    public SelfExplosiveCowEvent() {
        super(NAME, INTRO, MoModHelper.assetPath("img/events/SelfExplosiveCowEvent.png"));
        this.hpLoss = MathUtils.ceil((float) AbstractDungeon.player.maxHealth * 0.15F);
        this.cow = new ArrayList<>();
        this.imageEventText.setDialogOption(OPTIONS[0] + this.hpLoss + OPTIONS[3], new SelfExplosiveCow());
        this.imageEventText.setDialogOption(OPTIONS[1]);
    }

    public void onEnterRoom() {
        if (Settings.AMBIANCE_ON) {
            CardCrawlGame.sound.play("EVENT_FORGE");
        }

    }

    protected void buttonEffect(int buttonPressed) {
        switch (this.screenNum) {
            case 0:
                switch (buttonPressed) {
                    case 0:
                        this.screenNum = 2;
                        this.imageEventText.updateBodyText(COW);
                        this.replaceAttacks();
                        AbstractDungeon.player.damage(new DamageInfo((AbstractCreature) null, this.hpLoss, DamageType.HP_LOSS));
                        logMetricObtainCards(NAME, "试着接触", this.cow);
                        break;
                    case 1:
                        logMetricIgnored(NAME);
                        this.imageEventText.updateBodyText(RUN);
                        this.screenNum = 2;
                        break;
                }
                this.imageEventText.clearAllDialogs();
                this.imageEventText.setDialogOption(OPTIONS[2]);
                return;
            default:
                this.openMap();
        }
    }


    private void replaceAttacks() {
        ArrayList<AbstractCard> masterDeck = AbstractDungeon.player.masterDeck.group;

        int i;
        int count = 0;
        for (i = masterDeck.size() - 1; i >= 0; --i) {
            AbstractCard card = (AbstractCard) masterDeck.get(i);
            if (card.tags.contains(AbstractCard.CardTags.STARTER_STRIKE)) {
                AbstractDungeon.player.masterDeck.removeCard(card);
                count++;
            }
        }

        for (i = 0; i < count; ++i) {
            AbstractCard c = new SelfExplosiveCow();
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
            this.cow.add(c.cardID);
        }

    }

}
