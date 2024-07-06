package MoMod.modcore;


import MoMod.Enums.AbstractSovietRewardsEnum;
import MoMod.Rewards.SovietCardReward;
import MoMod.cards.attack.*;
import MoMod.cards.power.*;
import MoMod.cards.power.constrcution.*;
import MoMod.cards.skill.*;
import MoMod.characters.Soviet;
import MoMod.colorSet.SovietColorSet;
import MoMod.power.*;
import MoMod.relics.SovietRelic;
import MoMod.relics.TerrorDrone;
import MoMod.relics.WhitePhosphorus;
import MoMod.util.MoModHelper;
import MoMod.Enums.AbstractCardEnum;
import MoMod.Enums.AbstractCharactersEnum;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.rewards.RewardSave;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;


@SpireInitializer
public class MoMod implements EditCardsSubscriber, EditCharactersSubscriber, EditStringsSubscriber, EditRelicsSubscriber, AddAudioSubscriber, StartGameSubscriber, PostInitializeSubscriber, EditKeywordsSubscriber { // 实现接口
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

    public void receivePostInitialize() {
        BaseMod.registerCustomReward(AbstractSovietRewardsEnum.SOVIET_CARD_REWARD, (rewardSave) -> {
            SovietCardReward db = new SovietCardReward();
            return db;
        }, (customReward) -> new RewardSave(customReward.type.toString(), (String) null));
    }

    public static void initialize() {

        logger.info("========================= 开始初始化 =========================");
        new MoMod();
        logger.info("========================= 初始化完成 =========================");
    }

    public void receiveAddAudio() {
        logger.info("========================= 开始加载音效 =========================");
        BaseMod.addAudio("SOVIET_SELECT", MoModHelper.assetPath("sound/SovietSelect.ogg"));
        BaseMod.addAudio("MIG_BOMBING", MoModHelper.assetPath("sound/MigBombing.ogg"));
        logger.info("========================= 音效加载完毕 =========================");
    }

    public void receiveStartGame() {
    }

    // 当basemod开始注册mod卡牌时，便会调用这个函数
    @Override
    public void receiveEditCards() {
        // TODO 这里写添加你卡牌的代码
        logger.info("========================= 开始加载卡牌 =========================");
        BaseMod.addCard(new Conscript());
        BaseMod.addCard(new Engineer());
        BaseMod.addCard(new Build0Walls());
        BaseMod.addCard(new AttackDog());
        BaseMod.addCard(new Build0SovietBarracks());
        BaseMod.addCard(new SovietBarracks());
        BaseMod.addCard(new SovietWarFactory());
        BaseMod.addCard(new Build0SovietWarFactory());
        BaseMod.addCard(new RhinoHeavyTank());
        BaseMod.addCard(new LevelUpgrade());
        BaseMod.addCard(new Pyro());
        BaseMod.addCard(new ShockTrooper());
        BaseMod.addCard(new Arsonist());
        BaseMod.addCard(new Buratino());
        BaseMod.addCard(new IronDragon());
        BaseMod.addCard(new TeslaCruiser());
        BaseMod.addCard(new Wallbuster());
        BaseMod.addCard(new Walls());
        BaseMod.addCard(new EliteReserves());
        BaseMod.addCard(new FlakTrooper());
        BaseMod.addCard(new FuryDrone());
        BaseMod.addCard(new Build0Walls());
        BaseMod.addCard(new Boris());
        BaseMod.addCard(new CyborgVanguard());
        BaseMod.addCard(new ApocalypseTank());
        BaseMod.addCard(new LargeIterationPlan());
        BaseMod.addCard(new IrradiationGamma());
        BaseMod.addCard(new DebrisShelter());
        BaseMod.addCard(new MoonDrop());
        BaseMod.addCard(new SmokeBombs());
        BaseMod.addCard(new ExpandTerritory());
        BaseMod.addCard(new RepairDrone());
//        BaseMod.addCard(new TerrorDrone());
        BaseMod.addCard(new Build0IndustrialPlant());
        BaseMod.addCard(new IndustrialPlant());
        BaseMod.addCard(new GearChange());
        BaseMod.addCard(new TechOilDerrick());
        BaseMod.addCard(new Build0TechOilDerrick());
        BaseMod.addCard(new EMPControlStation());
        BaseMod.addCard(new Build0EMPControlStation());
        BaseMod.addCard(new EMPulse());
        BaseMod.addCard(new Build0SovietOreRefinery());
        BaseMod.addCard(new SovietOreRefinery());
        BaseMod.addCard(new WarMiner());
        BaseMod.addCard(new TacticalNuke());
        BaseMod.addCard(new Build0TacticalNukeSilo());
        BaseMod.addCard(new OverCharge());
        BaseMod.addCard(new TerrorDrop());
        BaseMod.addCard(new RepairCrane());
        BaseMod.addCard(new RadAttack());
        BaseMod.addCard(new EMPMines());
        BaseMod.addCard(new WarCash());
        BaseMod.addCard(new HydraCannon());
        BaseMod.addCard(new TankKiller());
        BaseMod.addCard(new TankDrop());
        BaseMod.addCard(new MortarQuad());
        BaseMod.addCard(new MotorAmbush());
        logger.info("========================= 卡牌加载完毕 =========================");
    }

    public void receiveEditRelics() {
        logger.info("========================= 开始加载遗物 =========================");
        BaseMod.addRelicToCustomPool(new SovietRelic(), AbstractCardEnum.SOVIET); // RelicType表示是所有角色都能拿到的遗物，还是一个角色的独有遗物
        BaseMod.addRelicToCustomPool(new TerrorDrone(), AbstractCardEnum.SOVIET); // RelicType表示是所有角色都能拿到的遗物，还是一个角色的独有遗物
        BaseMod.addRelicToCustomPool(new WhitePhosphorus(), AbstractCardEnum.SOVIET); // RelicType表示是所有角色都能拿到的遗物，还是一个角色的独有遗物
        logger.info("========================= 遗物加载完毕 =========================");
    }

    @Override
    public void receiveEditCharacters() {
        // 向basemod注册人物
        logger.info("========================= 开始加载人物 =========================");
        BaseMod.addCharacter(new Soviet(CardCrawlGame.playerName), MoModHelper.assetPath("img/character/SovietButton.png"), MoModHelper.assetPath("img/character/SovietCover.png"), AbstractCharactersEnum.SOVIET);
        logger.info("========================= 人物加载完毕 =========================");
    }

    public void receiveEditKeywords() {
        logger.info("========================= 加载关键词 =========================");
        Gson gson = new Gson();
        String lang = "ENG";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        } else if (Settings.language == Settings.GameLanguage.RUS) {
            lang = "RUS";
        }

        String json = Gdx.files.internal(MoModHelper.assetPath("localization/" + lang + "/keywords.json")).readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = (Keyword[]) gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            Keyword[] var5 = keywords;
            int var6 = keywords.length;
            for (int var7 = 0; var7 < var6; ++var7) {
                Keyword keyword = var5[var7];
                BaseMod.addKeyword("momod", keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
            }
        }
        logger.info("========================= 关键词加载毕 =========================");
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

