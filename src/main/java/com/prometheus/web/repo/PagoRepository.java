package com.prometheus.web.repo;

import com.prometheus.web.model.Pago;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import com.prometheus.web.shared.PagedResult;

public class PagoRepository {
    private static final Map<Long, Pago> DATA = new LinkedHashMap<>();
    private static final AtomicLong SEQ = new AtomicLong(1);

    public static synchronized List<Pago> findAll() {
        return new ArrayList<>(DATA.values()); // copia defensiva
    }

    public static synchronized Pago save(Pago p) {
        if (p.getId() == 0) {
            p.setId(SEQ.getAndIncrement());
        }
        DATA.put(p.getId(), p);
        return p;
    }

    public static synchronized boolean deleteById(long id) {
        return DATA.remove(id) != null;
    }

    // ----- BÚSQUEDA -----
    public static synchronized List<Pago> search(String q) {
        if (q == null || q.isBlank())
            return findAll();
        String term = q.toLowerCase(Locale.ROOT).trim();
        return DATA.values().stream()
                .filter(p -> contains(String.valueOf(p.getId()), term) ||
                        contains(String.valueOf(p.getAlquilerId()), term) ||
                        contains(p.getFechaPago().toString(), term) ||
                        contains(p.getMontoMensual().toString(), term) ||
                        contains(p.isPagoRenta() ? "true" : "false", term) ||
                        contains(p.isPagoAgua() ? "true" : "false", term) ||
                        contains(p.isPagoEnergia() ? "true" : "false", term) ||
                        contains(p.isPagoGas() ? "true" : "false", term))
                .collect(Collectors.toList());
    }

    private static boolean contains(String s, String term) {
        return s != null && s.toLowerCase(Locale.ROOT).contains(term);
    }

    // ----- Paginación -----
    public static PagedResult<Pago> searchPage(String q, int page, int size) {
        List<Pago> all = search(q);
        int from = Math.max(0, Math.min(page * size, all.size()));
        int to = Math.min(from + size, all.size());
        List<Pago> slice = all.subList(from, to);
        return new PagedResult<>(slice, all.size(), page, size);
    }

    public static synchronized Pago update(Pago p) {
        DATA.put(p.getId(), p);
        return p;
    }

    public static synchronized Pago findById(long id) {
        return DATA.get(id);
    }
}
