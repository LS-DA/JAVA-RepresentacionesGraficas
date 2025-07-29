package com.lsda.api.services;

import com.lsda.api.entities.EmpresaCa;
import com.lsda.api.repositories.EmpresaJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EmpresaCacheService {

    @Autowired
    private EmpresaJdbcRepository empresaRepository;

    // Caché thread-safe global
    private static final Map<String, EmpresaCa> EMPRESA_CACHE = new ConcurrentHashMap<>();
    
    // Timestamp de la última actualización
    private volatile long lastUpdate;

    /**
     * Carga inicial del caché al arrancar la aplicación
     */
    @PostConstruct
    public void cargarCacheInicial() {
        System.out.println("🚀 Iniciando carga de caché de empresas...");
        long inicio = System.currentTimeMillis();
        
        try {
            List<EmpresaCa> empresas = empresaRepository.getAllEmpresas();
            
            // Limpiar caché anterior
            EMPRESA_CACHE.clear();
            
            // Cargar empresas al caché usando NIT como key
            for (EmpresaCa empresa : empresas) {
                EMPRESA_CACHE.put(empresa.getNit(), empresa);
            }
            
            lastUpdate = System.currentTimeMillis();
            long tiempo = lastUpdate - inicio;
            
            System.out.println("✅ Caché de empresas cargado exitosamente:");
            System.out.println("   📊 Total empresas: " + empresas.size());
            System.out.println("   ⏱️ Tiempo de carga: " + tiempo + "ms");
            System.out.println("   🔄 Próxima actualización: en 1 hora");
            
        } catch (Exception e) {
            System.err.println("❌ Error al cargar caché de empresas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Actualización automática cada hora
     */
    @Scheduled(fixedRate = 3600000) // 1 hora = 3600000 ms
    public void actualizarCache() {
        System.out.println("🔄 Actualizando caché de empresas (programado)...");
        cargarCacheInicial();
    }

    /**
     * Obtener empresa por NIT desde el caché
     */
    public static EmpresaCa getEmpresaByNit(String nit) {
        if (nit == null) return null;
        return EMPRESA_CACHE.get(nit.trim());
    }

    /**
     * Obtener todas las empresas del caché
     */
    public static Map<String, EmpresaCa> getAllEmpresas() {
        return new ConcurrentHashMap<>(EMPRESA_CACHE);
    }

    /**
     * Verificar si el caché contiene una empresa
     */
    public static boolean containsEmpresa(String nit) {
        if (nit == null) return false;
        return EMPRESA_CACHE.containsKey(nit.trim());
    }

    /**
     * Obtener el tamaño del caché
     */
    public static int getCacheSize() {
        return EMPRESA_CACHE.size();
    }

    /**
     * Obtener timestamp de la última actualización
     */
    public long getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Forzar actualización manual del caché
     */
    public void forzarActualizacion() {
        System.out.println("🔄 Forzando actualización manual del caché...");
        cargarCacheInicial();
    }

    /**
     * Limpiar caché manualmente
     */
    public void limpiarCache() {
        EMPRESA_CACHE.clear();
        System.out.println("🗑️ Caché de empresas limpiado");
    }
}