// Tipos para la API de Prometheus

export interface ApiResponse<T> {
  success: boolean;
  message?: string;
  data?: T;
  error?: string;
}

export interface PagedResult<T> {
  items: T[];
  total: number;
  page: number;
  size: number;
}

// Entidades
export interface Property {
  id: number;
  nombre: string;
  direccion: string;
  descripcion: string;
  rentado: boolean;
  createdAt?: string;
  updatedAt?: string;
}

export interface Inquilino {
  id: number;
  nombre: string;
  email: string;
  telefono: string;
  direccion: string;
  documento?: string;
  createdAt?: string;
  updatedAt?: string;
}

export interface Alquiler {
  id: number;
  propiedadId: number;
  inquilinoId: number;
  fechaInicio: string;
  fechaFin: string;
  montoMensual: number;
  activo: boolean;
  createdAt?: string;
  updatedAt?: string;
}

export interface Pago {
  id: number;
  alquilerId: number;
  monto: number;
  fechaPago: string;
  metodoPago: string;
  estado: string;
  createdAt?: string;
  updatedAt?: string;
}

export interface Perfil {
  id: number;
  nombre: string;
  email: string;
  telefono: string;
  direccion: string;
  rol: string;
  createdAt?: string;
  updatedAt?: string;
}

// Tipos para formularios
export interface PropertyFormData {
  nombre: string;
  direccion: string;
  descripcion: string;
  rentado: boolean;
}

export interface InquilinoFormData {
  nombre: string;
  email: string;
  telefono: string;
  direccion: string;
  documento?: string;
}

export interface AlquilerFormData {
  propiedadId: number;
  inquilinoId: number;
  fechaInicio: string;
  fechaFin: string;
  montoMensual: number;
  activo: boolean;
}

export interface PagoFormData {
  alquilerId: number;
  monto: number;
  fechaPago: string;
  metodoPago: string;
  estado: string;
}

export interface PerfilFormData {
  nombre: string;
  email: string;
  telefono: string;
  direccion: string;
  rol: string;
}
