// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3

package net.instantgratification.agrarianreform.sidedness;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AgrarianReformSidednessContractTest: Pillar 5 Sidedness & Mixin Contract Verification Suite (MC 1.21.11)
 *
 * Verifies:
 * - Zero unguarded net.minecraft.client.* or com.mojang.blaze3d.* references in common sources & bytecode.
 * - Complete removal of ModVersionGuard.java and absence of Class.forName in AgrarianReformFabric.java.
 * - Structural integrity of agrarian_reform.mixins.json, agrarian_reform.client.mixins.json, and fabric.mod.json.
 */
public class AgrarianReformSidednessContractTest {

    @Test
    @DisplayName("Pillar 5: Common Sources & Bytecode Contain Zero Unguarded Client References")
    void testCommonSidednessIsolation() throws IOException {
        Path srcRoot = Paths.get("src/main/java");
        assertTrue(Files.exists(srcRoot), "src/main/java must exist");

        try (Stream<Path> stream = Files.walk(srcRoot)) {
            List<Path> javaFiles = stream.filter(p -> p.toString().endsWith(".java")).toList();
            assertFalse(javaFiles.isEmpty());

            for (Path file : javaFiles) {
                String normalized = file.toString().replace('\\', '/');
                String content = Files.readString(file, StandardCharsets.UTF_8);

                assertTrue(content.startsWith("// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3"),
                        "Missing GPLv3 license header on line 1 in: " + normalized);

                if (normalized.contains("/client/") || normalized.endsWith("/BlockColorsMixin.java")) {
                    continue;
                }

                // Client GUI integration classes in config/ must carry @Environment(EnvType.CLIENT)
                if (content.contains("@Environment(EnvType.CLIENT)")) {
                    continue;
                }

                assertFalse(content.contains("net.minecraft.client."),
                        "Forbidden client package reference in common source file: " + normalized);
                assertFalse(content.contains("com.mojang.blaze3d."),
                        "Forbidden Blaze3D reference in common source file: " + normalized);
            }
        }

        Path classesRoot = Paths.get("build/classes/java/main");
        if (Files.exists(classesRoot)) {
            try (Stream<Path> stream = Files.walk(classesRoot)) {
                List<Path> classFiles = stream.filter(p -> p.toString().endsWith(".class")).toList();
                for (Path classFile : classFiles) {
                    String normalized = classFile.toString().replace('\\', '/');
                    if (normalized.contains("/client/")
                            || normalized.contains("BlockColorsMixin")
                            || normalized.contains("ModMenuIntegration")
                            || normalized.contains("YaclScreenHelper")) {
                        continue;
                    }
                    String rawBytes = new String(Files.readAllBytes(classFile), StandardCharsets.ISO_8859_1);
                    assertFalse(rawBytes.contains("net/minecraft/client/"),
                            "Forbidden net/minecraft/client/ constant pool reference in common bytecode: " + normalized);
                    assertFalse(rawBytes.contains("com/mojang/blaze3d/"),
                            "Forbidden com/mojang/blaze3d/ constant pool reference in common bytecode: " + normalized);
                }
            }
        }
    }

    @Test
    @DisplayName("Pillar 5: ModVersionGuard Purged and Zero Unremapped Class.forName in Entrypoint")
    void testModVersionGuardPurged() throws IOException {
        Path guardPath = Paths.get("src/main/java/net/instantgratification/agrarianreform/util/ModVersionGuard.java");
        assertFalse(Files.exists(guardPath), "Obsolete ModVersionGuard.java must be deleted");

        Path entrypointPath = Paths.get("src/main/java/net/instantgratification/agrarianreform/AgrarianReformFabric.java");
        assertTrue(Files.exists(entrypointPath));
        String entrypointContent = Files.readString(entrypointPath, StandardCharsets.UTF_8);
        assertFalse(entrypointContent.contains("ModVersionGuard"),
                "AgrarianReformFabric.java must not reference ModVersionGuard");
        assertFalse(entrypointContent.contains("Class.forName"),
                "AgrarianReformFabric.java must not invoke Class.forName");
        assertTrue(entrypointContent.contains("player.swing(hand, true)"),
                "AgrarianReformFabric.java must use 2-arg player.swing(hand, true) overload");
        assertTrue(entrypointContent.contains("// Verified against: LivingEntity.java"),
                "AgrarianReformFabric.java must cite LivingEntity.java for player.swing signature");
    }

    @Test
    @DisplayName("Pillar 5: Mixin Configurations Separate Common and Client Mixins Cleanly")
    void testMixinConfigContracts() throws IOException {
        Path commonMixinsPath = Paths.get("src/main/resources/agrarian_reform.mixins.json");
        Path clientMixinsPath = Paths.get("src/main/resources/agrarian_reform.client.mixins.json");
        Path fabricModPath = Paths.get("src/main/resources/fabric.mod.json");

        assertTrue(Files.exists(commonMixinsPath));
        assertTrue(Files.exists(clientMixinsPath));
        assertTrue(Files.exists(fabricModPath));

        String commonJson = Files.readString(commonMixinsPath, StandardCharsets.UTF_8);
        String clientJson = Files.readString(clientMixinsPath, StandardCharsets.UTF_8);
        String fabricModJson = Files.readString(fabricModPath, StandardCharsets.UTF_8);

        assertFalse(commonJson.contains("BlockColorsMixin"),
                "Client mixin BlockColorsMixin must not be registered in common mixins array");
        assertTrue(commonJson.contains("FarmlandBlockMixin"));
        assertTrue(commonJson.contains("CropBlockMixin"));
        assertTrue(commonJson.contains("BlockStateBaseMixin"));
        assertTrue(clientJson.contains("BlockColorsMixin"));
        assertTrue(fabricModJson.contains("\"projectId\": \"nqQyDogh\""));
        assertTrue(fabricModJson.contains("\"slug\": \"vanilla-outsider-agrarian-reform\""));
    }
}
