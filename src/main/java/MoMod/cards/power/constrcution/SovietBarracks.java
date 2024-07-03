package MoMod.cards.power.constrcution;

import MoMod.Actions.ConscriptReadyAction;
import MoMod.Actions.SovietBarracksUnitReadyAction;
import MoMod.Actions.UnitReadyAction;
import MoMod.Enums.AbstractCardEnum;
import MoMod.Enums.AbstractTagEnum;
import MoMod.cards.Abstract.AbstractConstructionCard;
import MoMod.cards.attack.AttackDog;
import MoMod.cards.attack.Boris;
import MoMod.cards.attack.FlakTrooper;
import MoMod.cards.attack.ShockTrooper;
import MoMod.power.EliteRseservesPower;
import MoMod.power.TechnologyLevelPower;
import MoMod.util.MoModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SovietBarracks extends AbstractConstructionCard {
    public static final String ID = MoModHelper.makeID(SovietBarracks.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardColor COLOR = AbstractCardEnum.SOVIET;

    public SovietBarracks() {
        super(ID, false, CARD_STRINGS, COLOR, RARITY);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        this.addToBot(new SovietBarracksUnitReadyAction());
    }
}
