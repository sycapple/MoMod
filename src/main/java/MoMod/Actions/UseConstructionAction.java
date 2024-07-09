package MoMod.Actions;

import MoMod.cards.Abstract.AbstractConstructionCard;
import MoMod.cards.attack.TacticalNuke;
import MoMod.cards.power.constrcution.*;
import MoMod.cards.skill.EMPulse;
import MoMod.cards.skill.IronCurtain;
import MoMod.power.*;
import MoMod.util.ConstructionPileManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class UseConstructionAction extends AbstractGameAction {
    public UseConstructionAction() {

    }

    @Override
    public void update() {
        CardGroup gp = ConstructionPileManager.getConstructionPile();
        AbstractCreature p = AbstractDungeon.player;
        AbstractConstructionCard c;
        boolean sb = true;
        boolean sw = true;
        boolean ft = true;
        for (int i = 0; i < gp.size(); i++) {
            c = (AbstractConstructionCard) gp.group.get(i);
            if (c instanceof SovietBarracks && sb) {
                sb = false;
                AbstractPower po = p.getPower(SovietBarracksPower.POWER_ID);
                po.flash();
                this.addToBot(new SovietBarracksUnitReadyAction());
            }
            if (c instanceof SovietWarFactory && sw) {
                sw = false;
                AbstractPower po = p.getPower(SovietWarFactoryPower.POWER_ID);
                po.flash();
                this.addToBot(new SovietWarFactoryUnitReadyAction());
            }
            if (c instanceof Walls) {
                AbstractPower po = p.getPower(WallsPower.POWER_ID);
                po.flash();
                this.addToBot(new GainBlockAction(p, c.block));
            }
            if (c instanceof TacticalNukeSilo) {
                this.addToBot(new UnitReadyAction(new TacticalNuke()));
                TacticalNukeSiloPower po = (TacticalNukeSiloPower) p.getPower(TacticalNukeSiloPower.POWER_ID);
                po.flash();
                po.resetPower();
            }
            if (c instanceof IronCurtainDevice) {
                this.addToBot(new UnitReadyAction(new IronCurtain()));
                IronCurtainDevicePower po = (IronCurtainDevicePower) p.getPower(IronCurtainDevicePower.POWER_ID);
                po.flash();
                po.resetPower();
            }
            if (c instanceof EMPControlStation) {
                this.addToBot(new UnitReadyAction(new EMPulse()));
                EMPControlStationPower po = (EMPControlStationPower) p.getPower(EMPControlStationPower.POWER_ID);
                po.flash();
                po.resetPower();
            }
            if (c instanceof SovietOreRefinery) {
                AbstractPower po = p.getPower(SovietOreRefineryPower.POWER_ID);
                po.flash();
                this.addToBot(new GainEnergyAction(1));
            }
            if (c instanceof TechOilDerrick) {
                AbstractPower po = p.getPower(TechOilDerrickPower.POWER_ID);
                po.flash();
                this.addToBot(new GainEnergyAction(1));
            }
            if (c instanceof InstantShelter) {
                AbstractPower po = p.getPower(InstantShelterPower.POWER_ID);
                po.flash();
                this.addToBot(new GainBlockAction(p, c.block));
            }
            if (c instanceof FlameTower && ft) {
                ft = false;
                AbstractPower po = p.getPower(FlameTowerPower.POWER_ID);
                po.flash();
                this.addToBot(new FireUpAction(AbstractDungeon.getRandomMonster(), p, po.amount));
            }
        }
        this.isDone = true;
    }
}
