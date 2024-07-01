package MoMod.cards.Abstract;

import MoMod.Actions.AddCardToConstructionPileAction;
import MoMod.Enums.AbstractTagEnum;
import MoMod.cards.power.constrcution.SovietBarracks;
import MoMod.util.ConstructionPileManager;
import MoMod.util.MoModHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractBuildingConstructionCard extends MoCard {

    public AbstractBuildingConstructionCard(String ID, boolean useTmpArt, CardStrings strings, int cost, CardType Type, CardColor color, CardRarity RARITY, CardTarget TARGET) {
        super(ID, useTmpArt, strings, cost, Type, color, RARITY, TARGET);
        this.tags.add(AbstractTagEnum.CONSTRUCTION_CARD);
        this.exhaust = true;
    }


    public void building() {
        //todo: 满了但是还是能把牌塞进去
        String getCs = this.cardID.split("0")[1];
        AbstractConstructionCard c = null;
        switch (getCs) {
            case "SovietBarracks":
                c = new SovietBarracks();
                break;
        }
        this.addToTop(new AddCardToConstructionPileAction(c));
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.building();
    }

}