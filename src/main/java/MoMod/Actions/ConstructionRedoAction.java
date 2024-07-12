package MoMod.Actions;

import MoMod.cards.Abstract.AbstractConstructionCard;
import MoMod.cards.power.constrcution.*;
import MoMod.power.*;
import MoMod.util.ConstructionPileManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import sun.management.Sensor;

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
                if (lc instanceof SovietBarracks) {
                    this.addToBot(new SovietBarracksUnitReadyAction());
                    nc = new SovietBarracks();
                }
                if (lc instanceof SovietWarFactory) {
                    this.addToBot(new SovietWarFactoryUnitReadyAction());
                    nc = new SovietWarFactory();
                }
                if (lc instanceof Walls) {
                    this.addToBot(new GainBlockAction(p, lc.block));
                    nc = new Walls();
                }
                if (lc instanceof SovietOreRefinery) {
                    this.addToBot(new GainEnergyAction(1));
                    nc = new SovietOreRefinery();
                }
                if (lc instanceof TechOilDerrick) {
                    this.addToBot(new GainEnergyAction(1));
                    nc = new TechOilDerrick();
                }
                if (lc instanceof InstantShelter) {
                    this.addToBot(new GainBlockAction(p, lc.block));
                    nc = new InstantShelter(lc.magicNumber);
                }
                if (lc instanceof FlameTower) {
                    AbstractPower po = p.getPower(FlameTowerPower.POWER_ID);
                    po.flash();
                    AbstractCreature m = AbstractDungeon.getCurrRoom().monsters.getRandomMonster();
                    if (m != null && !m.isDead)
                        this.addToBot(new FireUpAction(m, p, 2));
                    nc = new FlameTower();
                }
                if (lc instanceof SensorTower) {
                    AbstractPower po = p.getPower(SensorTowerPower.POWER_ID);
                    po.flash();
                    this.addToBot(new ScryAction(2));
                    this.addToBot(new DrawCardAction(p, 1));
                    nc = new SensorTower();
                }
                gp.moveToExhaustPile(lc);
                this.addToBot(new ApplyPowerAction(p, p, nc.getPower(true)));
                ConstructionPileManager.buildingConstruction(nc);
            } else {
                int amount = 0;
                if (lc instanceof EMPControlStation) {
                    AbstractPower po = p.getPower(EMPControlStationPower.POWER_ID);
                    amount = po.amount;
                    nc = new EMPControlStation();
                    ConstructionPileManager.buildingConstruction(nc);
                    gp.moveToExhaustPile(lc);
                    this.addToBot(new UnitReadyAction(nc.cardsToPreview));
                    this.addToBot(new ApplyPowerAction(p, p, new EMPControlStationPower(p, amount, lc.uuid)));
                }
                if (lc instanceof TacticalNukeSilo) {
                    AbstractPower po = p.getPower(TacticalNukeSiloPower.POWER_ID);
                    amount = po.amount;
                    nc = new TacticalNukeSilo(lc.magicNumber);
                    ConstructionPileManager.buildingConstruction(nc);
                    gp.moveToExhaustPile(lc);
                    this.addToBot(new UnitReadyAction(nc.cardsToPreview));
                    this.addToBot(new ApplyPowerAction(p, p, new TacticalNukeSiloPower(p, amount, lc.uuid)));
                }
                if (lc instanceof IronCurtainDevice) {
                    AbstractPower po = p.getPower(IronCurtainDevicePower.POWER_ID);
                    amount = po.amount;
                    nc = new IronCurtainDevice(lc.magicNumber);
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

