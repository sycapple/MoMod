package MoMod.cards.Abstract;

import MoMod.Actions.ConstructionDestroyedAction;
import MoMod.Enums.AbstractTagEnum;
import MoMod.power.AbstractMoPower;
import MoMod.power.SovietBarracksPower;
import MoMod.power.SovietWarFactoryPower;
import MoMod.util.MoModHelper;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class TankUnitCard extends MoCard {
    //todo:坦克正常费用,加强数值
    public TankUnitCard(String ID, boolean useTmpArt, CardStrings strings, int COST, CardType TYPE, AbstractCard.CardColor color, AbstractCard.CardRarity RARITY, AbstractCard.CardTarget TARGET) {
        super(ID, useTmpArt, strings, COST, TYPE, color, RARITY, TARGET);
        this.tags.add(AbstractTagEnum.TANK_UNIT);
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }
}