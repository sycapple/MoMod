package MoMod.power;

import MoMod.Actions.CardToPreviewUpdateAction;
import MoMod.Actions.TechnologyUpgradeAction;
import MoMod.util.MoModHelper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

public class TechnologyLevelPower extends AbstractMoPower {
    protected Color greenColor2;
    public static final String POWER_ID = MoModHelper.makeID(TechnologyLevelPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public TechnologyLevelPower(AbstractCreature owner) {
        this.amount = 0;
        this.InitialTechnologyLevelPower(owner);
    }

    public TechnologyLevelPower(AbstractCreature owner, int amount) {
        this.amount = amount;
        this.InitialTechnologyLevelPower(owner);
    }

    public void InitialTechnologyLevelPower(AbstractCreature owner) {
        this.greenColor2 = Color.GREEN.cpy();
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        String path128 = MoModHelper.assetPath("img/powers/SovietConstructionYardB.png");
        String path48 = MoModHelper.assetPath("img/powers/SovietConstructionYard.png");
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();

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
        this.description = DESCRIPTIONS[0] + this.amount;
        this.type = PowerType.BUFF;
    }

    @Override
    public void stackPower(int stackAmount) {
        if (stackAmount > 3)
            this.amount = 3;
    }
}
