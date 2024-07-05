package MoMod.power;

import MoMod.util.MoModHelper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class FuryDronepower extends AbstractMoPower {
    protected Color greenColor2;
    public static final String POWER_ID = MoModHelper.makeID(FuryDronepower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FuryDronepower(AbstractCreature owner, int amount) {
        this.greenColor2 = Color.GREEN.cpy();
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = amount;
        this.isTurnBased = true;
        String path128 = MoModHelper.assetPath("img/powers/") + FuryDronepower.class.getSimpleName() + "B.png";
        String path48 = MoModHelper.assetPath("img/powers/") + FuryDronepower.class.getSimpleName() + ".png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
            this.flash();
            this.addToTop(new DamageAction(info.owner, new DamageInfo(this.owner, 5 * this.amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE, true));
            this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, this.amount));
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
        //todo:怒焰机器人每回合结束会消失,后面加一个能力让他不会消失
        if (this.amount != 0) {
            if(!AbstractDungeon.player.hasPower(TerrorDronePower.POWER_ID)) {
                this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, this.amount));
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
            }
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}