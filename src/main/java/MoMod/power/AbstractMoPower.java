package MoMod.power;

import MoMod.util.MoModHelper;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.UUID;

public abstract class AbstractMoPower extends AbstractPower {
    public boolean upgraded = false;
    public UUID Cuuid;

    public AbstractMoPower() {
        String path128 = MoModHelper.assetPath("/img/powers/TestPower84.png");
        String path48 = MoModHelper.assetPath("/img/powers/TestPower32.png");
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    public void updateDescription() {
    }

    public void updateTexture() {
    }

    public void onWear() {
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.limitedUpgrade();
            this.updateDescription();
        }

    }

    public void limitedUpgrade() {
    }

    public void onExhaust(AbstractCard card) {
        super.onExhaust(card);
    }
}
