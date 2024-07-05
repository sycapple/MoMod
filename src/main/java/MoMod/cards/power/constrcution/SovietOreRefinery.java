package MoMod.cards.power.constrcution;

import MoMod.Actions.SovietBarracksUnitReadyAction;
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

public class SovietOreRefinery extends AbstractConstructionCard {
    public static final String ID = MoModHelper.makeID(SovietOreRefinery.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardColor COLOR = AbstractCardEnum.SOVIET;

    public SovietOreRefinery() {
        super(ID, true, CARD_STRINGS, COLOR, RARITY);
        //todo:苏军矿场能力贴图
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        super.use(abstractPlayer, abstractMonster);
    }
}
