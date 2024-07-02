package MoMod.power;

import MoMod.Actions.FireUpLoseHpAction;
import MoMod.Actions.SovietWarFactoryUnitReadyAction;
import MoMod.util.MoModHelper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.PoisonLoseHpAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

public class FireUpPower extends AbstractMoPower {
    protected Color greenColor2;
    public static final String POWER_ID = MoModHelper.makeID(FireUpPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private final AbstractCreature source;
    private final int vigour;

    public FireUpPower(AbstractCreature owner, AbstractCreature source, int amount) {
        this.greenColor2 = Color.RED.cpy();
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.source = source;
        this.type = PowerType.DEBUFF;
        this.amount = amount;
        this.vigour = 3;
        this.isTurnBased = true;
        String path128 = MoModHelper.assetPath("img/powers/") + FireUpPower.class.getSimpleName() + "B.png";
        String path48 = MoModHelper.assetPath("img/powers/") + FireUpPower.class.getSimpleName() + ".png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("ATTACK_FIRE", 0.05F);
    }

    public void atStartOfTurn() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flashWithoutSound();
            this.addToBot(new FireUpLoseHpAction(this.owner, this.source, this.amount, AbstractGameAction.AttackEffect.FIRE, this.vigour));
        }

    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
    }

    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        super.renderAmount(sb, x, y, c);
        if (this.amount == 0) {
            if (!this.isTurnBased) {
                this.greenColor2.a = c.a;
                c = this.greenColor2;
            }
            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(this.amount), x, y, this.fontScale, c);
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.vigour + DESCRIPTIONS[2];
    }
}
