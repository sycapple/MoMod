package MoMod.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class SellAndDashAction extends AbstractGameAction {
    public SellAndDashAction() {

    }

    @Override
    public void update() {
        this.addToBot(new UseConstructionAction());
        this.addToBot(new ExhaustAllConstructionAction());
//        System.out.println(gp.size());
//        AbstractCreature p = AbstractDungeon.player;
//        AbstractConstructionCard c;
//        for (int i = 0; i < ConstructionPileManager.getConstructionCnt(); i++) {
//            c = (AbstractConstructionCard) gp.group.get(i);
//            if (c instanceof SovietBarracks) {
//                AbstractPower po = p.getPower(SovietBarracksPower.POWER_ID);
//                po.flash();
//                this.addToBot(new SovietBarracksUnitReadyAction());
//            }
//            if (c instanceof SovietWarFactory) {
//                AbstractPower po = p.getPower(SovietWarFactoryPower.POWER_ID);
//                po.flash();
//                this.addToBot(new SovietWarFactoryUnitReadyAction());
//            }
//            if (c instanceof Walls) {
//                AbstractPower po = p.getPower(WallsPower.POWER_ID);
//                po.flash();
//                this.addToBot(new GainBlockAction(p, c.block));
//            }
//            if (c instanceof TacticalNukeSilo) {
//                this.addToBot(new UnitReadyAction(new TacticalNuke()));
//                TacticalNukeSiloPower po = (TacticalNukeSiloPower) p.getPower(TacticalNukeSiloPower.POWER_ID);
//                po.flash();
//                po.resetPower();
//            }
//            if (c instanceof IronCurtainDevice) {
//                this.addToBot(new UnitReadyAction(new IronCurtain()));
//                IronCurtainDevicePower po = (IronCurtainDevicePower) p.getPower(IronCurtainDevicePower.POWER_ID);
//                po.flash();
//                po.resetPower();
//            }
//            if (c instanceof EMPControlStation) {
//                this.addToBot(new UnitReadyAction(new EMPulse()));
//                EMPControlStationPower po = (EMPControlStationPower) p.getPower(EMPControlStationPower.POWER_ID);
//                po.flash();
//                po.resetPower();
//            }
//            if (c instanceof SovietOreRefinery) {
//                AbstractPower po = p.getPower(SovietOreRefineryPower.POWER_ID);
//                po.flash();
//                this.addToBot(new GainEnergyAction(1));
//            }
//            if (c instanceof TechOilDerrick) {
//                AbstractPower po = p.getPower(TechOilDerrickPower.POWER_ID);
//                po.flash();
//                this.addToBot(new GainEnergyAction(1));
//            }
//        }
        this.isDone = true;
    }
}
