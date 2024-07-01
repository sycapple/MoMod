package MoMod.cards.Abstract;

import MoMod.Actions.AddCardToConstructionPileAction;
import MoMod.Enums.AbstractTagEnum;
import MoMod.util.ConstructionPileManager;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class AbstractConstructionCard extends MoCard {

    public AbstractConstructionCard(String ID, boolean useTmpArt, CardStrings strings, AbstractCard.CardColor color, AbstractCard.CardRarity RARITY) {
        super(ID, useTmpArt, strings, -2, CardType.POWER, color, RARITY, CardTarget.NONE);
        this.tags.add(AbstractTagEnum.CONSTRUCTION_CARD);
        this.exhaust = false;
    }

    public void triggerOnExhaust() {
        this.addToTop(new GainEnergyAction(1));
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }

}