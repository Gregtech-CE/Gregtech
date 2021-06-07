package gregtech.common.render;

import gregtech.api.block.machines.BlockMachine;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.BlockPatternChecker;
import gregtech.api.render.scene.WorldSceneRenderer;
import gregtech.integration.jei.GTJeiOptional;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(Side.CLIENT)
public class MultiHelperRenderer {
    @SubscribeEvent
    public static void renderWorldLastEvent(RenderWorldLastEvent evt) {
        drawBlockBoxHighLight(evt);
        drawMultiBlockPreview(evt);
    }

    private static BlockPos posHighLight;
    private static long hlEndTime;

    public static void renderBlockBoxHighLight(BlockPos blockpos, long durTimeMillis) {
        posHighLight = blockpos;
        hlEndTime = System.currentTimeMillis() + durTimeMillis;
    }

    private static void drawBlockBoxHighLight(RenderWorldLastEvent evt) {
        if (posHighLight != null) {
            long time = System.currentTimeMillis();
            if (time > hlEndTime) {
                posHighLight = null;
                hlEndTime = 0;
                return;
            }
            if (((time / 500) & 1) == 0) {
                return;
            }
            EntityPlayerSP p = Minecraft.getMinecraft().player;
            double doubleX = p.lastTickPosX + (p.posX - p.lastTickPosX) * evt.getPartialTicks();
            double doubleY = p.lastTickPosY + (p.posY - p.lastTickPosY) * evt.getPartialTicks();
            double doubleZ = p.lastTickPosZ + (p.posZ - p.lastTickPosZ) * evt.getPartialTicks();

            GlStateManager.pushMatrix();
            GlStateManager.color(1.0f, 0, 0);
            GlStateManager.glLineWidth(3);
            GlStateManager.translate(-doubleX, -doubleY, -doubleZ);

            GlStateManager.disableDepth();
            GlStateManager.disableTexture2D();

            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder buffer = tessellator.getBuffer();

            buffer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);

            renderHighLightedBlocksOutline(buffer, posHighLight.getX(), posHighLight.getY(), posHighLight.getZ(), 1.0f, 0.0f, 0.0f, 1.0f);

            tessellator.draw();

            GlStateManager.enableTexture2D();
            GlStateManager.enableDepth();
            GlStateManager.color(1, 1, 1);
            GlStateManager.popMatrix();
        }
    }

    private static BlockPos mbpPos;
    private static WorldSceneRenderer worldSceneRenderer;
    private static long mbpEndTime;
    private static int opList = -1;
    private static int layer = -1;
    private static byte renderMode = 0;
    private static MultiblockControllerBase controllerBase;

    public static void renderMultiBlockPreview(MultiblockControllerBase controller, long durTimeMillis, byte mode){
        if(controller == null) {
            reset();
            posHighLight = null;
            hlEndTime = 0;
        }
        else if(!controller.getPos().equals(mbpPos)) {
            layer = -1;
        }
        controllerBase = controller;
        mbpEndTime = durTimeMillis;
        renderMode = mode;
    }

    private static void drawMultiBlockPreview(RenderWorldLastEvent evt) {
        if (controllerBase != null && Loader.isModLoaded("jei")) {
            long durTimeMillis = mbpEndTime;
            reset();
            mbpEndTime = System.currentTimeMillis() + durTimeMillis;
            if (renderMode == 2) {
                BlockPos error = BlockPatternChecker.getPatternErrorPos(controllerBase);
                if (error != null) {
                    renderBlockBoxHighLight(error, durTimeMillis);
                }
                mbpEndTime = 0;
                controllerBase = null;
                return;
            }
            worldSceneRenderer = GTJeiOptional.getWorldSceneRenderer(controllerBase);
            if(worldSceneRenderer == null) {
                reset();
                controllerBase = null;
                return;
            }

            mbpPos = controllerBase.getPos();
            EnumFacing frontFacing, previewFacing;
            previewFacing = controllerBase.getFrontFacing();
            List<BlockPos> renderedBlocks = ObfuscationReflectionHelper.getPrivateValue(WorldSceneRenderer.class, worldSceneRenderer,"renderedBlocks");
            BlockPos controllerPos = BlockPos.ORIGIN;
            MultiblockControllerBase mte = null;
            int maxY = (int) worldSceneRenderer.world.getMaxPos().getY();
            int minY = (int) worldSceneRenderer.world.getMinPos().getY();

            if(renderedBlocks != null) {
                for(BlockPos blockPos : renderedBlocks) {
                    MetaTileEntity metaTE = BlockMachine.getMetaTileEntity(worldSceneRenderer.world, blockPos);
                    if(metaTE instanceof MultiblockControllerBase && metaTE.metaTileEntityId.equals(controllerBase.metaTileEntityId)) {
                        controllerPos = blockPos;
                        previewFacing = metaTE.getFrontFacing();
                        mte = (MultiblockControllerBase) metaTE;
                        break;
                    }
                }
                if (layer >= 0) {
                    if (layer + minY > maxY) { // shouldn't happen
                        layer = -1;
                    }
                    renderedBlocks = new ArrayList<>(renderedBlocks);
                    renderedBlocks.removeIf(pos -> pos.getY() != minY + layer);
                    if (mte != null) {
                        mte.invalidateStructure();
                    }
                    if (layer + minY == maxY){
                        layer = -1;
                    } else {
                        layer++;
                    }
                } else {
                    layer++;
                }
            } else {
                reset();
                controllerBase = null;
                return;
            }

            EnumFacing facing = controllerBase.getFrontFacing();
            BlockPattern.RelativeDirection[] structureDir = controllerBase.getStructurePattern().getStructureDir();

            if (structureDir == null) {
                reset();
                controllerBase = null;
                if (mte != null) {
                    mte.update();
                }
                return;
            }

            EnumFacing spin = BlockPatternChecker.getSpin(controllerBase);

            frontFacing = facing.getFrontOffsetY() == 0 ? facing : facing.getFrontOffsetY() < 0 ? spin : spin.getOpposite();

            Rotation rotatePreviewBy = Rotation.values()[(4 + frontFacing.getHorizontalIndex() - previewFacing.getHorizontalIndex()) % 4];

            opList = GLAllocation.generateDisplayLists(1); // allocate op list
            GlStateManager.glNewList(opList, GL11.GL_COMPILE);

            Minecraft mc = Minecraft.getMinecraft();

            BlockRenderLayer oldLayer = MinecraftForgeClient.getRenderLayer();
            BlockRendererDispatcher brd = mc.getBlockRendererDispatcher();
            WorldSceneRenderer.TrackedDummyWorld world = worldSceneRenderer.world;

            Tessellator tes = Tessellator.getInstance();
            BufferBuilder buff = tes.getBuffer();

            GlStateManager.pushMatrix();

            GlStateManager.translate(0.5, 0, 0.5);
            GlStateManager.rotate(rotatePreviewBy.ordinal() * 90, 0, -1, 0);
            GlStateManager.translate(-0.5, 0, -0.5);

            if (facing == EnumFacing.UP) {
                GlStateManager.translate(0.5, 0.5, 0.5);
                GlStateManager.rotate(90, -previewFacing.getFrontOffsetZ(), 0, previewFacing.getFrontOffsetX());
                GlStateManager.translate(-0.5, -0.5, -0.5);
            } else if (facing == EnumFacing.DOWN){
                GlStateManager.translate(0.5, 0.5, 0.5);
                GlStateManager.rotate(90, previewFacing.getFrontOffsetZ(), 0, -previewFacing.getFrontOffsetX());
                GlStateManager.translate(-0.5, -0.5, -0.5);
            } else {
                int degree = 90 * (spin == EnumFacing.EAST? -1: spin == EnumFacing.SOUTH? 2: spin == EnumFacing.WEST? 1:0);
                GlStateManager.translate(0.5, 0.5, 0.5);
                GlStateManager.rotate(degree, previewFacing.getFrontOffsetX(), 0, previewFacing.getFrontOffsetZ());
                GlStateManager.translate(-0.5, -0.5, -0.5);
            }


            TargetBlockAccess targetBA = new TargetBlockAccess(world, BlockPos.ORIGIN);

            for(BlockPos pos : renderedBlocks) {
                targetBA.setPos(pos);

                if (renderMode == 0) {
                    GlStateManager.pushMatrix();

                    BlockPos.MutableBlockPos tPos = new BlockPos.MutableBlockPos(pos.subtract(controllerPos));

                    GlStateManager.translate(tPos.getX(), tPos.getY(), tPos.getZ());
                    GlStateManager.translate(0.125, 0.125, 0.125);
                    GlStateManager.scale(0.75, 0.75, 0.75);

                    buff.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
                    IBlockState state = world.getBlockState(pos);
                    for(BlockRenderLayer brl : BlockRenderLayer.values()) {
                        if(state.getBlock().canRenderInLayer(state, brl)) {
                            ForgeHooksClient.setRenderLayer(brl);
                            brd.renderBlock(state, BlockPos.ORIGIN, targetBA, buff);
                        }
                    }
                    tes.draw();

                    GlStateManager.popMatrix();
                } else {
                    GlStateManager.disableTexture2D();
                    GlStateManager.disableDepth();
                    buff.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);
                    GlStateManager.glLineWidth(1);
                    renderHighLightedBlocksOutline(buff, pos.getX() - controllerPos.getX(), pos.getY() - controllerPos.getY(), pos.getZ() - controllerPos.getZ(), 1.0f, 0.0f, 0.0f, 1.0f);
                    tes.draw();
                    GlStateManager.enableDepth();
                    GlStateManager.enableTexture2D();
                }
            }

            GlStateManager.popMatrix();
            ForgeHooksClient.setRenderLayer(oldLayer);

            GlStateManager.glEndList();
            GlStateManager.enableLighting();
            if (mte != null) {
                try{
                    ObfuscationReflectionHelper.findMethod(MultiblockControllerBase.class, "checkStructurePattern", Void.TYPE).invoke(mte);
                } catch (Exception ignored){}
            }
            controllerBase = null;
        } else if (worldSceneRenderer != null) {
            long time = System.currentTimeMillis();
            if (time > mbpEndTime) {
                reset();
                layer = -1;
                controllerBase = null;
                return;
            }
            if (renderMode == 2) {
                mbpPos = BlockPatternChecker.getPatternErrorPos(controllerBase);
                controllerBase = null;
                return;
            }
            if (opList != -1) {
                Minecraft mc = Minecraft.getMinecraft();
                Entity entity = mc.getRenderViewEntity();
                if(entity == null) entity = mc.player;

                float partialTicks = evt.getPartialTicks();

                double tx = entity.lastTickPosX + ((entity.posX - entity.lastTickPosX) * partialTicks);
                double ty = entity.lastTickPosY + ((entity.posY - entity.lastTickPosY) * partialTicks);
                double tz = entity.lastTickPosZ + ((entity.posZ - entity.lastTickPosZ) * partialTicks);

                Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

                GlStateManager.color(1F, 1F, 1F, 1F);

                GlStateManager.pushMatrix();
//                GlStateManager.disableDepth();
                GlStateManager.translate(-tx, -ty, -tz);
                GlStateManager.translate(mbpPos.getX(), mbpPos.getY(), mbpPos.getZ());
                GlStateManager.enableBlend();
                GlStateManager.callList(opList);
//                GlStateManager.enableDepth();
                GlStateManager.popMatrix();

                GlStateManager.color(1F, 1F, 1F, 1F);
            }
        }
    }

    private static void reset(){
        mbpPos = null;
        worldSceneRenderer = null;
        mbpEndTime = 0;
        if(opList != -1) {
            GlStateManager.glDeleteLists(opList, 1);
            opList = -1;
        }
    }

    private static class TargetBlockAccess implements IBlockAccess {

        private final IBlockAccess delegate;
        private BlockPos targetPos;

        public TargetBlockAccess(IBlockAccess delegate, BlockPos pos) {
            this.delegate = delegate;
            this.targetPos = pos;
        }

        public void setPos(BlockPos pos) {
            targetPos = pos;
        }

        @Override
        public TileEntity getTileEntity(BlockPos pos) {
            return pos.equals(BlockPos.ORIGIN) ? delegate.getTileEntity(targetPos) : null;
        }

        @Override
        public int getCombinedLight(BlockPos pos, int lightValue) {
            return 15;
        }

        @Override
        public IBlockState getBlockState(BlockPos pos) {
            return pos.equals(BlockPos.ORIGIN) ? delegate.getBlockState(targetPos) : Blocks.AIR.getDefaultState();
        }

        @Override
        public boolean isAirBlock(BlockPos pos) {
            return !pos.equals(BlockPos.ORIGIN) || delegate.isAirBlock(targetPos);
        }

        @Override
        public Biome getBiome(BlockPos pos) {
            return delegate.getBiome(targetPos);
        }

        @Override
        public int getStrongPower(BlockPos pos, EnumFacing direction) {
            return 0;
        }

        @Override
        public WorldType getWorldType() {
            return delegate.getWorldType();
        }

        @Override
        public boolean isSideSolid(BlockPos pos, EnumFacing side, boolean _default) {
            return pos.equals(BlockPos.ORIGIN) && delegate.isSideSolid(targetPos, side, _default);
        }

    }

    private static void renderHighLightedBlocksOutline(BufferBuilder buffer, float mx, float my, float mz, float r, float g, float b, float a) {
        buffer.pos(mx, my, mz).color(r, g, b, a).endVertex();
        buffer.pos(mx + 1, my, mz).color(r, g, b, a).endVertex();
        buffer.pos(mx, my, mz).color(r, g, b, a).endVertex();
        buffer.pos(mx, my + 1, mz).color(r, g, b, a).endVertex();
        buffer.pos(mx, my, mz).color(r, g, b, a).endVertex();
        buffer.pos(mx, my, mz + 1).color(r, g, b, a).endVertex();
        buffer.pos(mx + 1, my + 1, mz + 1).color(r, g, b, a).endVertex();
        buffer.pos(mx, my + 1, mz + 1).color(r, g, b, a).endVertex();
        buffer.pos(mx + 1, my + 1, mz + 1).color(r, g, b, a).endVertex();
        buffer.pos(mx + 1, my, mz + 1).color(r, g, b, a).endVertex();
        buffer.pos(mx + 1, my + 1, mz + 1).color(r, g, b, a).endVertex();
        buffer.pos(mx + 1, my + 1, mz).color(r, g, b, a).endVertex();

        buffer.pos(mx, my + 1, mz).color(r, g, b, a).endVertex();
        buffer.pos(mx, my + 1, mz + 1).color(r, g, b, a).endVertex();
        buffer.pos(mx, my + 1, mz).color(r, g, b, a).endVertex();
        buffer.pos(mx + 1, my + 1, mz).color(r, g, b, a).endVertex();

        buffer.pos(mx + 1, my, mz).color(r, g, b, a).endVertex();
        buffer.pos(mx + 1, my, mz + 1).color(r, g, b, a).endVertex();
        buffer.pos(mx + 1, my, mz).color(r, g, b, a).endVertex();
        buffer.pos(mx + 1, my + 1, mz).color(r, g, b, a).endVertex();

        buffer.pos(mx, my, mz + 1).color(r, g, b, a).endVertex();
        buffer.pos(mx + 1, my, mz + 1).color(r, g, b, a).endVertex();
        buffer.pos(mx, my, mz + 1).color(r, g, b, a).endVertex();
        buffer.pos(mx, my + 1, mz + 1).color(r, g, b, a).endVertex();
    }
}
