package MoMod.power;

import MoMod.util.MoModHelper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class LargeIterationPlanPower extends AbstractMoPower {
    protected Color greenColor2;
    public static final String POWER_ID = MoModHelper.makeID(LargeIterationPlanPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private final AbstractCreature source;

    public LargeIterationPlanPower(AbstractCreature owner, AbstractCreature source) {
        this.greenColor2 = Color.RED.cpy();
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.source = source;
        this.type = PowerType.BUFF;
        this.isTurnBased = true;
        String path128 = MoModHelper.assetPath("img/powers/") + LargeIterationPlanPower.class.getSimpleName() + "B.png";
        String path48 = MoModHelper.assetPath("img/powers/") + LargeIterationPlanPower.class.getSimpleName() + ".png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
        this.type = PowerType.BUFF;
    }

    @Override
    public void atEndOfRound() {
    }
}
