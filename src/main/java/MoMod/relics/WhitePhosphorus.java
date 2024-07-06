package MoMod.relics;

import MoMod.util.MoModHelper;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class WhitePhosphorus extends CustomRelic {
    //遗物ID（此处的ModHelper在“04 - 本地化”中提到）
    public static final String ID = MoModHelper.makeID(WhitePhosphorus.class.getSimpleName());
    // 图片路径
    //todo:恐怖机器人遗物贴图
    private static final String IMG_PATH = MoModHelper.assetPath("img/relics/WhitePhosphorus.png");
    private static final String IMG_PATH_O = MoModHelper.assetPath("img/relics/outline/WhitePhosphorus.png");
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.BOSS;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public WhitePhosphorus() {
        super(ID, ImageMaster.loadImage(IMG_PATH), ImageMaster.loadImage(IMG_PATH_O), RELIC_TIER, LANDING_SOUND);
    }

    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new WhitePhosphorus();
    }
}