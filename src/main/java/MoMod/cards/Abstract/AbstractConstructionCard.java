package MoMod.cards.Abstract;

import MoMod.Actions.AddCardToConstructionPileAction;
import MoMod.Actions.ConstructionDestroyedAction;
import MoMod.Enums.AbstractTagEnum;
import MoMod.cards.power.constrcution.SovietBarracks;
import MoMod.cards.power.constrcution.SovietWarFactory;
import MoMod.power.AbstractMoPower;
import MoMod.power.SovietBarracksPower;
import MoMod.power.SovietWarFactoryPower;
import MoMod.util.ConstructionPileManager;
import MoMod.util.MoModHelper;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public abstract class AbstractConstructionCard extends MoCard {

    public AbstractConstructionCard(String ID, boolean useTmpArt, CardStrings strings, AbstractCard.CardColor color, AbstractCard.CardRarity RARITY) {
        super(ID, useTmpArt, strings, -2, CardType.POWER, color, RARITY, CardTarget.NONE);
        this.tags.add(AbstractTagEnum.CONSTRUCTION_CARD);
        this.exhaust = false;
    }

    public AbstractMoPower getPower(boolean AD) {
        String getCs = this.cardID.replace(MoModHelper.makeID(""), "");
        AbstractMoPower po = null;
        switch (getCs) {
            case "SovietBarracks": {
                po = new SovietBarracksPower(AbstractDungeon.player, AD);
                break;
            }
            case "SovietWarFactory": {
                po = new SovietWarFactoryPower(AbstractDungeon.player, AD);
                break;
            }
        }
        return po;
    }

    public void triggerOnExhaust() {
        AbstractMoPower po = this.getPower(false);
        this.addToTop(new ConstructionDestroyedAction());
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, po));
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }

}