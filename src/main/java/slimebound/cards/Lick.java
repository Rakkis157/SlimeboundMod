package slimebound.cards;



import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import slimebound.SlimeboundMod;
import slimebound.patches.AbstractCardEnum;
import slimebound.powers.SlimedPower;
import slimebound.vfx.LickEffect;
import slimebound.vfx.SlimeDripsEffect;
import slimebound.vfx.SlimeProjectileEffect;

import java.util.Random;

import static com.badlogic.gdx.graphics.Color.FOREST;
import static com.badlogic.gdx.graphics.Color.GREEN;


public class Lick extends AbstractSlimeboundCard {
    public static final String ID = "Slimebound:Lick";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    public static final String IMG_PATH = "cards/lick.png";
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final CardStrings cardStrings;
    private static final int COST = 0;
    private static final int POWER = 6;
    private static final int UPGRADE_BONUS = 3;


    public Lick() {

        super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);
        tags.add(SlimeboundMod.LICK);


        this.slimed = this.baseSlimed = 4;
        upgradeSlimed(0);
        this.poison = 1;
        this.exhaust = true;


    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new LickEffect(m.hb.cX, m.hb.cY,0.6F,new Color(GREEN)), 0.1F));

        AbstractDungeon.effectsQueue.add(new SlimeDripsEffect(m.hb.cX, m.hb.cY, 3));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, this.poison, false), this.poison, true, AbstractGameAction.AttackEffect.NONE));

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new SlimedPower(m, p, this.slimed  ), this.slimed , true, AbstractGameAction.AttackEffect.NONE));




        if (upgraded) AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
    }


    public AbstractCard makeCopy() {

        return new Lick();

    }


    public void upgrade() {

        if (!this.upgraded) {

            upgradeName();

            //upgradeMagicNumber(2);
            this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();

        }

    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    }
}


