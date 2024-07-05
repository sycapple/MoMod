package MoMod.power;

import MoMod.Actions.SovietBarracksUnitReadyAction;
import MoMod.Actions.UnitReadyAction;
import MoMod.cards.skill.EMPulse;
import MoMod.util.MoModHelper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.UUID;

public class EMPControlStationPower extends AbstractMoPower {
    protected Color greenColor2;
    public static final String POWER_ID = MoModHelper.makeID(EMPControlStationPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public EMPControlStationPower(AbstractCreature owner, int turns, UUID uuid) {
        this.greenColor2 = Color.GREEN.cpy();
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.Cuuid = uuid;
        this.amount = turns;
        //todo:emp控制站能力贴图
//        String path128 = MoModHelper.assetPath("img/powers/") + EMPControlStationPower.class.getSimpleName() + "B.png";
//        String path48 = MoModHelper.assetPath("img/powers/") + EMPControlStationPower.class.getSimpleName() + ".png";
//        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
//        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    public void atStartOfTurn() {
        this.flash();
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            if (this.amount == 1) {
                this.addToBot(new UnitReadyAction(new EMPulse()));
                AbstractCreature p = AbstractDungeon.player;
                AbstractPower po = new EMPControlStationPower(p, 3, this.Cuuid);
                this.addToTop(new ApplyPowerAction(p, p, po));
            }
            this.addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
        }

    }

    public void updateDescription() {
        if (this.amount > 0) {
            this.description = this.amount + DESCRIPTIONS[0];
            this.type = PowerType.BUFF;
        }
    }

}
