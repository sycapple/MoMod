package MoMod.power;

import MoMod.Actions.FireUpLoseHpAction;
import MoMod.util.MoModHelper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class FireUpPower extends AbstractMoPower implements HealthBarRenderPower {
    protected Color greenColor2;
    public static final String POWER_ID = MoModHelper.makeID(FireUpPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private final AbstractCreature source;
    public int vigour = 0;
    public Color fireUpColor = new Color(0xfff89cff);

    //todo:使用两个参数来控制,一个是燃烧值,一个是燃烧率
    // 燃烧率 决定每回合加多少燃烧值
    // 燃烧值 决定每回合扣多少血量
    public void initialFirePower() {
        this.greenColor2 = Color.RED.cpy();
        this.name = NAME;
        this.ID = POWER_ID;
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
        String path128 = MoModHelper.assetPath("img/powers/") + FireUpPower.class.getSimpleName() + "B.png";
        String path48 = MoModHelper.assetPath("img/powers/") + FireUpPower.class.getSimpleName() + ".png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
    }

    public int getHealthBarAmount() {
        return this.amount;
    }

    public Color getColor() {
        return this.fireUpColor.cpy();
    }

    public FireUpPower(AbstractCreature owner, AbstractCreature source, int amount) {
        initialFirePower();
        this.owner = owner;
        this.source = source;
        this.amount = amount;
        this.updateDescription();
    }


    public FireUpPower(AbstractCreature owner, AbstractCreature source, int amount, int vigour) {
        initialFirePower();
        this.owner = owner;
        this.source = source;
        this.amount = amount;
        this.vigour += vigour;
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
