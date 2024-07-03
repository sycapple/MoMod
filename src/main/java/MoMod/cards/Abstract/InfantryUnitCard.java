package MoMod.cards.Abstract;

import MoMod.Actions.ConstructionDestroyedAction;
import MoMod.Enums.AbstractTagEnum;
import MoMod.power.AbstractMoPower;
import MoMod.power.SovietBarracksPower;
import MoMod.power.SovietWarFactoryPower;
import MoMod.util.MoModHelper;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.ExhaustAllEtherealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class InfantryUnitCard extends MoCard {
    //todo: 步兵改为全部0费,降低数值
    public InfantryUnitCard(String ID, boolean useTmpArt, CardStrings strings, int COST, CardType TYPE, AbstractCard.CardColor color, AbstractCard.CardRarity RARITY, AbstractCard.CardTarget TARGET) {
        super(ID, useTmpArt, strings, COST, TYPE, color, RARITY, TARGET);
        this.tags.add(AbstractTagEnum.INFANTRY_UNIT);
    }

    public void triggerOnEndOfPlayerTurn() {
//        if(this.tags.contains(AbstractTagEnum.TRAINED_UNIT))
//            AbstractDungeon.player.hand.moveToExhaustPile(this);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }
}