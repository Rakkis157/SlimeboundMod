package slimebound.orbs;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import slimebound.SlimeboundMod;
import slimebound.actions.SlimeAutoAttack;
import slimebound.vfx.SlimeFlareEffect;


public class SlimingSlime
        extends SpawnedSlime {
    public static final String ID = "Slimebound:SlimingSlime";

    public SlimingSlime() {
        super(ID,-17, new Color (1.0F,.5F,1.0F,100F),"images/monsters/theBottom/slimeAltS/skeleton.atlas","images/monsters/theBottom/slimeAltS/skeleton.json","idle",.85F,new Color(224F/255F,113F/255F,224F/255F,2F),1, 2,true, new Color(.6F, .47F, .59F, 1), SlimeFlareEffect.OrbFlareColor.SLIMING, new Texture("SlimeboundImages/orbs/debuff2.png"), "SlimeboundImages/orbs/sliming.png");
        this.extraFontColor = Color.PURPLE;
        this.debuffAmount=2;

    }


    public void updateDescription() {
        this.description = this.descriptions[0] + (this.debuffAmount+ this.debuffBonusAmount + SlimeboundMod.getAcidTongueBonus(AbstractDungeon.player)) + this.descriptions[1];
    }


    public void activateEffectUnique() {

        AbstractDungeon.actionManager.addToBottom(new SlimeAutoAttack(AbstractDungeon.player,this.passiveAmount, AbstractGameAction.AttackEffect.BLUNT_LIGHT,this,false,true,false,2 + this.debuffBonusAmount + SlimeboundMod.getAcidTongueBonus(AbstractDungeon.player),false,0,false));

         }


    public AbstractOrb makeCopy() {
        return new SlimingSlime();
    }
}


