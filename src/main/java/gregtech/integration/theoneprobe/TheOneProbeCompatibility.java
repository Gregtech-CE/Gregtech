package gregtech.integration.theoneprobe;

import gregtech.integration.theoneprobe.provider.*;
import mcjty.theoneprobe.TheOneProbe;
import mcjty.theoneprobe.api.ITheOneProbe;

public class TheOneProbeCompatibility {

    public static void registerCompatibility() {
        ITheOneProbe oneProbe = TheOneProbe.theOneProbeImp;
        oneProbe.registerProvider(new ElectricContainerInfoProvider());
        oneProbe.registerProvider(new WorkableInfoProvider());
        oneProbe.registerProvider(new ControllableInfoProvider());
        oneProbe.registerProvider(new DebugPipeNetInfoProvider());
        oneProbe.registerProvider(new TransformerInfoProvider());
    }
}
