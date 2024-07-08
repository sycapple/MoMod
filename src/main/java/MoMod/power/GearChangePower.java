package MoMod.power;

import MoMod.util.MoModHelper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class GearChangePower extends AbstractMoPower {
    protected Color greenColor2;
    public static final String POWER_ID = MoModHelper.makeID(GearChangePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static int percent;

    public GearChangePower(AbstractCreature owner, int amount) {
        this.greenColor2 = Color.GREEN.cpy();
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = amount;
        this.isTurnBased = true;
        String path128 = MoModHelper.assetPath("img/powers/") + GearChangePower.class.getSimpleName() + "B.png";
        String path48 = MoModHelper.assetPath("img/powers/") + GearChangePower.class.getSimpleName() + ".png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }


    @Override
    public void atEndOfRound() {
        if (this.amount == 1)
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new CapacityUpgradingPower(this.owner, true)));
        this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
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
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
