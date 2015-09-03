//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class EntityDayZombie extends EntityMob
{
    protected static final IAttribute field_110186_bp = (new RangedAttribute("zombie.spawnReinforcements", 0.0D, 0.0D, 1.0D)).setDescription("Spawn Reinforcements Chance");
    private static final UUID babySpeedBoostUUID = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
    private static final AttributeModifier babySpeedBoostModifier;
    private final EntityAIBreakDoor field_146075_bs = new EntityAIBreakDoor(this);
    private int conversionTime;
    private boolean field_146076_bu = false;
    private float field_146074_bv = -1.0F;
    private float field_146073_bw;
    private static final String __OBFID = "CL_00001702";

    public EntityDayZombie(World p_i1745_1_)
    {
        super(p_i1745_1_);
        this.getNavigator().setBreakDoors(true);
        super.tasks.addTask(0, new EntityAISwimming(this));
        super.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        super.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
        super.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        super.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
        super.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        super.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        super.tasks.addTask(8, new EntityAILookIdle(this));
        super.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        super.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        super.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
        this.setSize(0.6F, 1.8F);
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.20000000417232514D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
        this.getAttributeMap().registerAttribute(field_110186_bp).setBaseValue(super.rand.nextDouble() * ForgeModContainer.zombieSummonBaseChance);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.getDataWatcher().addObject(12, Byte.valueOf((byte) 0));
        this.getDataWatcher().addObject(13, Byte.valueOf((byte) 0));
        this.getDataWatcher().addObject(14, Byte.valueOf((byte) 0));
    }

    public int getTotalArmorValue()
    {
        int i = super.getTotalArmorValue() + 2;
        if (i > 20)
        {
            i = 20;
        }

        return i;
    }

    protected boolean isAIEnabled()
    {
        return true;
    }

    public boolean func_146072_bX()
    {
        return this.field_146076_bu;
    }

    public void func_146070_a(boolean p_146070_1_)
    {
        if (this.field_146076_bu != p_146070_1_)
        {
            this.field_146076_bu = p_146070_1_;
            if (p_146070_1_)
            {
                super.tasks.addTask(1, this.field_146075_bs);
            } else
            {
                super.tasks.removeTask(this.field_146075_bs);
            }
        }

    }

    public boolean isChild()
    {
        return this.getDataWatcher().getWatchableObjectByte(12) == 1;
    }

    protected int getExperiencePoints(EntityPlayer p_70693_1_)
    {
        if (this.isChild())
        {
            super.experienceValue = (int) ((float) super.experienceValue * 2.5F);
        }

        return super.getExperiencePoints(p_70693_1_);
    }

    public void setChild(boolean p_82227_1_)
    {
        p_82227_1_ = false;
        this.getDataWatcher().updateObject(12, Byte.valueOf((byte) (p_82227_1_ ? 1 : 0)));
        if (super.worldObj != null && !super.worldObj.isRemote)
        {
            IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
            iattributeinstance.removeModifier(babySpeedBoostModifier);
            if (p_82227_1_)
            {
                iattributeinstance.applyModifier(babySpeedBoostModifier);
            }
        }

        this.func_146071_k(p_82227_1_);
    }

    public boolean isVillager()
    {
        return this.getDataWatcher().getWatchableObjectByte(13) == 1;
    }

    public void setVillager(boolean p_82229_1_)
    {
        this.getDataWatcher().updateObject(13, Byte.valueOf((byte) (p_82229_1_ ? 1 : 0)));
    }

    public void onLivingUpdate()
    {
        if (super.worldObj.isDaytime() && !super.worldObj.isRemote && !this.isChild())
        {
            float f = this.getBrightness(1.0F);
            if (f > 0.5F)
            {
                super.entityAge -= 2;
                if (super.entityAge < 0)
                {
                    super.entityAge = 0;
                }
            } else
            {
                super.entityAge += 2;
            }

            if (f > 0.5F && super.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && super.worldObj.canBlockSeeTheSky(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.posY), MathHelper.floor_double(super.posZ)))
            {
                boolean flag = true;
                ItemStack itemstack = this.getEquipmentInSlot(4);
                if (itemstack != null)
                {
                    if (itemstack.isItemStackDamageable())
                    {
                        itemstack.setItemDamage(itemstack.getItemDamageForDisplay() + super.rand.nextInt(2));
                        if (itemstack.getItemDamageForDisplay() >= itemstack.getMaxDamage())
                        {
                            this.renderBrokenItemStack(itemstack);
                            this.setCurrentItemOrArmor(4, (ItemStack) null);
                        }
                    }

                    flag = false;
                }

                if (flag)
                {
                    ;
                }
            }
        }

        if (this.isRiding() && this.getAttackTarget() != null && super.ridingEntity instanceof EntityChicken)
        {
            ((EntityLiving) super.ridingEntity).getNavigator().setPath(this.getNavigator().getPath(), 1.5D);
        }

        super.onLivingUpdate();
    }

    public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
    {
        if (!super.attackEntityFrom(p_70097_1_, p_70097_2_))
        {
            return false;
        } else
        {
            EntityLivingBase entitylivingbase = this.getAttackTarget();
            if (entitylivingbase == null && this.getEntityToAttack() instanceof EntityLivingBase)
            {
                entitylivingbase = (EntityLivingBase) this.getEntityToAttack();
            }

            if (entitylivingbase == null && p_70097_1_.getEntity() instanceof EntityLivingBase)
            {
                entitylivingbase = (EntityLivingBase) p_70097_1_.getEntity();
            }

            return true;
        }
    }

    public void onUpdate()
    {
        if (!super.worldObj.isRemote && this.isConverting())
        {
            int i = this.getConversionTimeBoost();
            this.conversionTime -= i;
            if (this.conversionTime <= 0)
            {
                this.convertToVillager();
            }
        }

        super.onUpdate();
    }

    public boolean attackEntityAsMob(Entity p_70652_1_)
    {
        boolean flag = super.attackEntityAsMob(p_70652_1_);
        if (flag)
        {
            int i = super.worldObj.difficultySetting.getDifficultyId();
            if (this.getHeldItem() == null && this.isBurning() && super.rand.nextFloat() < (float) i * 0.3F)
            {
                p_70652_1_.setFire(2 * i);
            }
        }

        return flag;
    }

    protected String getLivingSound()
    {
        return "mob.zombie.say";
    }

    protected String getHurtSound()
    {
        return "mob.zombie.hurt";
    }

    protected String getDeathSound()
    {
        return "mob.zombie.death";
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
    {
        this.playSound("mob.zombie.step", 0.15F, 1.0F);
    }

    protected Item getDropItem()
    {
        return Items.rotten_flesh;
    }

    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    protected void dropRareDrop(int p_70600_1_)
    {
        switch (super.rand.nextInt(3))
        {
            case 0:
                this.dropItem(Items.iron_ingot, 1);
                break;
            case 1:
                this.dropItem(Items.carrot, 1);
                break;
            case 2:
                this.dropItem(Items.potato, 1);
        }

    }

    protected void addRandomArmor()
    {
        super.addRandomArmor();
        if (super.rand.nextFloat() < (super.worldObj.difficultySetting == EnumDifficulty.HARD ? 0.05F : 0.01F))
        {
            int i = super.rand.nextInt(3);
            if (i == 0)
            {
                this.setCurrentItemOrArmor(0, new ItemStack(Items.iron_sword));
            } else
            {
                this.setCurrentItemOrArmor(0, new ItemStack(Items.iron_shovel));
            }
        }

    }

    public void writeEntityToNBT(NBTTagCompound p_70014_1_)
    {
        super.writeEntityToNBT(p_70014_1_);
        if (this.isChild())
        {
            p_70014_1_.setBoolean("IsBaby", true);
        }

        if (this.isVillager())
        {
            p_70014_1_.setBoolean("IsVillager", true);
        }

        p_70014_1_.setInteger("ConversionTime", this.isConverting() ? this.conversionTime : -1);
        p_70014_1_.setBoolean("CanBreakDoors", this.func_146072_bX());
    }

    public void readEntityFromNBT(NBTTagCompound p_70037_1_)
    {
        super.readEntityFromNBT(p_70037_1_);
        if (p_70037_1_.getBoolean("IsBaby"))
        {
            this.setChild(true);
        }

        if (p_70037_1_.getBoolean("IsVillager"))
        {
            this.setVillager(true);
        }

        if (p_70037_1_.hasKey("ConversionTime", 99) && p_70037_1_.getInteger("ConversionTime") > -1)
        {
            this.startConversion(p_70037_1_.getInteger("ConversionTime"));
        }

        this.func_146070_a(p_70037_1_.getBoolean("CanBreakDoors"));
    }

    public void onKillEntity(EntityLivingBase p_70074_1_)
    {
        super.onKillEntity(p_70074_1_);
        if ((super.worldObj.difficultySetting == EnumDifficulty.NORMAL || super.worldObj.difficultySetting == EnumDifficulty.HARD) && p_70074_1_ instanceof EntityVillager)
        {
            if (super.worldObj.difficultySetting != EnumDifficulty.HARD && super.rand.nextBoolean())
            {
                return;
            }

            EntityDayZombie entityzombie = new EntityDayZombie(super.worldObj);
            entityzombie.copyLocationAndAnglesFrom(p_70074_1_);
            super.worldObj.removeEntity(p_70074_1_);
            entityzombie.onSpawnWithEgg((IEntityLivingData) null);
            entityzombie.setVillager(true);
            if (p_70074_1_.isChild())
            {
                entityzombie.setChild(true);
            }

            super.worldObj.spawnEntityInWorld(entityzombie);
            super.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1016, (int) super.posX, (int) super.posY, (int) super.posZ, 0);
        }

    }

    public IEntityLivingData onSpawnWithEgg(IEntityLivingData p_110161_1_)
    {
        Object p_110161_1_1 = super.onSpawnWithEgg(p_110161_1_);
        float f = super.worldObj.func_147462_b(super.posX, super.posY, super.posZ);
        this.setCanPickUpLoot(super.rand.nextFloat() < 0.55F * f);
        if (p_110161_1_1 == null)
        {
            p_110161_1_1 = new EntityDayZombie.GroupData(super.worldObj.rand.nextFloat() < ForgeModContainer.zombieBabyChance, super.worldObj.rand.nextFloat() < 0.05F, (Object) null);
        }

        if (p_110161_1_1 instanceof EntityDayZombie.GroupData)
        {
            EntityDayZombie.GroupData d0 = (EntityDayZombie.GroupData) p_110161_1_1;
            if (d0.field_142046_b)
            {
                this.setVillager(true);
            }

            if (d0.field_142048_a)
            {
                this.setChild(true);
                if ((double) super.worldObj.rand.nextFloat() < 0.05D)
                {
                    List entitychicken1 = super.worldObj.selectEntitiesWithinAABB(EntityChicken.class, super.boundingBox.expand(5.0D, 3.0D, 5.0D), IEntitySelector.field_152785_b);
                    if (!entitychicken1.isEmpty())
                    {
                        EntityChicken entitychicken = (EntityChicken) entitychicken1.get(0);
                        entitychicken.func_152117_i(true);
                        this.mountEntity(entitychicken);
                    }
                } else if ((double) super.worldObj.rand.nextFloat() < 0.05D)
                {
                    EntityChicken entitychicken11 = new EntityChicken(super.worldObj);
                    entitychicken11.setLocationAndAngles(super.posX, super.posY, super.posZ, super.rotationYaw, 0.0F);
                    entitychicken11.onSpawnWithEgg((IEntityLivingData) null);
                    entitychicken11.func_152117_i(true);
                    super.worldObj.spawnEntityInWorld(entitychicken11);
                    this.mountEntity(entitychicken11);
                }
            }
        }

        this.func_146070_a(super.rand.nextFloat() < f * 0.1F);
        this.addRandomArmor();
        this.enchantEquipment();
        if (this.getEquipmentInSlot(4) == null)
        {
            Calendar d01 = super.worldObj.getCurrentDate();
            if (d01.get(2) + 1 == 10 && d01.get(5) == 31 && super.rand.nextFloat() < 0.25F)
            {
                this.setCurrentItemOrArmor(4, new ItemStack(super.rand.nextFloat() < 0.1F ? Blocks.lit_pumpkin : Blocks.pumpkin));
                super.equipmentDropChances[4] = 0.0F;
            }
        }

        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).applyModifier(new AttributeModifier("Random spawn bonus", super.rand.nextDouble() * 0.05000000074505806D, 0));
        double d02 = super.rand.nextDouble() * 1.5D * (double) super.worldObj.func_147462_b(super.posX, super.posY, super.posZ);
        if (d02 > 1.0D)
        {
            this.getEntityAttribute(SharedMonsterAttributes.followRange).applyModifier(new AttributeModifier("Random zombie-spawn bonus", d02, 2));
        }

        if (super.rand.nextFloat() < f * 0.05F)
        {
            this.getEntityAttribute(field_110186_bp).applyModifier(new AttributeModifier("Leader zombie bonus", super.rand.nextDouble() * 0.25D + 0.5D, 0));
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).applyModifier(new AttributeModifier("Leader zombie bonus", super.rand.nextDouble() * 3.0D + 1.0D, 2));
            this.func_146070_a(true);
        }

        return (IEntityLivingData) p_110161_1_1;
    }

    public boolean interact(EntityPlayer p_70085_1_)
    {
        ItemStack itemstack = p_70085_1_.getCurrentEquippedItem();
        if (itemstack != null && itemstack.getItem() == Items.golden_apple && itemstack.getItemDamage() == 0 && this.isVillager() && this.isPotionActive(Potion.weakness))
        {
            if (!p_70085_1_.capabilities.isCreativeMode)
            {
                --itemstack.stackSize;
            }

            if (itemstack.stackSize <= 0)
            {
                p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, (ItemStack) null);
            }

            if (!super.worldObj.isRemote)
            {
                this.startConversion(super.rand.nextInt(2401) + 3600);
            }

            return true;
        } else
        {
            return false;
        }
    }

    protected void startConversion(int p_82228_1_)
    {
        this.conversionTime = p_82228_1_;
        this.getDataWatcher().updateObject(14, Byte.valueOf((byte) 1));
        this.removePotionEffect(Potion.weakness.id);
        this.addPotionEffect(new PotionEffect(Potion.damageBoost.id, p_82228_1_, Math.min(super.worldObj.difficultySetting.getDifficultyId() - 1, 0)));
        super.worldObj.setEntityState(this, (byte) 16);
    }

    @SideOnly(Side.CLIENT)
    public void handleHealthUpdate(byte p_70103_1_)
    {
        if (p_70103_1_ == 16)
        {
            super.worldObj.playSound(super.posX + 0.5D, super.posY + 0.5D, super.posZ + 0.5D, "mob.zombie.remedy", 1.0F + super.rand.nextFloat(), super.rand.nextFloat() * 0.7F + 0.3F, false);
        } else
        {
            super.handleHealthUpdate(p_70103_1_);
        }

    }

    protected boolean canDespawn()
    {
        return !this.isConverting();
    }

    public boolean isConverting()
    {
        return this.getDataWatcher().getWatchableObjectByte(14) == 1;
    }

    protected void convertToVillager()
    {
        EntityVillager entityvillager = new EntityVillager(super.worldObj);
        entityvillager.copyLocationAndAnglesFrom(this);
        entityvillager.onSpawnWithEgg((IEntityLivingData) null);
        entityvillager.setLookingForHome();
        if (this.isChild())
        {
            entityvillager.setGrowingAge(-24000);
        }

        super.worldObj.removeEntity(this);
        super.worldObj.spawnEntityInWorld(entityvillager);
        entityvillager.addPotionEffect(new PotionEffect(Potion.confusion.id, 200, 0));
        super.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1017, (int) super.posX, (int) super.posY, (int) super.posZ, 0);
    }

    protected int getConversionTimeBoost()
    {
        int i = 1;
        if (super.rand.nextFloat() < 0.01F)
        {
            int j = 0;

            for (int k = (int) super.posX - 4; k < (int) super.posX + 4 && j < 14; ++k)
            {
                for (int l = (int) super.posY - 4; l < (int) super.posY + 4 && j < 14; ++l)
                {
                    for (int i1 = (int) super.posZ - 4; i1 < (int) super.posZ + 4 && j < 14; ++i1)
                    {
                        Block block = super.worldObj.getBlock(k, l, i1);
                        if (block == Blocks.iron_bars || block == Blocks.bed)
                        {
                            if (super.rand.nextFloat() < 0.3F)
                            {
                                ++i;
                            }

                            ++j;
                        }
                    }
                }
            }
        }

        return i;
    }

    public void func_146071_k(boolean p_146071_1_)
    {
        this.func_146069_a(p_146071_1_ ? 0.5F : 1.0F);
    }

    protected final void setSize(float p_70105_1_, float p_70105_2_)
    {
        boolean flag = this.field_146074_bv > 0.0F && this.field_146073_bw > 0.0F;
        this.field_146074_bv = p_70105_1_;
        this.field_146073_bw = p_70105_2_;
        if (!flag)
        {
            this.func_146069_a(1.0F);
        }

    }

    protected final void func_146069_a(float p_146069_1_)
    {
        super.setSize(this.field_146074_bv * p_146069_1_, this.field_146073_bw * p_146069_1_);
    }

    public float getBlockPathWeight(int p_70783_1_, int p_70783_2_, int p_70783_3_)
    {
        return 1.0F - super.worldObj.getLightBrightness(p_70783_1_, p_70783_2_, p_70783_3_);
    }

    protected boolean isValidLightLevel()
    {
        if (((int) (super.worldObj.getWorldTime() / 12000L) & 1) == 1)
        {
            return false;
        } else
        {
            int i = MathHelper.floor_double(super.posX);
            int j = MathHelper.floor_double(super.boundingBox.minY);
            int k = MathHelper.floor_double(super.posZ);
            if (super.worldObj.getSavedLightValue(EnumSkyBlock.Sky, i, j, k) <= super.rand.nextInt(32) && super.worldObj.canBlockSeeTheSky(i, j, k))
            {
                int i1 = super.worldObj.skylightSubtracted;
                super.worldObj.skylightSubtracted = 10;
                int l = super.worldObj.getBlockLightValue(i, j, k);
                super.worldObj.skylightSubtracted = i1;
                return l <= super.rand.nextInt(8);
            } else
            {
                return false;
            }
        }
    }

    static
    {
        babySpeedBoostModifier = new AttributeModifier(babySpeedBoostUUID, "Baby speed boost", 0.5D, 1);
    }

    class GroupData implements IEntityLivingData
    {
        public boolean field_142048_a;
        public boolean field_142046_b;
        private static final String __OBFID = "CL_00001704";

        private GroupData(boolean p_i2348_2_, boolean p_i2348_3_)
        {
            this.field_142048_a = false;
            this.field_142046_b = false;
            this.field_142048_a = p_i2348_2_;
            this.field_142046_b = p_i2348_3_;
        }

        GroupData(boolean p_i2349_2_, boolean p_i2349_3_, Object p_i2349_4_)
        {
            this(p_i2349_2_, p_i2349_3_);
        }
    }
}
