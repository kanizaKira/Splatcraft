package com.cibernet.splatcraft.entities.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class ModelAbstractTank extends ModelArmorOverride
{
	protected List<ModelRenderer> inkPieces = new ArrayList<>();
	protected float inkPctg;
	
	public ModelAbstractTank()
	{
	
	}
	
	public void setInk(float inkPctg) {this.inkPctg = inkPctg;}
	
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
	{
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
		
		
		for(int i = 1; i <= inkPieces.size(); i++)
		{
			ModelRenderer box = inkPieces.get(i-1);
			if(inkPctg == 0)
			{
				box.isHidden = true;
				continue;
			}
			box.isHidden = false;
			box.offsetY = - Math.min((inkPctg), i/8f)/2f;
		}
	}
}
