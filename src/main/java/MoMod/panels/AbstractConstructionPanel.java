

package MoMod.panels;

import MoMod.cards.Special.EmptyConstructionSlot;
import MoMod.patches.ConstructionFontPatch;
import MoMod.util.ConstructionPileManager;
import MoMod.util.MoModHelper;
import MoMod.util.TextureLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;
import com.megacrit.cardcrawl.ui.panels.AbstractPanel;

import java.lang.reflect.Field;

//import txwzmod.Cards.Special.KongCaoWei;
//import txwzmod.Helpers.TxwzModHelper;
//import txwzmod.Panels.MechaFontPatch.MechaFontField;
//import txwzmod.Powers.Mechas.MechaJiJiaZhaoHuan;

public class AbstractConstructionPanel extends AbstractPanel {
    public static float AnimaP_locx;
    public static float AnimaP_locy;
    public static float hide_x;
    public static float shadow_offset;
    public static int totalCount;
    private Hitbox tipHitbox;
    private static final Color ANIMA_TEXT_COLOR;
    //    private static final PowerStrings tutorialStrings;
    private static final int Max_Anima = 999;
    private static final float AbstractCard_HB_W;
    private static final float AbstractCard_HB_H;
    private static final float zoomRatio = 0.38F;
    private static final float mechaLength;
    private static final float boxWidth;
    private static final float boxHeight;
    private static final float boxLeftMove;
    private static final float boxDownMove;
    private static final float distanceBetweenMechaAndBox;
    private static AbstractCard ret;
    public static Texture emptyMecha;
    public static Texture backMecha;
    public static Texture boxMecha;
    private static Field frameShadowColorField;

    static {
        AnimaP_locx = 235.0F * Settings.xScale;
        AnimaP_locy = 780.0F * Settings.yScale;
        hide_x = -480.0F * Settings.scale;
        shadow_offset = 12.0F * Settings.scale;
        ANIMA_TEXT_COLOR = new Color(1.0F, 1.0F, 1.0F, 1.0F);
//        tutorialStrings = CardCrawlGame.languagePack.getPowerStrings();
        AbstractCard_HB_W = 300.0F * Settings.scale;
        AbstractCard_HB_H = 420.0F * Settings.scale;
        mechaLength = 1600.0F * Settings.scale;
        boxWidth = 2266.279F * Settings.scale;
        boxHeight = 831.3953F * Settings.scale;
        boxLeftMove = 332.5581F * Settings.scale;
        boxDownMove = 197.6744F * Settings.scale;
        distanceBetweenMechaAndBox = 6.0F * Settings.scale;
        ret = null;
        emptyMecha = TextureLoader.getTexture(MoModHelper.assetPath("/img/UI/Panel/EmptyConstructionSlot.png"));
        backMecha = TextureLoader.getTexture(MoModHelper.assetPath("/img/UI/Panel/BackConstructionPanel.png"));
        boxMecha = TextureLoader.getTexture(MoModHelper.assetPath("/img/UI/Panel/BoxConstruction.png"));
        frameShadowColorField = null;
    }

    public AbstractConstructionPanel() {
        super(AnimaP_locx, AnimaP_locy, hide_x, AnimaP_locy, shadow_offset, -shadow_offset, (Texture) null, true);
        this.tipHitbox = new Hitbox(AnimaP_locx - AbstractCard_HB_W * 0.38F / 2.0F, AnimaP_locy - AbstractCard_HB_H * 0.38F / 2.0F, mechaLength * 0.38F, AbstractCard_HB_H * 0.38F);
    }

    public static void setAnima(int anima) {
        totalCount = anima;
        if (totalCount > 999) {
            totalCount = 999;
        }

    }

    public static void increaseAnima(int e) {
        totalCount += e;
        if (totalCount > 999) {
            totalCount = 999;
        }

    }

    public static void decreaseAnima(int e) {
        totalCount -= e;
        if (totalCount < 0) {
            totalCount = 0;
        }

    }

    public static int getAnima() {
        return totalCount;
    }

    public void updatePositions() {
        super.updatePositions();
        if (!this.isHidden) {
            this.tipHitbox.update();
        }

    }

    private BitmapFont getPanelFont() {
        return (BitmapFont) ConstructionFontPatch.ConstructionFontField.FightHardFont.get();
    }

    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        int maxConstruction = ConstructionPileManager.getMaxConstruction();
        if (!this.isHidden && maxConstruction > 1) {
            float extendMoveOnce = (mechaLength - AbstractCard_HB_W) * 0.38F / (float) (maxConstruction - 1);
            float leftMove = 0.0F;
            if (maxConstruction <= 5) {
                extendMoveOnce -= distanceBetweenMechaAndBox * 2.0F / (float) (maxConstruction - 1);
                leftMove = distanceBetweenMechaAndBox;
            }

            this.tipHitbox.render(sb);
            CardGroup mp = ConstructionPileManager.getConstructionPile();
            // 渲染放牌的盒子
            sb.draw(boxMecha, this.tipHitbox.x - boxLeftMove * 0.38F, this.tipHitbox.y - boxDownMove * 0.38F, boxWidth * 0.38F, boxHeight * 0.38F);

            for (int i = maxConstruction - 1; i >= 0; --i) {
                AbstractCard hovered;
                if (mp.group.size() <= i) {
                    // 槽位中一张牌都没有就全部渲染空槽位提示
                    AbstractCard c = new EmptyConstructionSlot();
                    c.flash();
                    hovered = renderCard(this.tipHitbox.x + leftMove + extendMoveOnce * (float) i, this.tipHitbox.y, sb, c, i, 0.38F, true);
                    if (hovered != null) {
                        ret = hovered;
                    }

                    if (ret != null && !ret.hb.hovered && hovered == null) {
                        ret = null;
                    }
                    // 渲染提示气泡
                    //renderGenericTip
                    //{
                    //    float x,
                    //    float y,
                    //    String header,
                    //    String body
                    //}
                    if (hovered != null) {
                        TipHelper.renderGenericTip((float) InputHelper.mX + 20.0F * Settings.scale, (float) InputHelper.mY, "空槽位", "这是一个空建筑槽位。");
                    }
                } else {
                    // 有牌就分类渲染
                    AbstractCard c = (AbstractCard) mp.group.get(i);
                    //闪一下
                    c.flash();
                    hovered = renderCard(this.tipHitbox.x + leftMove + extendMoveOnce * (float) i, this.tipHitbox.y, sb, c, i, 0.38F, true);
                    if (hovered != null) {
                        ret = hovered;
                    }

                    if (ret != null && !ret.hb.hovered && hovered == null) {
                        ret = null;
                    }

                    String getS2;
                    AbstractPower p2;
                    if (hovered != null) {
//                        getS2 = TxwzModHelper.MakePath("Mecha" + TxwzModHelper.pureId(hovered.cardID) + c.uuid);
//                        p2 = AbstractDungeon.player.getPower(getS2);
                        if (ret != null && ret.hb.hovered && AbstractDungeon.getCurrRoom().phase == RoomPhase.COMBAT && !AbstractDungeon.isScreenUp)
                            TipHelper.renderGenericTip((float) InputHelper.mX + 20.0F * Settings.scale, (float) InputHelper.mY, hovered.name, hovered.rawDescription);
                    }
                }
                // 根据取得的卡牌取得对应的能力牌
//                    getS2 = TxwzModHelper.MakePath("Mecha" + TxwzModHelper.pureId(c.cardID) + c.uuid);
                //这个应该是显示这个牌的能力
//                p2 = AbstractDungeon.player.getPower(getS2);
//                if (p2 != null && p2.amount != -1) {
//                    String animaMsg = " " + p2.amount + " ";
//                    BitmapFont f = this.getPanelFont();
                // 这个是在卡牌槽位右下角显示能力的点数
//                    FontHelper.renderRotatedText(sb, f, animaMsg, this.current_x + extendMoveOnce * (float) i, this.current_y, (AbstractCard_HB_W / 2.0F - 13.0F * Settings.scale) * 0.38F, (-AbstractCard_HB_H / 2.0F + 29.0F * Settings.scale) * 0.38F, 0.0F, false, Color.WHITE);
//                }
            }
        }
    }


    private static AbstractCard renderCard(float x, float y, SpriteBatch sb, AbstractCard card, int i, float scale, boolean hitbox) {
        AbstractCard hovered = null;
        float prev_current_x = card.current_x;
        float prev_current_y = card.current_y;
        float prev_drawScale = card.drawScale;
        float prev_angle = card.angle;
        card.current_x = x + AbstractCard_HB_W * 0.38F / 2.0F;
        card.current_y = y + AbstractCard_HB_H * 0.38F / 2.0F;
        card.drawScale = scale;
        card.angle = 0.0F;

        // 边框高亮?
        card.lighten(true);

        // 这个应该是鼠标放上去然后让卡片放大显示
        if (hitbox) {
            card.hb.move(card.current_x, card.current_y);
            card.hb.resize(AbstractCard_HB_W * card.drawScale, AbstractCard_HB_H * card.drawScale);
            card.hb.update();
            if (card.hb.hovered) {
                hovered = card;
            }
        }

        Color frameShadowColor = null;
        float prev_frameShadow_a = 0.0F;
        if (hitbox) {
            try {
                if (frameShadowColorField == null) {
                    frameShadowColorField = AbstractCard.class.getDeclaredField("frameShadowColor");
                    frameShadowColorField.setAccessible(true);
                }

                frameShadowColor = (Color) frameShadowColorField.get(card);
                prev_frameShadow_a = frameShadowColor.a;
                frameShadowColor.a = 0.0F;
            } catch (NoSuchFieldException | IllegalAccessException var15) {
                var15.printStackTrace();
            }
        }

        card.render(sb);
        if (hitbox) {
            frameShadowColor.a = prev_frameShadow_a;
        }

        card.current_x = prev_current_x;
        card.current_y = prev_current_y;
        card.drawScale = prev_drawScale;
        card.angle = prev_angle;
        return hovered;
    }


}