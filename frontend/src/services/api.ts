// Definición de tipos para los datos de estudiantes y cursos
export interface Estudiante {
  id?: number;
  nombre: string;
  apellido: string;
  email: string;
  fechaNacimiento: string;
  telefono: string;
}

export interface Curso {
  id?: number;
  nombre: string;
  descripcion: string;
  creditos: number;
  cursoEstudiantes?: CursoEstudiante[];
}

export interface CursoEstudiante {
  id: number;
  estudiante_id: number;
}

// URLs base de los microservicios (ajuste para funcionar en local y Docker)
const BASE_URL_ESTUDIANTES =
  import.meta.env?.VITE_API_ESTUDIANTES && import.meta.env?.VITE_API_ESTUDIANTES !== "undefined"
    ? import.meta.env.VITE_API_ESTUDIANTES
    : "http://localhost:8002/api/estudiantes";

const BASE_URL_CURSOS =
  import.meta.env?.VITE_API_CURSOS && import.meta.env?.VITE_API_CURSOS !== "undefined"
    ? import.meta.env.VITE_API_CURSOS
    : "http://localhost:8003/api/cursos";

// Función para obtener cursos (evita llamada en tiempo de build)
export const getCursos = async (): Promise<Curso[]> => {
  if (import.meta.env.PROD) {
    return []; // Retorna un array vacío en tiempo de build
  }

  const response = await fetch(BASE_URL_CURSOS);
  if (!response.ok) throw new Error("Error al obtener los cursos");
  return await response.json();
};

// Función para obtener estudiantes (evita llamada en tiempo de build)
export const getEstudiantes = async (): Promise<Estudiante[]> => {
  try {
    console.log("📡 Fetching estudiantes desde:", BASE_URL_ESTUDIANTES);
    
    const response = await fetch(BASE_URL_ESTUDIANTES);
    
    console.log("🔍 Respuesta del servidor:", response.status, response.statusText);
    
    if (!response.ok) throw new Error(`Error ${response.status}: No se pudo obtener estudiantes`);
    
    const data = await response.json();
    console.log("📦 Datos recibidos de la API:", data);
    
    return data;
  } catch (error) {
    console.error("❌ Error en getEstudiantes:", error);
    return []; // Retorna un array vacío para evitar que la app se rompa
  }
};

// Función para obtener un curso por ID
export const getCursoById = async (id: number): Promise<Curso> => {
  if (import.meta.env.PROD) {
    throw new Error("No se puede obtener un curso en build");
  }

  const response = await fetch(`${BASE_URL_CURSOS}/${id}`);
  if (!response.ok) throw new Error("Curso no encontrado");
  return await response.json();
};

// Función para obtener un estudiante por ID
export const getEstudianteById = async (id: number): Promise<Estudiante> => {
  if (import.meta.env.PROD) {
    throw new Error("No se puede obtener un estudiante en build");
  }

  const response = await fetch(`${BASE_URL_ESTUDIANTES}/${id}`);
  if (!response.ok) throw new Error("Estudiante no encontrado");
  return await response.json();
};

// Función para crear un estudiante
export const createEstudiante = async (estudiante: Estudiante): Promise<Estudiante> => {
  const response = await fetch(BASE_URL_ESTUDIANTES, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(estudiante),
  });
  if (!response.ok) throw new Error("Error al crear el estudiante");
  return await response.json();
};

// Función para actualizar un estudiante
export const updateEstudiante = async (id: number, estudiante: Estudiante): Promise<Estudiante> => {
  const response = await fetch(`${BASE_URL_ESTUDIANTES}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(estudiante),
  });
  if (!response.ok) throw new Error("Error al actualizar el estudiante");
  return await response.json();
};

// Función para eliminar un estudiante
export const deleteEstudiante = async (id: number): Promise<void> => {
  const response = await fetch(`${BASE_URL_ESTUDIANTES}/${id}`, {
    method: "DELETE",
  });
  if (!response.ok) throw new Error("Error al eliminar el estudiante");
};

// Función para crear un curso
export const createCurso = async (curso: Curso): Promise<Curso> => {
  const response = await fetch(BASE_URL_CURSOS, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(curso),
  });
  if (!response.ok) throw new Error("Error al crear el curso");
  return await response.json();
};

// Función para actualizar un curso
export const updateCurso = async (id: number, curso: Curso): Promise<Curso> => {
  const response = await fetch(`${BASE_URL_CURSOS}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(curso),
  });
  if (!response.ok) throw new Error("Error al actualizar el curso");
  return await response.json();
};

// Función para eliminar un curso
export const deleteCurso = async (id: number): Promise<void> => {
  const response = await fetch(`${BASE_URL_CURSOS}/${id}`, {
    method: "DELETE",
  });
  if (!response.ok) throw new Error("Error al eliminar el curso");
};

// Función para matricular un estudiante a un curso
export const matricularEstudianteEnCurso = async (cursoId: number, estudiante: Estudiante): Promise<{ id: number }> => {
  if (import.meta.env.PROD) {
    throw new Error("No se puede matricular en build");
  }

  const response = await fetch(`${BASE_URL_CURSOS}/matricular-estudiante/${cursoId}`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(estudiante),
  });
  if (!response.ok) throw new Error("Error al matricular el estudiante al curso");
  return await response.json();
};

// Función para desmatricular un estudiante de un curso
export const desmatricularEstudianteDeCurso = async (cursoId: number, estudianteId: number): Promise<{ mensaje: string }> => {
  if (import.meta.env.PROD) {
    throw new Error("No se puede desmatricular en build");
  }

  const response = await fetch(`${BASE_URL_CURSOS}/desmatricular-estudiante/${cursoId}/estudiante/${estudianteId}`, {
    method: "DELETE",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ id: estudianteId }),
  });
  if (!response.ok) throw new Error("Error al desmatricular el estudiante del curso");
  return await response.json();
};