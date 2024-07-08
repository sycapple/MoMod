package MoMod.power;

import MoMod.Actions.UnitReadyAction;
import MoMod.cards.skill.IronCurtain;
import MoMod.util.MoModHelper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.UUID;

public class IronCurtainDevicePower extends AbstractMoPower {
    protected Color greenColor2;
    public static final String POWER_ID = MoModHelper.makeID(IronCurtainDevicePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public int initialTurn;

    public IronCurtainDevicePower(AbstractCreature owner, int turns, UUID uuid) {
        this.greenColor2 = Color.GREEN.cpy();
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.Cuuid = uuid;
        this.amount = turns;
        this.initialTurn = turns;
        String path128 = MoModHelper.assetPath("img/powers/") + IronCurtainDevicePower.class.getSimpleName() + "B.png";
        String path48 = MoModHelper.assetPath("img/powers/") + IronCurtainDevicePower.class.getSimpleName() + ".png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    public void atStartOfTurn() {
        this.flash();
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            if (this.amount == 1) {
                this.addToBot(new UnitReadyAction(new IronCurtain()));
                AbstractCreature p = AbstractDungeon.player;
                AbstractPower po = new IronCurtainDevicePower(p, this.initialTurn, this.Cuuid);
                this.addToBot(new ApplyPowerAction(p, p, po));
            }
            this.addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
        }

    }
    public void resetPower(){
        this.amount = this.initialTurn;
    }
    public void updateDescription() {
        if (this.amount > 0) {
            this.description = this.amount + DESCRIPTIONS[0];
            this.type = PowerType.BUFF;
        }
    }

}
