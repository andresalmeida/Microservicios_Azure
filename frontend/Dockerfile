# Usar una imagen base de Node.js
FROM node:20 AS build

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar los archivos de configuración del proyecto
COPY package.json yarn.lock* ./

# Instalar dependencias
RUN yarn install

# Copiar el resto del código del proyecto
COPY . .

ARG VITE_API_ESTUDIANTES
ARG VITE_API_CURSOS

ENV VITE_API_ESTUDIANTES=${VITE_API_ESTUDIANTES}
ENV VITE_API_CURSOS=${VITE_API_CURSOS}

# Construir la aplicación
RUN yarn build

# Usar una imagen ligera de Nginx para servir la aplicación
FROM nginx:alpine

# Copiar los archivos construidos desde la etapa de construcción
COPY --from=build /app/dist /usr/share/nginx/html

# Copiar la configuración de Nginx personalizada
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Exponer el puerto 80
EXPOSE 80

# Iniciar Nginx
CMD ["nginx", "-g", "daemon off;"]