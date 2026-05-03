package com.nocta.ores.data;

import com.nocta.ores.content.NoctaOreItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;

public final class NoctaOresRecipeProvider extends RecipeProvider {

    public NoctaOresRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(NoctaOreItems.RAW_TIN.get()),
                        RecipeCategory.MISC,
                        NoctaOreItems.TIN_INGOT.get(),
                        0.7f, 200)
                .unlockedBy("has_raw_tin", this.has(NoctaOreItems.RAW_TIN.get()))
                .save(this.output, "tin_ingot_from_smelting_raw_tin");

        SimpleCookingRecipeBuilder.blasting(
                        Ingredient.of(NoctaOreItems.RAW_TIN.get()),
                        RecipeCategory.MISC,
                        NoctaOreItems.TIN_INGOT.get(),
                        0.7f, 100)
                .unlockedBy("has_raw_tin", this.has(NoctaOreItems.RAW_TIN.get()))
                .save(this.output, "tin_ingot_from_blasting_raw_tin");

        SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(NoctaOreItems.RAW_BRONZE.get()),
                        RecipeCategory.MISC,
                        NoctaOreItems.BRONZE_INGOT.get(),
                        1.0f, 200)
                .unlockedBy("has_raw_bronze", this.has(NoctaOreItems.RAW_BRONZE.get()))
                .save(this.output, "bronze_ingot_from_smelting_raw_bronze");

        SimpleCookingRecipeBuilder.blasting(
                        Ingredient.of(NoctaOreItems.RAW_BRONZE.get()),
                        RecipeCategory.MISC,
                        NoctaOreItems.BRONZE_INGOT.get(),
                        1.0f, 100)
                .unlockedBy("has_raw_bronze", this.has(NoctaOreItems.RAW_BRONZE.get()))
                .save(this.output, "bronze_ingot_from_blasting_raw_bronze");

        ShapelessRecipeBuilder.shapeless(
                        this.registries.lookupOrThrow(net.minecraft.core.registries.Registries.ITEM),
                        RecipeCategory.MISC,
                        NoctaOreItems.BRONZE_INGOT.get(),
                        2)
                .requires(Items.COPPER_INGOT)
                .requires(NoctaOreItems.TIN_INGOT.get())
                .unlockedBy("has_tin_ingot", this.has(NoctaOreItems.TIN_INGOT.get()))
                .save(this.output, "bronze_ingot_from_alloying");
    }

    public static final class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
            super(output, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            return new NoctaOresRecipeProvider(registries, output);
        }

        @Override
        public String getName() {
            return "Nocta Ores Recipes";
        }
    }
}