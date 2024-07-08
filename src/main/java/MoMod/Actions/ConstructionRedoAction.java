package MoMod.Actions;

import MoMod.cards.Abstract.AbstractConstructionCard;
import MoMod.cards.power.constrcution.*;
import MoMod.power.EMPControlStationPower;
import MoMod.power.IronCurtainDevicePower;
import MoMod.power.TacticalNukeSiloPower;
import MoMod.util.ConstructionPileManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ConstructionRedoAction extends AbstractGameAction {
    public ConstructionRedoAction() {

    }

    @Override
    public void update() {
        CardGroup gp = ConstructionPileManager.getConstructionPile();
        AbstractCreature p = AbstractDungeon.player;
        if (!gp.isEmpty()) {
            AbstractConstructionCard lc = (AbstractConstructionCard) gp.group.get(ConstructionPileManager.getConstructionCnt() - 1);
            AbstractConstructionCard nc = null;
            if (!(lc instanceof EMPControlStation) && !(lc instanceof TacticalNukeSilo) && !(lc instanceof IronCurtainDevice)) {
                nc = (AbstractConstructionCard) lc.makeCopy();
                if (lc instanceof SovietBarracks) {
                    this.addToBot(new SovietBarracksUnitReadyAction());
                }
                if (lc instanceof SovietWarFactory) {
                    this.addToBot(new SovietWarFactoryUnitReadyAction());
                }
                if (lc instanceof Walls) {
                    this.addToBot(new GainBlockAction(p, lc.block));
                }
                if (lc instanceof SovietOreRefinery) {
                    this.addToBot(new GainEnergyAction(1));
                }
                if (lc instanceof TechOilDerrick) {
                    this.addToBot(new GainEnergyAction(1));
                }
                gp.moveToExhaustPile(lc);
                this.addToBot(new ApplyPowerAction(p, p, nc.getPower(true)));
                ConstructionPileManager.buildingConstruction(nc);
            } else {
                nc = (AbstractConstructionCard) lc.makeCopy();
                int amount = 0;
                if (lc instanceof EMPControlStation) {
                    AbstractPower po = p.getPower(EMPControlStationPower.POWER_ID);
                    amount = po.amount;
//                    nc = new EMPControlStation();
                    ConstructionPileManager.buildingConstruction(nc);
                    gp.moveToExhaustPile(lc);
                    this.addToBot(new UnitReadyAction(nc.cardsToPreview));
                    this.addToBot(new ApplyPowerAction(p, p, new EMPControlStationPower(p, amount, lc.uuid)));
                }
                if (lc instanceof TacticalNukeSilo) {
                    AbstractPower po = p.getPower(TacticalNukeSiloPower.POWER_ID);
                    amount = po.amount;
//                    nc = new Tack();
                    ConstructionPileManager.buildingConstruction(nc);
                    gp.moveToExhaustPile(lc);
                    this.addToBot(new UnitReadyAction(nc.cardsToPreview));
                    this.addToBot(new ApplyPowerAction(p, p, new TacticalNukeSiloPower(p, amount, lc.uuid)));
                }
                if (lc instanceof IronCurtainDevice) {
                    AbstractPower po = p.getPower(IronCurtainDevicePower.POWER_ID);
                    amount = po.amount;
//                    nc = new EMPControlStation();
                    ConstructionPileManager.buildingConstruction(nc);
                    gp.moveToExhaustPile(lc);
                    this.addToBot(new UnitReadyAction(nc.cardsToPreview));
                    this.addToBot(new ApplyPowerAction(p, p, new IronCurtainDevicePower(p, amount, lc.uuid)));
                }
            }
        }
        this.isDone = true;
    }
}

