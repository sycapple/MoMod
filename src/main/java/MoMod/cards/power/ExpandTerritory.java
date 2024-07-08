package MoMod.cards.power;


import MoMod.Enums.AbstractCardEnum;
import MoMod.cards.Abstract.MoCard;
import MoMod.patches.PlayerHasConstructionPilePatch;
import MoMod.util.ConstructionPileManager;
import MoMod.util.MoModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

import java.util.Objects;

//todo:有的时候有无法打出的bug,同时伴随能量不足,不知道
public class ExpandTerritory extends MoCard {
    public static final String ID = MoModHelper.makeID(ExpandTerritory.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 2;
    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardColor COLOR = AbstractCardEnum.SOVIET;

    public ExpandTerritory() {
        // 为了命名规范修改了变量名。这些参数具体的作用见下方
        //todo:开疆拓土卡牌贴图
        super(ID, true, CARD_STRINGS, COST, TYPE, COLOR, RARITY, TARGET);
        this.setupMagicNumber(2);
    }

    @Override
    public void limitedUpgrade() {
        this.upgradeMagicNumber(1);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (Objects.equals(ConstructionPileManager.getMaxConstruction(), PlayerHasConstructionPilePatch.MaxConstructionAvailable)) {
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, CardCrawlGame.languagePack.getUIString(MoModHelper.makeID(ExpandTerritory.class.getSimpleName())).TEXT[0], true));
            return false;
        }
        return true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ConstructionPileManager.addMaxConstruction(this.magicNumber);
    }

}
