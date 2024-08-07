package MoMod.cards.power.constrcution;

import MoMod.Actions.SovietWarFactoryUnitReadyAction;
import MoMod.Enums.AbstractCardEnum;
import MoMod.cards.Abstract.AbstractConstructionCard;
import MoMod.cards.attack.Conscript;
import MoMod.util.MoModHelper;
import MoMod.util.UnitToTrain;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SovietWarFactory extends AbstractConstructionCard {
    public static final String ID = MoModHelper.makeID(SovietWarFactory.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardColor COLOR = AbstractCardEnum.SOVIET;

    public SovietWarFactory() {
        super(ID, false, CARD_STRINGS, COLOR, RARITY);
        this.cardsToPreview = new UnitToTrain(this, AbstractDungeon.player).getUnit() == null ? new Conscript() :
                new UnitToTrain(this, AbstractDungeon.player).getUnit();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new SovietWarFactoryUnitReadyAction());
    }
}
