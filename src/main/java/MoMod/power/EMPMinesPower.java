package MoMod.power;

import MoMod.relics.TerrorDrone;
import MoMod.util.MoModHelper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

public class EMPMinesPower extends AbstractMoPower {
    protected Color greenColor2;
    public static final String POWER_ID = MoModHelper.makeID(EMPMinesPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static int percent;

    public EMPMinesPower(AbstractCreature owner, int amount, int percent) {
        this.greenColor2 = Color.GREEN.cpy();
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = amount;
        this.isTurnBased = true;
        this.percent = percent;
        String path128 = MoModHelper.assetPath("img/powers/") + EMPMinesPower.class.getSimpleName() + "B.png";
        String path48 = MoModHelper.assetPath("img/powers/") + EMPMinesPower.class.getSimpleName() + ".png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
            this.flash();
            AbstractCreature p = AbstractDungeon.player;
            AbstractCreature m = info.owner;
            this.addToTop(new SFXAction("ORB_LIGHTNING_EVOKE", 0.1F));
            this.addToTop(new VFXAction(new LightningEffect(m.hb.cX, m.hb.cY)));
            if (p.hasPower(OverChargePower.POWER_ID))
                this.addToBot(new ApplyPowerAction(m, p, new ElectromagneticParalysisPower(m, 2, p), 2));
            if (MathUtils.random(0, 1) * 100 < percent)
                this.addToBot(new ApplyPowerAction(m, p, new ElectromagneticParalysisPower(m, 2, p), 2));
            this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
            if (this.amount == 0)
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        }
        return damageAmount;
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

    @Override
    public void atEndOfRound() {
        if (this.amount != 0) {
            if (!AbstractDungeon.player.hasRelic(TerrorDrone.ID)) {
                this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, this.amount));
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
            } else
                AbstractDungeon.player.getRelic(TerrorDrone.ID).flash();
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + percent + DESCRIPTIONS[1];
    }
}
