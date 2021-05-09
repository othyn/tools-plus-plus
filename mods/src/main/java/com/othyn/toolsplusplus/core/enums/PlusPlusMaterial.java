package com.othyn.toolsplusplus.core.enums;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

public class PlusPlusMaterial implements IItemTier {

	@Override
	public int getUses() {
		// Durability
		return 15000;
	}

	@Override
	public float getSpeed() {
		// Usage speed
		return 100;
	}

	@Override
	public float getAttackDamageBonus() {
		// Base attack damage
		return 15;
	}

	@Override
	public int getLevel() {
		// Harvest level
		return 10;
	}

	@Override
	public int getEnchantmentValue() {
		// Enchantability
		return 35;
	}

	@Override
	public Ingredient getRepairIngredient() {
		// Repair items
		return Ingredient.of(Items.DIAMOND);
	}

}
