package MoMod.cards.Abstract;

import MoMod.Actions.AddCardToConstructionPileAction;
import MoMod.Enums.AbstractTagEnum;
import MoMod.cards.power.constrcution.*;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class AbstractBuildingConstructionCard extends MoCard {

    public AbstractBuildingConstructionCard(String ID, boolean useTmpArt, CardStrings strings, int cost, CardType Type, CardColor color, CardRarity RARITY, CardTarget TARGET) {
        super(ID, useTmpArt, strings, cost, Type, color, RARITY, TARGET);
        this.tags.add(AbstractTagEnum.BUILDING_CONSTRUCTION_CARD);
    }

    public AbstractConstructionCard getConstruction() {
        String getCs = this.cardID.split("0")[1];
        AbstractConstructionCard c = null;
        switch (getCs) {
            case "SovietBarracks": {
                c = new SovietBarracks();
                break;
            }
            case "SovietWarFactory": {
                c = new SovietWarFactory();
                break;
            }
            case "Walls": {
                c = new Walls();
                break;
            }
            case "IndustrialPlant": {
                c = new IndustrialPlant();
                break;
            }
            case "TechOilDerrick": {
                c = new TechOilDerrick();
                break;
            }
            case "SovietOreRefinery": {
                c = new SovietOreRefinery();
                break;
            }
            case "SensorTower": {
                c = new SensorTower();
                break;
            }
            case "InstantShelter": {
                c = new InstantShelter(this.magicNumber);
                break;
            }
        }
        return c;
    }


    public void building() {
        AbstractConstructionCard c = this.getConstruction();
        this.addToTop(new AddCardToConstructionPileAction(c));
        this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, c.getPower(true)));
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.building();
    }

    @Override
    public void triggerOnManualDiscard() {
        AbstractConstructionCard c = new Walls();
        this.addToTop(new AddCardToConstructionPileAction(c));
        this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, c.getPower(true)));
    }
}