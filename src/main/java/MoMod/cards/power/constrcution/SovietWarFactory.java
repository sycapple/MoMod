package MoMod.cards.power.constrcution;

import MoMod.Enums.AbstractCardEnum;
import MoMod.cards.Abstract.AbstractConstructionCard;
import MoMod.util.MoModHelper;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

public class SovietWarFactory extends AbstractConstructionCard {
    public static final String ID = MoModHelper.makeID(SovietWarFactory.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardColor COLOR = AbstractCardEnum.SOVIET;

    public SovietWarFactory() {
        super(ID, false, CARD_STRINGS, COLOR, RARITY);
    }
}
