package dk.mrspring.wasteland.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dk.mrspring.wasteland.entity.EntityDayZombie;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.model.ModelZombieVillager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderDayZombie extends RenderBiped {

   private static final ResourceLocation zombieTextures = new ResourceLocation("WLM:textures/zombie.png");
   private ModelBiped field_82434_o;
   private ModelZombieVillager zombieVillagerModel;
   protected ModelBiped field_82437_k;
   protected ModelBiped field_82435_l;
   protected ModelBiped field_82436_m;
   protected ModelBiped field_82433_n;
   private int field_82431_q = 1;
   private static final String __OBFID = "CL_00001037";


   public RenderDayZombie() {
      super(new ModelZombie(), 0.5F, 1.0F);
      this.field_82434_o = super.modelBipedMain;
      this.zombieVillagerModel = new ModelZombieVillager();
   }

   protected void func_82421_b() {
      super.field_82423_g = new ModelZombie(1.0F, true);
      super.field_82425_h = new ModelZombie(0.5F, true);
      this.field_82437_k = super.field_82423_g;
      this.field_82435_l = super.field_82425_h;
      this.field_82436_m = new ModelZombieVillager(1.0F, 0.0F, true);
      this.field_82433_n = new ModelZombieVillager(0.5F, 0.0F, true);
   }

   protected int shouldRenderPass(EntityDayZombie p_77032_1_, int p_77032_2_, float p_77032_3_) {
      this.func_82427_a(p_77032_1_);
      return super.shouldRenderPass(p_77032_1_, p_77032_2_, p_77032_3_);
   }

   public void doRender(EntityDayZombie p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      this.func_82427_a(p_76986_1_);
      super.doRender(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
   }

   protected ResourceLocation getEntityTexture(EntityDayZombie p_110775_1_) {
      return zombieTextures;
   }

   protected void renderEquippedItems(EntityDayZombie p_77029_1_, float p_77029_2_) {
      this.func_82427_a(p_77029_1_);
      super.renderEquippedItems(p_77029_1_, p_77029_2_);
   }

   private void func_82427_a(EntityDayZombie p_82427_1_) {
      if(p_82427_1_.isVillager()) {
         if(this.field_82431_q != this.zombieVillagerModel.func_82897_a()) {
            this.zombieVillagerModel = new ModelZombieVillager();
            this.field_82431_q = this.zombieVillagerModel.func_82897_a();
            this.field_82436_m = new ModelZombieVillager(1.0F, 0.0F, true);
            this.field_82433_n = new ModelZombieVillager(0.5F, 0.0F, true);
         }

         super.mainModel = this.zombieVillagerModel;
         super.field_82423_g = this.field_82436_m;
         super.field_82425_h = this.field_82433_n;
      } else {
         super.mainModel = this.field_82434_o;
         super.field_82423_g = this.field_82437_k;
         super.field_82425_h = this.field_82435_l;
      }

      super.modelBipedMain = (ModelBiped)super.mainModel;
   }

   protected void rotateCorpse(EntityDayZombie p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
      if(p_77043_1_.isConverting()) {
         p_77043_3_ += (float)(Math.cos((double)p_77043_1_.ticksExisted * 3.25D) * 3.141592653589793D * 0.25D);
      }

      super.rotateCorpse(p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
   }

   protected void renderEquippedItems(EntityLiving p_77029_1_, float p_77029_2_) {
      this.renderEquippedItems((EntityDayZombie)p_77029_1_, p_77029_2_);
   }

   protected ResourceLocation getEntityTexture(EntityLiving p_110775_1_) {
      return this.getEntityTexture((EntityDayZombie)p_110775_1_);
   }

   public void doRender(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      this.doRender((EntityDayZombie)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
   }

   protected int shouldRenderPass(EntityLiving p_77032_1_, int p_77032_2_, float p_77032_3_) {
      return this.shouldRenderPass((EntityDayZombie)p_77032_1_, p_77032_2_, p_77032_3_);
   }

   protected int shouldRenderPass(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_) {
      return this.shouldRenderPass((EntityDayZombie)p_77032_1_, p_77032_2_, p_77032_3_);
   }

   protected void renderEquippedItems(EntityLivingBase p_77029_1_, float p_77029_2_) {
      this.renderEquippedItems((EntityDayZombie)p_77029_1_, p_77029_2_);
   }

   protected void rotateCorpse(EntityLivingBase p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
      this.rotateCorpse((EntityDayZombie)p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
   }

   public void doRender(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      this.doRender((EntityDayZombie)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
   }

   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
      return this.getEntityTexture((EntityDayZombie)p_110775_1_);
   }

   public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      this.doRender((EntityDayZombie)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
   }

}
