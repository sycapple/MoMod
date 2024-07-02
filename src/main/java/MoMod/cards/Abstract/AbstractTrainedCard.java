package MoMod.cards.Abstract;

import MoMod.Enums.AbstractTagEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.localization.CardStrings;

public abstract class AbstractTrainedCard extends MoCard {
    public AbstractTrainedCard(String ID, boolean useTmpArt, CardStrings strings, int COST, CardType TYPE, AbstractCard.CardColor color, AbstractCard.CardRarity RARITY, CardTarget TARGET, boolean isTrained) {
        super(ID, useTmpArt, strings, COST, TYPE, color, RARITY, TARGET);
        if (isTrained) {
            this.exhaust = true;
            this.isEthereal = true;
            strings.DESCRIPTION = strings.DESCRIPTION + " NL 消耗  虚无 ";
        }
    }

    public AbstractTrainedCard(String ID, boolean useTmpArt, CardStrings strings, int COST, CardType TYPE, AbstractCard.CardColor color, AbstractCard.CardRarity RARITY, CardTarget TARGET) {
        super(ID, useTmpArt, strings, COST, TYPE, color, RARITY, TARGET);
    }
}
