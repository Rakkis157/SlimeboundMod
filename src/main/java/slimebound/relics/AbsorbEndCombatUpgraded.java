package slimebound.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import slimebound.orbs.SpawnedSlime;

public class AbsorbEndCombatUpgraded extends CustomRelic {
    public static final String ID = "Slimebound:AbsorbEndCombatUpgraded";
    public static final String IMG_PATH = "relics/heartofgooblack.png";
    public static final String OUTLINE_IMG_PATH = "relics/heartofgooOutline.png";
    private static final int HP_PER_CARD = 1;

    public AbsorbEndCombatUpgraded() {
        super(ID, new Texture(slimebound.SlimeboundMod.getResourcePath(IMG_PATH)), new Texture(slimebound.SlimeboundMod.getResourcePath(OUTLINE_IMG_PATH)),
                RelicTier.BOSS, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void onVictory() {
        this.flash();
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractPlayer p = AbstractDungeon.player;
        int slimeCount = 0;
        if (p.orbs.get(0) != null) {
            for (AbstractOrb o : AbstractDungeon.player.orbs) {

                if (o instanceof SpawnedSlime) {
                    slimeCount++;
                }


            }
            p.heal(slimeCount * 3);
        }
    }

    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }


    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(AbsorbEndCombat.ID);
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(AbsorbEndCombat.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(AbsorbEndCombat.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new AbsorbEndCombatUpgraded();
    }

}