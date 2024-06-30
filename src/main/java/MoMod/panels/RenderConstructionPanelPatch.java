

package MoMod.panels;

import MoMod.Enums.AbstractCharactersEnum;
import MoMod.panels.AbstractConstructionPanel;
import MoMod.util.ConstructionPileManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.OverlayMenu;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
//import txwzmod.Characters.txwz.MoMod.Enums;
//import txwzmod.Helpers.TxwzModHelper;

public class RenderConstructionPanelPatch {
    static AbstractConstructionPanel ConstructionPanel = new AbstractConstructionPanel();

    public RenderConstructionPanelPatch() {
    }

    @SpirePatch(
        clz = AbstractRoom.class,
        method = "update"
    )
    public static class onBattleStartEffect {
        public onBattleStartEffect() {
        }

        @SpireInsertPatch(
            rloc = 43
        )
        public static void Insert() {
        }
    }

    @SpirePatch(
        clz = OverlayMenu.class,
        method = "update"
    )
    public static class updatePatch {
        public updatePatch() {
        }

        @SpirePrefixPatch
        public static void Prefix() {
            AbstractPlayer p = AbstractDungeon.player;
            RenderConstructionPanelPatch.ConstructionPanel.updatePositions();
        }
    }

    @SpirePatch(
        clz = OverlayMenu.class,
        method = "showCombatPanels"
    )
    public static class showCombatPatch {
        public showCombatPatch() {
        }

        @SpirePrefixPatch
        public static void Prefix() {
            AbstractPlayer p = AbstractDungeon.player;
            RenderConstructionPanelPatch.ConstructionPanel.show();
        }
    }

    @SpirePatch(
        clz = OverlayMenu.class,
        method = "hideCombatPanels"
    )
    public static class hideCombatPatch {
        public hideCombatPatch() {
        }

        @SpirePrefixPatch
        public static void Prefix() {
            AbstractPlayer p = AbstractDungeon.player;
            RenderConstructionPanelPatch.ConstructionPanel.hide();
        }
    }

    @SpirePatch(
        clz = OverlayMenu.class,
        method = "render",
        paramtypez = {SpriteBatch.class}
    )
    public static class RenderPatch {
        public RenderPatch() {
        }

        @SpirePrefixPatch
        public static void Prefix(OverlayMenu _OM, SpriteBatch _sb) {
            CardGroup mp = ConstructionPileManager.getConstructionPile();
            if (AbstractDungeon.player != null && AbstractDungeon.player.chosenClass.equals(AbstractCharactersEnum.SOVIET) || !mp.isEmpty()) {
                RenderConstructionPanelPatch.ConstructionPanel.render(_sb);
            }
        }
    }
}
