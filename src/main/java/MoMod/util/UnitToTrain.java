package MoMod.util;

import MoMod.Enums.AbstractTagEnum;
import MoMod.cards.Abstract.AbstractConstructionCard;
import MoMod.cards.attack.*;
import MoMod.cards.power.constrcution.SovietBarracks;
import MoMod.cards.power.constrcution.SovietWarFactory;
import MoMod.cards.skill.FuryDrone;
import MoMod.power.EliteRseservesPower;
import MoMod.power.IndustrialPlantPower;
import MoMod.power.TechnologyLevelPower;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;

import java.util.Iterator;

public class UnitToTrain {
    AbstractConstructionCard card = null;
    AbstractCreature p = null;

    public UnitToTrain(AbstractConstructionCard card, AbstractCreature p) {
        this.card = card;
        this.p = p;
    }

    public AbstractCard getUnit() {
        AbstractCard c = null;
        if (p != null) {
            int technologyLevel = p.hasPower(TechnologyLevelPower.POWER_ID) ? p.getPower(TechnologyLevelPower.POWER_ID).amount : 0;
            if (card instanceof SovietBarracks) {
                switch (technologyLevel) {
                    case 0: {
                        c = new Conscript();
                        break;
                    }
                    case 1: {
                        c = new FlakTrooper();
                        break;
                    }
                    case 2: {
                        c = new ShockTrooper();
                        break;
                    }
                    case 3: {
                        c = new CyborgVanguard();
                        break;
                    }
                }
            } else if (card instanceof SovietWarFactory) {
                switch (technologyLevel) {
                    case 0: {
                        c = new FuryDrone();
                        break;
                    }
                    case 1: {
                        c = new RhinoHeavyTank();
                        break;
                    }
                    case 2: {
                        c = new TeslaCruiser();
                        break;
                    }
                    case 3: {
                        c = new ApocalypseTank();
                        break;
                    }
                }
            }
            if (c != null) {
                if (p.hasPower(EliteRseservesPower.POWER_ID))
                    c.upgrade();
                if (p.hasPower(IndustrialPlantPower.POWER_ID)) {
                    int amount = p.getPower(IndustrialPlantPower.POWER_ID).amount;
                    if (c == null) {
                        Iterator var1 = GetAllInBattleInstances.get(c.uuid).iterator();
                        while (var1.hasNext()) {
                            AbstractCard card1 = (AbstractCard) var1.next();
                            card1.modifyCostForCombat(-amount);
                        }
                    } else {
                        c.modifyCostForCombat(-amount);
                    }
                }
            }
        }
        return c;
    }
}