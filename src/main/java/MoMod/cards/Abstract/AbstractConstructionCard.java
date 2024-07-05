package MoMod.cards.Abstract;

import MoMod.Actions.ConstructionDestroyedAction;
import MoMod.Enums.AbstractTagEnum;
import MoMod.cards.attack.*;
import MoMod.cards.power.constrcution.TechOilDerrick;
import MoMod.cards.power.constrcution.Walls;
import MoMod.power.*;
import MoMod.util.MoModHelper;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public abstract class AbstractConstructionCard extends MoCard {

    public AbstractConstructionCard(String ID, boolean useTmpArt, CardStrings strings, AbstractCard.CardColor color, AbstractCard.CardRarity RARITY) {
        super(ID, useTmpArt, strings, -2, CardType.POWER, color, RARITY, CardTarget.NONE);
        this.tags.add(AbstractTagEnum.CONSTRUCTION_CARD);
//        int technologyLevel = AbstractDungeon.player.hasPower(TechnologyLevelPower.POWER_ID) ? AbstractDungeon.player.getPower(TechnologyLevelPower.POWER_ID).amount : 0;
//        String getCs = this.cardID.replace(MoModHelper.makeID(""), "");
//        if (getCs.equals("SovietBarracks")) {
//            switch (technologyLevel) {
//                case 0: {
//                    this.cardsToPreview = new Conscript();
//                    break;
//                }
//                case 1: {
//                    this.cardsToPreview = new Pyro();
//                    break;
//                }
//                case 2: {
//                    this.cardsToPreview = new ShockTrooper();
//                    break;
//                }
//                case 3: {
//                    this.cardsToPreview = new Arsonist();
//                    break;
//                }
//            }
//        } else {
//            switch (technologyLevel) {
//                case 0: {
//                    this.cardsToPreview = new RhinoHeavyTank();
//                    break;
//                }
//                case 1: {
//                    this.cardsToPreview = new Buratino();
//                    break;
//                }
//                case 2: {
//                    this.cardsToPreview = new TeslaCruiser();
//                    break;
//                }
//                case 3: {
//                    this.cardsToPreview = new IronDragon();
//                    break;
//                }
//            }
//        }
    }

    public AbstractMoPower getPower(boolean AD) {
        String getCs = this.cardID.replace(MoModHelper.makeID(""), "");
        AbstractMoPower po = null;
        switch (getCs) {
            case "SovietBarracks": {
                po = new SovietBarracksPower(AbstractDungeon.player, AD);
                break;
            }
            case "SovietWarFactory": {
                po = new SovietWarFactoryPower(AbstractDungeon.player, AD);
                break;
            }
            case "Walls": {
                po = new WallsPower(AbstractDungeon.player, AD);
                break;
            }
            case "IndustrialPlant": {
                po = new IndustrialPlantPower(AbstractDungeon.player, AD);
                break;
            }
            case "TechOilDerrick": {
                po = new TechOilDerrickPower(AbstractDungeon.player, AD);
                break;
            }
        }
        return po;
    }

    public void triggerOnExhaust() {
        AbstractMoPower po = this.getPower(false);
        this.addToBot(new ConstructionDestroyedAction());
        this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, po));
        DebrisShelterPower pd = (DebrisShelterPower) AbstractDungeon.player.getPower(DebrisShelterPower.POWER_ID);
        if (pd != null)
            this.gainBlock(pd.BlockArg);
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }


}