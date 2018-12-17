package slimebound.cards;



import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import slimebound.SlimeboundMod;
import slimebound.actions.SlimeSpawnAction;
import slimebound.patches.AbstractCardEnum;
import slimebound.powers.SlimeSacrificePower;

import java.util.Random;


public class SlimeSacrifice extends AbstractSlimeboundCard {
    public static final String ID = "Slimebound:SlimeSacrifice";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    public static final String IMG_PATH = "cards/slimesacrifice.png";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardStrings cardStrings;

    private static final int COST = 0;
    private static final int BLOCK = 5;
    private static final int UPGRADE_BONUS = 3;

    public SlimeSacrifice() {
        super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);


        this.exhaust = true;
        this.magicNumber = this.baseMagicNumber = 1;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
       // Random random = new Random();
        //Integer chosenRand = random.nextInt(4);

/*
        if (chosenRand == 0) {
            AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.SplitAttack(), false, true));
        } else if (chosenRand == 1) {
            AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.SplitShield(), false, true));
        } else if (chosenRand == 2) {
            AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.SplitPoison(), false, true));
        } else {
            AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.SplitSliming(), false, true));
        }

        if (upgraded){
            if (chosenRand == 0) {
                AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.SplitAttack(), false, true));
            } else if (chosenRand == 1) {
                AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.SplitShield(), false, true));
            } else if (chosenRand == 2) {
                AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.SplitPoison(), false, true));
            } else {
                AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.SplitSliming(), false, true));
            }
        }
*/
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SlimeSacrificePower(p, this.magicNumber), this.magicNumber, true));


    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }

    public AbstractCard makeCopy() {
        return new SlimeSacrifice();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
           // this.exhaust = false;
            upgradeMagicNumber(1);
            this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();


        }
    }
}

