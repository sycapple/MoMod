package MoMod.relics;

import MoMod.Actions.TechnologyUpgradeAction;
import MoMod.Enums.AbstractTagEnum;
import MoMod.power.TechnologyLevelPower;
import MoMod.util.ConstructionPileManager;
import MoMod.util.CyborgVanguardDamageAddonManager;
import MoMod.util.MoModHelper;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class SovietRelic extends CustomRelic {
    //Todo:科技不会迭代,升到顶就到顶,使用新 技能牌 科技归零 和 能力牌 科技迭代 来代替
    //遗物ID（此处的ModHelper在“04 - 本地化”中提到）
    public static final String ID = MoModHelper.makeID(SovietRelic.class.getSimpleName());
    // 图片路径
    private static final String IMG_PATH = MoModHelper.assetPath("img/relics/SovietRelics.png");
    private static final String IMG_PATH_O = MoModHelper.assetPath("img/relics/outline/SovietRelics.png");
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;
    private static final int amount = 4;

    public SovietRelic() {
        super(ID, ImageMaster.loadImage(IMG_PATH), ImageMaster.loadImage(IMG_PATH_O), RELIC_TIER, LANDING_SOUND);
        this.counter = 0;
    }

    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if (targetCard.tags.contains(AbstractTagEnum.BUILDING_CONSTRUCTION_CARD)) {
            ++this.counter;
            if (this.counter == amount) {
                this.counter = 0;
                this.flash();
                this.pulse = false;
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                this.addToBot(new TechnologyUpgradeAction());
            } else if (this.counter == amount - 1) {
                this.beginPulse();
                this.pulse = true;
            }
        }
    }

    @Override
    public void atBattleStart() {
        ConstructionPileManager.getConstructionPile().clear();
        ConstructionPileManager.resetMaxConstruction();
        CyborgVanguardDamageAddonManager.resetDamage();
        this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new TechnologyLevelPower(AbstractDungeon.player)));
//        CardGroup constructionGroup = ConstructionPileManager.getConstructionPile();
//        for (int i = 0; i < constructionGroup.size(); i++) {
//            AbstractConstructionCard c = (AbstractConstructionCard) constructionGroup.group.get(i);
//            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, c.getPower(true)));
//        }
//        if (this.counter == amount - 1) {
//            this.beginPulse();
//            this.pulse = true;
//        }
    }

    @Override
    public void atTurnStart() {
//        this.addToBot(new UseConstructionAction());
    }

    public AbstractRelic makeCopy() {
        return new SovietRelic();
    }
}