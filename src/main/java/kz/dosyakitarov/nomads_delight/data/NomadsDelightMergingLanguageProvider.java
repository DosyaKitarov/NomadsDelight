package kz.dosyakitarov.nomads_delight.data;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.data.PackOutput;
import net.minecraft.util.GsonHelper;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public abstract class NomadsDelightMergingLanguageProvider extends LanguageProvider {

    private final String locale;
    private final String modId;
    private final Set<String> generatedKeys = new LinkedHashSet<>();

    protected NomadsDelightMergingLanguageProvider(PackOutput output, String modId, String locale) {
        super(output, modId, locale);
        this.modId = modId;
        this.locale = locale;
    }

    @Override
    public void add(@NotNull String key, @NotNull String value) {
        generatedKeys.add(key);
        super.add(key, value);
    }

    protected void mergeManualTranslations() {
        Path langFile = Path.of("src", "main", "resources",
                "assets", modId, "lang", locale + ".json");

        if (!Files.exists(langFile)) return;

        try (BufferedReader reader = Files.newBufferedReader(langFile, StandardCharsets.UTF_8)) {
            JsonObject json = GsonHelper.parse(reader);
            for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                if (!generatedKeys.contains(entry.getKey())) {
                    add(entry.getKey(), entry.getValue().getAsString());
                }
            }
        } catch (IOException e) {
        }
    }
}
