package kz.dosyakitarov.nomads_delight.registry;

import kz.dosyakitarov.nomads_delight.block.entity.ButterChurnBlockEntity;
import kz.dosyakitarov.nomads_delight.platform.Services;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.List;
import java.util.function.Supplier;

public class NomadsDelightBlockEntityTypes {

    public static final Supplier<BlockEntityType<ButterChurnBlockEntity>> BUTTER_CHURN =
            Services.REGISTRAR.registerBlockEntityType("butter_churn",
                    ButterChurnBlockEntity::new, List.of(NomadsDelightBlocks.BUTTER_CHURN));

    /** Triggers static registration; called once from {@code NomadsDelight.init()} after blocks. */
    public static void init() {
    }
}
