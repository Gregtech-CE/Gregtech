package gregtech.common.worldgen;

import java.util.Random;

import gregtech.api.GregTechAPI;
import gregtech.api.unification.material.type.Material;
import gregtech.api.util.GTWorldGen;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;

public class GTWorldGen_OreSmall extends GTWorldGen {

    private final int minY, maxY, amount;
    private final Material material;

    public GTWorldGen_OreSmall(String name, boolean enabled, int minY, int maxY, int amount, Material material, String[] dimWhiteList, String[] biomeWhiteList) {
        super(name, enabled, 0, GregTechAPI.worldgenList, dimWhiteList, biomeWhiteList);
        this.minY = minY;
        this.maxY = maxY;
        this.amount = amount;
        this.material = material;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, Biome biome, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (!this.isGenerationAllowed(world, biome))
            return;
        if (material != null) {
            int x = chunkX << 4;
            int z = chunkZ << 4;
            BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
            for (int i = 0, j = Math.max(1, amount / 2 + random.nextInt(amount) / 2); i < j; i++) {
                generateOreBlock(world, pos.setPos(x + random.nextInt(16), minY + random.nextInt(Math.max(1, maxY- minY)), z + random.nextInt(16)), material, true, false);
            }
        }
    }

    @Override
    public String toString() {
        return "ore.small." + this.name;
    }

}
