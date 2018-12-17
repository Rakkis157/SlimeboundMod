package slimebound.powers;


import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;


public class PreventSlimeDecayPower extends AbstractPower {
    public static final String POWER_ID = "Slimebound:PreventSlimeDecayPower";
    public static final String NAME = "Potency";
    public static PowerType POWER_TYPE = PowerType.DEBUFF;
    public static final String IMG = "powers/SlimedS.png";
    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName());

    public static String[] DESCRIPTIONS;
    private AbstractCreature source;


    public PreventSlimeDecayPower(AbstractCreature owner, AbstractCreature source, int amount) {

        this.name = NAME;

        this.ID = POWER_ID;


        this.owner = owner;

        this.source = source;


        this.img = new com.badlogic.gdx.graphics.Texture(SlimeboundMod.getResourcePath(IMG));

        this.type = POWER_TYPE;

        this.amount = amount;
        this.DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;

        updateDescription();

    }


    public void updateDescription() {


        if (this.amount > 1) {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];

        } else {
            this.description = DESCRIPTIONS[0];

        }

    }


    public void atStartOfTurn() {

        flash();

        if (this.amount <= 1) {

            AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, PreventSlimeDecayPower.POWER_ID));

        } else {

            AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ReducePowerAction(this.owner, this.owner, PreventSlimeDecayPower.POWER_ID, 1));

        }


    }


}



