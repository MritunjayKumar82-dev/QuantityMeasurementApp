package com.apps.quantitymeasurement.UC15;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Singleton in-memory cache repository that also persists entities to disk.
 * Uses AppendableObjectOutputStream to append entities to a single file safely.
 */
public class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository {

    private static final String DATA_FILE = "quantity_measurements.dat";
    private static volatile QuantityMeasurementCacheRepository instance;

    private final List<QuantityMeasurementEntity> cache = new ArrayList<>();

    private QuantityMeasurementCacheRepository() {
        loadFromDisk();
    }

    /**
     * Thread-safe double-checked locking singleton.
     */
    public static QuantityMeasurementCacheRepository getInstance() {
        if (instance == null) {
            synchronized (QuantityMeasurementCacheRepository.class) {
                if (instance == null) {
                    instance = new QuantityMeasurementCacheRepository();
                }
            }
        }
        return instance;
    }

    @Override
    public synchronized void save(QuantityMeasurementEntity entity) {
        cache.add(entity);
        saveToDisk(entity);
    }

    @Override
    public synchronized List<QuantityMeasurementEntity> getAllMeasurements() {
        return Collections.unmodifiableList(new ArrayList<>(cache));
    }

    // ─── Disk persistence ─────────────────────────────────────────────────────

    private void saveToDisk(QuantityMeasurementEntity entity) {
        File file = new File(DATA_FILE);
        try (ObjectOutputStream oos = file.exists() && file.length() > 0
                ? new AppendableObjectOutputStream(new FileOutputStream(file, true))
                : new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(entity);
        } catch (IOException e) {
            System.err.println("Warning: Could not save entity to disk: " + e.getMessage());
        }
    }

    private void loadFromDisk() {
        File file = new File(DATA_FILE);
        if (!file.exists() || file.length() == 0) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    Object obj = ois.readObject();
                    if (obj instanceof QuantityMeasurementEntity entity) {
                        cache.add(entity);
                    }
                } catch (EOFException eof) {
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Warning: Could not load entities from disk: " + e.getMessage());
        }
    }

    /**
     * Custom ObjectOutputStream that skips writing the stream header on append,
     * allowing multiple objects to be read from a single file.
     */
    private static class AppendableObjectOutputStream extends ObjectOutputStream {
        AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            // Skip header to allow appending to existing file
            reset();
        }
    }
}
