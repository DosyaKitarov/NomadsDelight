package kz.dosyakitarov.nomads_delight.client;

import kz.dosyakitarov.nomads_delight.platform.Services;
import kz.dosyakitarov.nomads_delight.platform.services.IClientPlatformHelper;

/** Client counterpart of {@link Services}; only ever touched from client code. */
public class ClientServices {

    public static final IClientPlatformHelper CLIENT = Services.load(IClientPlatformHelper.class);
}
