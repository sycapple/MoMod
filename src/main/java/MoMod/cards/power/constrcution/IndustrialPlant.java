package MoMod.cards.power.constrcution;

import MoMod.Enums.AbstractCardEnum;
import MoMod.cards.Abstract.AbstractConstructionCard;
import MoMod.util.MoModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class IndustrialPlant extends AbstractConstructionCard {
    public static final String ID = MoModHelper.makeID(IndustrialPlant.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardColor COLOR = AbstractCardEnum.SOVIET;

    public IndustrialPlant() {
        super(ID, false, CARD_STRINGS, COLOR, RARITY);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        super.use(abstractPlayer, abstractMonster);
    }
}
