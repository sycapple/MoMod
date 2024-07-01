package MoMod.cards.Abstract;

import MoMod.Actions.AddCardToConstructionPileAction;
import MoMod.Enums.AbstractTagEnum;
import MoMod.cards.power.constrcution.SovietBarracks;
import MoMod.cards.power.constrcution.SovietWarFactory;
import MoMod.power.AbstractMoPower;
import MoMod.power.SovietBarracksPower;
import MoMod.power.SovietWarFactoryPower;
import MoMod.util.ConstructionPileManager;
import MoMod.util.MoModHelper;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractBuildingConstructionCard extends MoCard {

    public AbstractBuildingConstructionCard(String ID, boolean useTmpArt, CardStrings strings, int cost, CardType Type, CardColor color, CardRarity RARITY, CardTarget TARGET) {
        super(ID, useTmpArt, strings, cost, Type, color, RARITY, TARGET);
        this.tags.add(AbstractTagEnum.CONSTRUCTION_CARD);
        this.exhaust = true;
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

}