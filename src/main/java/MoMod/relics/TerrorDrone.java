package MoMod.relics;

import MoMod.Actions.TechnologyUpgradeAction;
import MoMod.Enums.AbstractTagEnum;
import MoMod.power.TechnologyLevelPower;
import MoMod.util.ConstructionPileManager;
import MoMod.util.MoModHelper;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class TerrorDrone extends CustomRelic {
    //遗物ID（此处的ModHelper在“04 - 本地化”中提到）
    public static final String ID = MoModHelper.makeID(TerrorDrone.class.getSimpleName());
    // 图片路径
    //todo:恐怖机器人遗物贴图
    private static final String IMG_PATH = MoModHelper.assetPath("img/relics/SovietRelics.png");
    private static final String IMG_PATH_O = MoModHelper.assetPath("img/relics/outline/SovietRelics.png");
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.COMMON;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public TerrorDrone() {
        super(ID, ImageMaster.loadImage(IMG_PATH), ImageMaster.loadImage(IMG_PATH_O), RELIC_TIER, LANDING_SOUND);
    }

    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new TerrorDrone();
    }
}