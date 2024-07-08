package MoMod.cards.power.constrcution;

import MoMod.Enums.AbstractCardEnum;
import MoMod.cards.Abstract.AbstractConstructionCard;
import MoMod.util.MoModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TechOilDerrick extends AbstractConstructionCard {
    public static final String ID = MoModHelper.makeID(TechOilDerrick.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardColor COLOR = AbstractCardEnum.SOVIET;

    public TechOilDerrick() {
        //为了命名规范修改了变量名。这些参数具体的作用见下方
        super(ID, false, CARD_STRINGS, COLOR, RARITY);
    }


    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        super.use(abstractPlayer, abstractMonster);
    }

    @Override
    public void triggerOnExhaust() {
        super.triggerOnExhaust();
    }
}
