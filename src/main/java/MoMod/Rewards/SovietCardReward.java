package MoMod.Rewards;

import MoMod.Enums.AbstractSovietRewardsEnum;
import basemod.ReflectionHacks;
import basemod.abstracts.CustomReward;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon.CurrentScreen;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.rewards.RewardItem;

public class SovietCardReward extends CustomReward {
    private static final String[] TEXT;
    public int amount;

    public SovietCardReward() {
        super(ImageMaster.REWARD_CARD_NORMAL, TEXT[2], AbstractSovietRewardsEnum.SOVIET_CARD_REWARD);
        this.hb = new Hitbox(460.0F * Settings.xScale, 90.0F * Settings.yScale);
        this.flashTimer = 0.0F;
        this.isDone = false;
        this.ignoreReward = false;
        this.redText = false;
        ReflectionHacks.setPrivate(this, RewardItem.class, "reticleColor", new Color(1.0F, 1.0F, 1.0F, 0.0F));
        this.type = AbstractSovietRewardsEnum.SOVIET_CARD_REWARD;
        ReflectionHacks.setPrivate(this, RewardItem.class, "isBoss", false);
    }

    public boolean claimReward() {
        if (AbstractDungeon.screen == CurrentScreen.COMBAT_REWARD) {
            AbstractDungeon.cardRewardScreen.open(this.cards, this, TEXT[4]);
            AbstractDungeon.previousScreen = CurrentScreen.COMBAT_REWARD;
        }

        return false;
    }

    public void generate_reward_cards() {
        this.cards.clear();
        this.cards.addAll(AbstractDungeon.getRewardCards());
    }

    static {
        TEXT = CardCrawlGame.languagePack.getUIString("RewardItem").TEXT;
    }
}

