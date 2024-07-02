package MoMod.modcore;


import MoMod.cards.attack.AttackDog;
import MoMod.cards.attack.Conscript;
import MoMod.cards.attack.RhinoHeavyTank;
import MoMod.cards.power.Build0SovietBarracks;
import MoMod.cards.power.Build0SovietWarFactory;
import MoMod.cards.power.constrcution.SovietBarracks;
import MoMod.cards.power.constrcution.SovietWarFactory;
import MoMod.cards.skill.Engineer;
import MoMod.cards.skill.TestLevelUpgrade;
import MoMod.cards.skill.Walls;
import MoMod.characters.Soviet;
import MoMod.colorSet.SovietColorSet;
import MoMod.power.SovietBarracksPower;
import MoMod.power.SovietWarFactoryPower;
import MoMod.power.TechnologyLevelPower;
import MoMod.relics.SovietRelic;
import MoMod.util.MoModHelper;
import MoMod.Enums.AbstractCardEnum;
import MoMod.Enums.AbstractCharactersEnum;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.badlogic.gdx.utils.compression.lzma.Base;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@SpireInitializer
public class MoMod implements EditCardsSubscriber, EditCharactersSubscriber, EditStringsSubscriber, EditRelicsSubscriber { // 实现接口
    public static final Logger logger = LogManager.getLogger(MoMod.class.getSimpleName());

    public MoMod() {
        logger.debug("Constructor started.");
        BaseMod.subscribe(this); // 告诉basemod你要订阅事件
        BaseMod.addColor(AbstractCardEnum.SOVIET,
                SovietColorSet.SovietColor, SovietColorSet.SovietColor, SovietColorSet.SovietColor, SovietColorSet.SovietColor, SovietColorSet.SovietColor, SovietColorSet.SovietColor, SovietColorSet.SovietColor,
                SovietColorSet.attackBg,
                SovietColorSet.skillBg,
                SovietColorSet.powerBg,
                SovietColorSet.energyOrb,
                SovietColorSet.attackBgPortrait,
                SovietColorSet.skillBgPortrait,
                SovietColorSet.powerBgPortrait,
                SovietColorSet.energyOrbPortrait,
                SovietColorSet.cardEnergyOrb);
        logger.debug("Constructor finished.");
    }


    public static void initialize() {

        logger.info("========================= 开始初始化 =========================");
        new MoMod();
        logger.info("========================= 初始化完成 =========================");
    }

    // 当basemod开始注册mod卡牌时，便会调用这个函数
    @Override
    public void receiveEditCards() {
        // TODO 这里写添加你卡牌的代码
        logger.info("========================= 开始加载卡牌 =========================");
        BaseMod.addCard(new Conscript());
        BaseMod.addCard(new Engineer());
        BaseMod.addCard(new Walls());
        BaseMod.addCard(new AttackDog());
        BaseMod.addCard(new Build0SovietBarracks());
        BaseMod.addCard(new SovietBarracks());
        BaseMod.addCard(new SovietWarFactory());
        BaseMod.addCard(new Build0SovietWarFactory());
        BaseMod.addCard(new RhinoHeavyTank());
        BaseMod.addCard(new TestLevelUpgrade());
        logger.info("========================= 卡牌加载完毕 =========================");
        logger.info("========================= 开始加载能力 =========================");
        BaseMod.addPower(SovietBarracksPower.class, SovietBarracksPower.POWER_ID);
        BaseMod.addPower(SovietWarFactoryPower.class, SovietWarFactoryPower.POWER_ID);
        BaseMod.addPower(TechnologyLevelPower.class, TechnologyLevelPower.POWER_ID);
        logger.info("========================= 能力加载完毕 =========================");
    }

    public void receiveEditRelics() {
        logger.info("========================= 开始加载遗物 =========================");
        BaseMod.addRelic(new SovietRelic(), RelicType.SHARED); // RelicType表示是所有角色都能拿到的遗物，还是一个角色的独有遗物
        logger.info("========================= 遗物加载完毕 =========================");
    }

    @Override
    public void receiveEditCharacters() {
        // 向basemod注册人物
        logger.info("========================= 开始加载人物 =========================");
        BaseMod.addCharacter(new Soviet(CardCrawlGame.playerName), MoModHelper.assetPath("img/character/SovietButton.png"), MoModHelper.assetPath("img/character/SovietCover.png"), AbstractCharactersEnum.SOVIET);
        logger.info("========================= 人物加载完毕 =========================");
    }

    public void receiveEditStrings() {
        String lang;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        } else {
            lang = "ENG";
        }
        // 这里添加注册本地化文本
        BaseMod.loadCustomStringsFile(CardStrings.class, MoModHelper.assetPath("localization/" + lang + "/cards.json"));
        BaseMod.loadCustomStringsFile(CharacterStrings.class, MoModHelper.assetPath("localization/" + lang + "/characters.json"));
        BaseMod.loadCustomStringsFile(UIStrings.class, MoModHelper.assetPath("localization/" + lang + "/ui.json"));
        BaseMod.loadCustomStringsFile(PowerStrings.class, MoModHelper.assetPath("localization/" + lang + "/powers.json"));
        BaseMod.loadCustomStringsFile(RelicStrings.class, MoModHelper.assetPath("localization/" + lang + "/relics.json"));
    }
}

