// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3

package net.instantgratification.agrarianreform.concurrency;

import net.instantgratification.agrarianreform.config.AgrarianConfig;
import net.instantgratification.agrarianreform.continuum.ContinuumManager;
import net.instantgratification.agrarianreform.util.SoundHelper;
import net.minecraft.core.BlockPos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AgrarianReformConcurrencyLoadTest: Pillar 2 Concurrency & Thread-Safety Load Suite (MC 1.21.1)
 *
 * Spawns 64 concurrent worker threads hammered across synchronized AgrarianConfig
 * accessors/mutators, SoundHelper primitive FastUtil maps, and ContinuumManager.UPDATE_QUEUE.
 */
public class AgrarianReformConcurrencyLoadTest {

    private static final int THREAD_COUNT = 64;
    private static final int OPS_PER_THREAD = 250;

    @Test
    @DisplayName("Pillar 2: 64-Thread Concurrent Contention Across Config, SoundHelper & Continuum Queue")
    void testConcurrentContentionLoad() throws Exception {
        AgrarianConfig config = new AgrarianConfig();
        ContinuumManager.UPDATE_QUEUE.clear();

        ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);
        CountDownLatch startLatch = new CountDownLatch(1);
        List<Future<?>> futures = new ArrayList<>(THREAD_COUNT);

        for (int t = 0; t < THREAD_COUNT; t++) {
            final int threadId = t;
            futures.add(pool.submit(() -> {
                try {
                    startLatch.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }

                for (int i = 0; i < OPS_PER_THREAD; i++) {
                    String cropId = "mod_" + (threadId % 16) + ":crop_" + (i % 32);
                    config.recordDiscoveredCrop(cropId);
                    config.setForcedGrowthMultiplier(cropId, (threadId + 1) * 10);

                    Set<String> allIds = config.getAllForcedCropIds();
                    assertNotNull(allIds);
                    assertTrue(config.isForcedCrop(cropId));
                    int mult = config.getForcedGrowthMultiplier(cropId);
                    assertTrue(mult >= 0);

                    int entityId = 1000 + (threadId % 8);
                    long tick = i * 5L;
                    SoundHelper.shouldPlayRustle(entityId, tick);
                    if (i % 25 == 0) {
                        SoundHelper.purgeEntity(entityId);
                    }

                    BlockPos pos = new BlockPos(threadId, 64, i);
                    ContinuumManager.UPDATE_QUEUE.add(new ContinuumManager.CropUpdateTask(null, pos, 2400L));
                    ContinuumManager.UPDATE_QUEUE.poll();
                }
            }));
        }

        startLatch.countDown();

        for (Future<?> future : futures) {
            assertDoesNotThrow(() -> future.get(30, TimeUnit.SECONDS),
                    "Concurrent worker threw an exception (ConcurrentModificationException or race hazard)");
        }

        pool.shutdown();
        assertTrue(pool.awaitTermination(10, TimeUnit.SECONDS));

        // Deterministic post-load state checks
        Set<String> finalCrops = config.getAllForcedCropIds();
        assertEquals(16 * 32, finalCrops.size(), "All 512 distinct crop IDs must be deterministically recorded");
        ContinuumManager.UPDATE_QUEUE.clear();
    }
}
