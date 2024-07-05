package MoMod.power;

import MoMod.Actions.SovietWarFactoryUnitReadyAction;
import MoMod.Enums.AbstractTagEnum;
import MoMod.util.MoModHelper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class IndustrialPlantPower extends AbstractMoPower {
    protected Color greenColor2;
    public static final String POWER_ID = MoModHelper.makeID(IndustrialPlantPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public IndustrialPlantPower(AbstractCreature owner, boolean AD) {
        this.greenColor2 = Color.GREEN.cpy();
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = AD ? 1 : -1;
        String path128 = MoModHelper.assetPath("img/powers/") + IndustrialPlantPower.class.getSimpleName() + "B.png";
        String path48 = MoModHelper.assetPath("img/powers/") + IndustrialPlantPower.class.getSimpleName() + ".png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }


    public void atStartOfTurn() {
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
        if (this.amount > 0) {
            this.description = DESCRIPTIONS[0];
            this.type = PowerType.BUFF;
        }
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
//        if (card.tags.contains(AbstractTagEnum.TRAINED_UNIT) && card.tags.contains(AbstractTagEnum.TANK_UNIT)) {
//            this.flash();
//            action.exhaustCard = true;

    }
}
