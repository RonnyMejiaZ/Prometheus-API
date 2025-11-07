package com.prometheus.web.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pago {
    private long id;
    private long alquilerId;

    private LocalDate fechaPago;
    private BigDecimal montoMensual;
    private boolean pagoRenta;

    private boolean pagoAgua;

    private boolean pagoEnergia;

    private boolean pagoGas;

    public Pago() {
    }

    public Pago(long id,
            long alquilerId,
            LocalDate fechaPago,
            BigDecimal montoMensual,
            boolean pagoRenta,
            boolean pagoAgua,
            boolean pagoEnergia,
            boolean pagoGas) {
        this.id = id;
        this.alquilerId = alquilerId;
        this.fechaPago = fechaPago;
        this.montoMensual = montoMensual;
        this.pagoRenta = pagoRenta;
        this.pagoAgua = pagoAgua;
        this.pagoEnergia = pagoEnergia;
        this.pagoGas = pagoGas;
    }

    // Getters & Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAlquilerId() {
        return alquilerId;
    }

    public void setAlquilerId(long alquilerId) {
        this.alquilerId = alquilerId;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public BigDecimal getMontoMensual() {
        return montoMensual;
    }

    public void setMontoMensual(BigDecimal montoMensual) {
        this.montoMensual = montoMensual;
    }

    public boolean isPagoRenta() {
        return pagoRenta;
    }

    public void setPagoRenta(boolean pagoRenta) {
        this.pagoRenta = pagoRenta;
    }

    public boolean isPagoAgua() {
        return pagoAgua;
    }

    public boolean isPagoEnergia() {
        return pagoEnergia;
    }

    public void setPagoEnergia(boolean pagoEnergia) {
        this.pagoEnergia = pagoEnergia;
    }

    public boolean isPagoGas() {
        return pagoGas;
    }

    public void setPagoGas(boolean pagoGas) {
        this.pagoGas = pagoGas;
    }

    public void setPagoAgua(boolean pagoAgua) {
        this.pagoAgua = pagoAgua;
    }
}
