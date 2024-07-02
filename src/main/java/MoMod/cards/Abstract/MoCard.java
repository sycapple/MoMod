

package MoMod.cards.Abstract;

import MoMod.util.MoModHelper;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;


public abstract class MoCard extends CustomCard {
    public int baseSelfDamage;
    private int baseSelfBlock;
    public int selfDamage;
    public int selfBlock;
    private boolean isSelfDamageModified;
    private boolean isSelfBlockModified;
    public boolean isSecondaryMModified;
    public boolean upgradesecondaryM;
    public int secondaryM;

    public MoCard(String ID, boolean useTmpArt, CardStrings strings, int COST, AbstractCard.CardType TYPE, AbstractCard.CardColor color, AbstractCard.CardRarity RARITY, AbstractCard.CardTarget TARGET) {
        super(ID, strings.NAME, useTmpArt ? GetTmpImgPath(TYPE) : GetImgPath(TYPE, ID), COST, strings.DESCRIPTION, TYPE, color, RARITY, TARGET);
    }

    private static String GetTmpImgPath(AbstractCard.CardType t) {
        String type;
        switch (t) {
            case ATTACK:
                type = "attack";
                break;
            case POWER:
                type = "power";
                break;
            case STATUS:
            case CURSE:
            case SKILL:
                type = "skill";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + t);
        }

        return String.format(MoModHelper.assetPath("img/cards/test_%s.png"), type);
    }

    private static String GetImgPath(AbstractCard.CardType t, String name) {
        String type;
        switch (t) {
            case ATTACK:
                type = "attack";
                break;
            case POWER:
                type = "power";
                break;
            case STATUS:
            default:
                type = "special";
                break;
            case CURSE:
                type = "curse";
                break;
            case SKILL:
                type = "skill";
        }
        return String.format(MoModHelper.assetPath("img/cards/%s/%s.png"), type, name.replace(MoModHelper.makeID(""), ""));
    }

    // 初始化伤害点数
    protected void setupDamage(int amt) {
        this.baseDamage = amt;
        this.damage = amt;
    }

    // 初始化格挡点数
    protected void setupBlock(int amt) {
        this.baseBlock = amt;
        this.block = amt;
    }

    // 初始化能力点数
    protected void setupMagicNumber(int amt) {
        this.baseMagicNumber = amt;
        this.magicNumber = amt;
    }

    //Todo: 不知道干啥的
    protected void setupSecondaryMagicNumber(int amt) {
        this.secondaryM = amt;
    }

    // 升级描述修改
    protected void upgradeDescription(CardStrings cardStrings) {
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        this.initializeDescription();
    }

    // 单体伤害
    public void damageToEnemy(AbstractMonster m, AbstractGameAction.AttackEffect effect) {
        this.addToBot(new DamageAction(m, new DamageInfo(AbstractDungeon.player, this.damage), effect));
    }

    // 群体伤害
    public void damageToAllEnemies(AbstractGameAction.AttackEffect effect) {
        this.addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, this.multiDamage, this.damageTypeForTurn, effect));
    }

    public void damageToRandomEnemies(AbstractGameAction.AttackEffect effect) {
        this.addToBot(new AttackDamageRandomEnemyAction(this, effect));
    }

    // 获得牌的定义格挡点数
    public void gainBlock() {
        this.addToBot(new GainBlockAction(AbstractDungeon.player, this.block));
    }

    // 获得牌任意格挡点数
    public void gainBlock(int amt) {
        this.addToBot(new GainBlockAction(AbstractDungeon.player, amt));
    }

    // 抽特定牌数
    public void drawCards(int amt) {
        this.addToBot(new DrawCardAction(amt));
    }

    // 将能力应用给角色
    public void applyToPlayer(AbstractPower power) {
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, power));
    }

    // 升级
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.limitedUpgrade();
        }

    }

    // Todo: 不知道
    public void limitedUpgrade() {
    }

    // Todo: 应该是看一个能力有几层
//    public boolean checkStrategyBiggerThanWhenUse(int a) {
//        return TxwzModHelper.getStrategy() >= a;
//    }

    // Todo: 不知道
    protected void upgradeSecondaryM(int amount) {
        this.secondaryM += amount;
        this.upgradesecondaryM = true;
    }

    // Todo: 不知道
//    public ArrayList<AbstractCard> getNearCard(AbstractCard c) {
//        ArrayList<AbstractCard> cl = new ArrayList();
//        if (AbstractDungeon.getCurrRoom().phase == RoomPhase.COMBAT) {
//            AbstractCard leftCard = null;
//            if (AbstractDungeon.player.hasRelic(HangHaiLuoPan.ID)) {
//                leftCard = AbstractDungeon.player.hand.group.isEmpty() ? null : (AbstractCard) AbstractDungeon.player.hand.group.get(0);
//            }
//
//            if (TxwzModHelper.getIsChained(c) || leftCard != null && leftCard == c) {
//                ArrayList<AbstractCard> resCList = new ArrayList();
//                Iterator var9 = AbstractDungeon.player.hand.group.iterator();
//
//                while (var9.hasNext()) {
//                    AbstractCard c2 = (AbstractCard) var9.next();
//                    if (!c2.uuid.equals(c.uuid)) {
//                        resCList.add(c2);
//                    }
//                }
//
//                return resCList;
//            }
//
//            int index = AbstractDungeon.player.hand.group.indexOf(c);
//            int near = 1;
//            if (AbstractDungeon.player.hasPower(CiNengZhenFaPower.POWER_ID)) {
//                near += AbstractDungeon.player.getPower(CiNengZhenFaPower.POWER_ID).amount;
//            }
//
//            for (int i = 0; i < AbstractDungeon.player.hand.group.size(); ++i) {
//                AbstractCard c2 = (AbstractCard) AbstractDungeon.player.hand.group.get(i);
//                if (TxwzModHelper.getIsChained(c2) && c2 != c && !cl.contains(c2)) {
//                    cl.add(c2);
//                } else if (index != -1 && i != index && i >= index - near && i <= index + near && c2 != c && !cl.contains(c2)) {
//                    cl.add(c2);
//                }
//            }
//
//            if (leftCard != null && !cl.contains(leftCard) && leftCard != c) {
//                cl.add(leftCard);
//            }
//        }
//
//        return cl;
//    }
    // 获取虚弱的敌人
    public static int getWeekEnemy() {
        int targetI = -1;
        ArrayList<Integer> maxHpMonsterIndexList = new ArrayList();
        int temp = AbstractDungeon.getCurrRoom().monsters.monsters.size();
        ArrayList<AbstractMonster> MaxHpMonster = new ArrayList();

        for (int i = 0; i < temp; ++i) {
            AbstractMonster m3 = (AbstractMonster) AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if (!m3.isDeadOrEscaped() && m3.currentHealth > 0) {
                if (MaxHpMonster.size() == 0) {
                    MaxHpMonster.add(m3);
                    maxHpMonsterIndexList.add(i);
                } else {
                    AbstractMonster chosenM = (AbstractMonster) MaxHpMonster.get(0);
                    if (m3.currentHealth < chosenM.currentHealth) {
                        maxHpMonsterIndexList.clear();
                        maxHpMonsterIndexList.add(i);
                        MaxHpMonster.clear();
                        MaxHpMonster.add(m3);
                    } else if (m3.currentHealth == chosenM.currentHealth) {
                        maxHpMonsterIndexList.add(i);
                        MaxHpMonster.add(m3);
                    }
                }
            }
        }

        if (maxHpMonsterIndexList.size() > 1) {
            targetI = (Integer) maxHpMonsterIndexList.get(AbstractDungeon.cardRandomRng.random(0, maxHpMonsterIndexList.size() - 1));
        } else if (maxHpMonsterIndexList.size() == 1) {
            targetI = (Integer) maxHpMonsterIndexList.get(0);
        }

        return targetI;
    }


}
